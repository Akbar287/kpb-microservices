package com.kpb.authservice.service;

import com.kpb.authservice.domain.User;
import com.kpb.authservice.dto.PaginationResponse;
import com.kpb.authservice.dto.informasi.InformasiAppbarResponse;
import com.kpb.authservice.dto.user.*;
import com.kpb.clientservice.web.CreateUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public PaginationResponse findAllUser(int page, int size, String sort, String search);
    public UserDetailsListResponseDTO findUserDetails(Long userId);
    public UserDetailsListResponseDTO findUserDetailsByUsername(String username);
    public CreateUserResponse searchUserByNama(String nama);
    public User findUserDetailByUsername(String username);
    public UserDetailsListResponseDTO createNewUser(UserCreateRequestDTO dto);
    public void addRoleToUser(Long user_id, String role_name);
    public void updateUser(Long userId, UserUpdateRequestDTO dto);
    public void updatePassword(Long userId, UserPasswordUpdateDTO dto);
    public void updateImage(Long userId, String image);
    public void deleteUser(Long userId);
}
