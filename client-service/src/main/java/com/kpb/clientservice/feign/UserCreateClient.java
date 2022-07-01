package com.kpb.clientservice.feign;

import com.kpb.clientservice.web.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("auth-service")
public interface UserCreateClient {

    @GetMapping("/auth/api/user/{userId}")
    CreateUserResponse getUser(@PathVariable("userId") Long userId);

    @GetMapping("/auth/api/nama")
    CreateUserResponse searchUserByNama(@RequestParam("nama") String nama);

    @GetMapping("/auth/api/area/get-area")
    List<Long> getUserIdInAreaByForm(@RequestParam("provinsi") String provinsi, @RequestParam("kabupaten") String kabupaten, @RequestParam("kecamatan") String kecamatan, @RequestParam("desa") String desa);

    @PostMapping("/auth/api/user")
    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    @PutMapping("/auth/api/user/{userId}")
    CreateUserResponse updateUser(@PathVariable("userId") Long userId, CreateUserRequest createUserRequest);

    @DeleteMapping("/auth/api/user/{userId}")
    void deleteUser(@PathVariable("userId") Long userId);

    @GetMapping("/auth/api/area/{userId}")
    List<AreaResponse> getAreaByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/auth/api/area/{userId}")
    AreaResponse createArea(@PathVariable("userId") Long userId, AreaRequest areaRequest);

    @PutMapping("/auth/api/area/{areaId}")
    AreaResponse updateArea(@PathVariable("areaId") Long areaId, AreaRequest areaRequest);

    @GetMapping("/auth/api/akun/{userId}")
    List<AkunBankResponse> getAkunBank(@PathVariable("userId") Long userId);
}
