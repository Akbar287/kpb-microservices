package com.kpb.authservice.web;

import com.kpb.authservice.dto.PaginationResponse;
import com.kpb.authservice.dto.informasi.InformasiAppbarResponse;
import com.kpb.authservice.dto.user.*;
import com.kpb.authservice.service.UserService;
import com.kpb.clientservice.web.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<PaginationResponse> findAllUser(@RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "5") int size, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "search", required = false) String search) {
        PaginationResponse result = userService.findAllUser(page, size, sort, search);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/nama")
    public ResponseEntity<CreateUserResponse> searchUserByNama(@RequestParam(value = "nama") String nama) {
        CreateUserResponse result = userService.searchUserByNama(nama);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetailsListResponseDTO> findUsername(@PathVariable("username") String username) {
        UserDetailsListResponseDTO result = userService.findUserDetailsByUsername(username);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDetailsListResponseDTO> findUser(@PathVariable("userId") Long userId) {
        UserDetailsListResponseDTO result = userService.findUserDetails(userId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDetailsListResponseDTO> createNewUser(@RequestBody UserCreateRequestDTO dto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        UserDetailsListResponseDTO user = userService.createNewUser(dto);
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequestDTO dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{userId}/password")
    public ResponseEntity<Void> changePw(@PathVariable("userId") Long userId, @RequestBody UserPasswordUpdateDTO dto) {
        userService.updatePassword(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{userId}/image")
    public ResponseEntity<Void> changeImage(@PathVariable("userId") Long userId, @RequestParam("image") String image) {
        userService.updateImage(userId, image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
