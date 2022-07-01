package com.kpb.authservice.service.Impl;

import com.kpb.authservice.domain.Role;
import com.kpb.authservice.domain.User;
import com.kpb.authservice.dto.PaginationResponse;
import com.kpb.authservice.dto.user.*;
import com.kpb.authservice.exception.ResourceNotFoundException;
import com.kpb.authservice.repository.RoleRepository;
import com.kpb.authservice.repository.UserRepository;
import com.kpb.authservice.service.UserService;
import com.kpb.clientservice.web.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNamaRole())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public PaginationResponse findAllUser(int page, int size, String sort, String search) {
        sort = (sort != null) ? sort : "userId";
        search = ObjectUtils.isEmpty(search) ? "%" : "%" + search + "%";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<User> userResponse = userRepository.findAllByNamaLike(search, pageable);

        List<UserDetailsListResponseDTO> dtos = userResponse.stream().map((b) -> {
            UserDetailsListResponseDTO dto = new UserDetailsListResponseDTO();
            Collection<Role> roles = b.getRole().stream().map((userRole) -> {
                Role role = new Role();
                role.setRoleId(userRole.getRoleId());
                role.setNamaRole(userRole.getNamaRole());
                return role;
            }).collect(Collectors.toList());
            dto.setUserId(b.getUserId());
            dto.setNama(b.getNama());
            dto.setFileGambar(b.getFileGambar());
            dto.setEmail(b.getEmail());
            dto.setRoles(roles);
            dto.setUsername(b.getUsername());
            dto.setNomorTelepon(b.getNomorTelepon());
            dto.setCreatedAt(String.valueOf(b.getCreatedAt()));
            dto.setUpdatedAt(String.valueOf(b.getUpdatedAt()));
            return dto;
        }).toList();

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(dtos);
        paginationResponse.setEmpty(userResponse.isEmpty());
        paginationResponse.setFirst(userResponse.isFirst());
        paginationResponse.setLast(userResponse.isLast());
        paginationResponse.setNumber(userResponse.getNumber());
        paginationResponse.setPageable(userResponse.getPageable());
        paginationResponse.setSize(userResponse.getSize());
        paginationResponse.setSort(userResponse.getSort());
        paginationResponse.setTotalElements(userResponse.getTotalElements());
        paginationResponse.setNumberOfElements(userResponse.getNumberOfElements());
        paginationResponse.setTotalPages(userResponse.getTotalPages());
        return paginationResponse;
    }

    @Override
    public UserDetailsListResponseDTO findUserDetails(Long userId) {
        User userResponse = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        UserDetailsListResponseDTO userDetails = new UserDetailsListResponseDTO();
        Collection<Role> roles = userResponse.getRole().stream().map((userRole)->{
            Role role = new Role();
            role.setRoleId(userRole.getRoleId());
            role.setNamaRole(userRole.getNamaRole());
            return role;
        }).collect(Collectors.toList());
        userDetails.setNama(userResponse.getNama());
        userDetails.setFileGambar(userResponse.getFileGambar());
        userDetails.setUserId(userResponse.getUserId());
        userDetails.setRoles(roles);
        userDetails.setUsername(userResponse.getUsername());
        userDetails.setNomorTelepon(userResponse.getNomorTelepon());
        userDetails.setEmail(userResponse.getEmail());
        userDetails.setCreatedAt(String.valueOf(userResponse.getCreatedAt()));
        userDetails.setUpdatedAt(String.valueOf(userResponse.getUpdatedAt()));
        return userDetails;
    }

    @Override
    public UserDetailsListResponseDTO findUserDetailsByUsername(String username) {
        try {
            User userResponse = userRepository.findByUsername(username);
            return this.findUserDetails(userResponse.getUserId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("user.not.found");
        }
    }

    @Override
    public CreateUserResponse searchUserByNama(String nama) {
        User user = userRepository.findByNama(nama);

        CreateUserResponse userResponse = new CreateUserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setNama(user.getNama());
        userResponse.setFileGambar(user.getFileGambar());
        userResponse.setUsername(user.getUsername());
        userResponse.setNomorTelepon(user.getNomorTelepon());
        userResponse.setCreatedAt(String.valueOf(user.getCreatedAt()));
        userResponse.setUpdatedAt(String.valueOf(user.getUpdatedAt()));
        return userResponse;
    }

    @Override
    public User findUserDetailByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetailsListResponseDTO createNewUser(UserCreateRequestDTO dto) {
        User user = new User();
        user.setNomorTelepon(dto.getNomorTelepon());
        user.setNama(dto.getNama());
        user.setFileGambar(dto.getFileGambar());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);

        this.addRoleToUser(user.getUserId(), dto.getRole());
        return this.findUserDetails(user.getUserId());
    }

    @Override
    public void addRoleToUser(Long user_id, String role_name) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        role_name = "ROLE_"+role_name;
        Role role = roleRepository.findByNamaRole(role_name);
        List<Role> myRole = new ArrayList<>();
        myRole.add(role);
        user.setRole(myRole);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserUpdateRequestDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(user.getPassword());
        user.setFileGambar(user.getFileGambar());
        user.setNama(dto.getNama());
        user.setNomorTelepon(dto.getNomorTelepon());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public void updatePassword(Long userId, UserPasswordUpdateDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        if(Objects.equals(dto.getPasswordBaru(), dto.getKonfirmasiPassword())) {
            if (BCrypt.checkpw(dto.getPasswordLama(), user.getPassword())) {
                user.setPassword(BCrypt.hashpw(dto.getPasswordBaru(), BCrypt.gensalt()));
                userRepository.save(user);
            } else {
                throw new RuntimeException("wrong old password");
            }
        } else {
            throw new RuntimeException("new password not same with confirmation password");
        }
    }

    @Override
    public void updateImage(Long userId, String image) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user.not.found"));
        user.setFileGambar(image);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
