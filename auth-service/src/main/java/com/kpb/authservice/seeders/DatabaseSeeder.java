package com.kpb.authservice.seeders;

import com.kpb.authservice.domain.Permission;
import com.kpb.authservice.domain.Role;
import com.kpb.authservice.dto.permission.PermissionCreateRequestDTO;
import com.kpb.authservice.dto.role.RoleCreateRequestDTO;
import com.kpb.authservice.dto.user.UserCreateRequestDTO;
import com.kpb.authservice.dto.user.UserRoleRequestDTO;
import com.kpb.authservice.repository.PermissionRepository;
import com.kpb.authservice.repository.RoleRepository;
import com.kpb.authservice.service.PermissionService;
import com.kpb.authservice.service.RoleService;
import com.kpb.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DatabaseSeeder {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    private void seedRoleTable() {
        roleService.createRole(new RoleCreateRequestDTO("ROLE_ADMIN"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_PETANI"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BINATANI"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_POKTAN"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BPP"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_OPDTPH"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_PENYULUH"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BANK"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_GUBERNUR"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_KEPALADAERAH"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_DISTRIBUTOR"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_KIOS"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_PABRIKAN"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BUYER"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_UPTDKECAMATAN"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BPTPH"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_KPTPH"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_POPT"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_KABKOTA"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_ASURANSI"));
        roleService.createRole(new RoleCreateRequestDTO("ROLE_BEASISWA"));
    }

    private void seedUsersTable() {
        userService.createNewUser(new UserCreateRequestDTO("muhammad_akbar287@outlook.com", "Akbar287", "Akbar", "avatar.png", "081234567891", "Akbar123", "ROLE_ADMIN"));
        userService.createNewUser(new UserCreateRequestDTO("erika@email.com", "Erika287", "Erika", "avatar.png", "08123456789113", "Erika123", "ROLE_PETANI"));
        userService.createNewUser(new UserCreateRequestDTO("kirito@email.com", "Kirito287", "Kirito", "avatar.png", "08123456729123", "Kirito123", "ROLE_BINATANI"));
        userService.createNewUser(new UserCreateRequestDTO("kaguya@email.com", "Kaguya287", "Kaguya", "avatar.png", "08123456739123", "Kaguya123", "ROLE_POKTAN"));
        userService.createNewUser(new UserCreateRequestDTO("franky@email.com", "Franky287", "Franky", "avatar.png", "08123456749123", "Franky123", "ROLE_BPP"));
        userService.createNewUser(new UserCreateRequestDTO("esdeath@email.com", "Esdeath287", "Esdeath", "avatar.png", "08123456789123", "Esdeath123", "ROLE_OPDTPH"));
        userService.createNewUser(new UserCreateRequestDTO("miyuki@email.com", "Miyuki287", "Miyuki", "avatar.png", "08123456769123", "Miyuki123", "ROLE_PENYULUH"));
        userService.createNewUser(new UserCreateRequestDTO("akame@email.com", "Akame287", "Akame", "avatar.png", "08123456789173", "Akame123", "ROLE_BANK"));
        userService.createNewUser(new UserCreateRequestDTO("killua@email.com", "Killua287", "Killua", "avatar.png", "08123456759123", "Killua123", "ROLE_GUBERNUR"));
        userService.createNewUser(new UserCreateRequestDTO("naruto@email.com", "Naruto287", "Naruto", "avatar.png", "08123456779123", "Naruto123", "ROLE_KEPALADAERAH"));
        userService.createNewUser(new UserCreateRequestDTO("floyd@email.com", "Floyd287", "Floy", "avatar.png", "08123456789129", "Floyd23", "ROLE_DISTRIBUTOR"));
        userService.createNewUser(new UserCreateRequestDTO("mikasa@email.com", "Mikasa287", "Mikasa", "avatar.png", "08123423729123", "Mikasa123", "ROLE_KIOS"));
        userService.createNewUser(new UserCreateRequestDTO("armin@email.com", "Armin287", "Armin", "avatar.png", "08123456789153", "Armin123", "ROLE_PABRIKAN"));
        userService.createNewUser(new UserCreateRequestDTO("nagisa@email.com", "Nagisa287", "Nagisa", "avatar.png", "08123456719123", "Nagisa123", "ROLE_BUYER"));
        userService.createNewUser(new UserCreateRequestDTO("mikey@email.com", "Mikey287", "Mikey", "avatar.png", "08123256719123", "Mikey123", "ROLE_UPTDKECAMATAN"));
        userService.createNewUser(new UserCreateRequestDTO("shinichi@email.com", "Shinichi287", "Shinichi", "avatar.png", "08123156719123", "Shinichi123", "ROLE_BPTPH"));
        userService.createNewUser(new UserCreateRequestDTO("mouri@email.com", "Mouri287", "Mouri", "avatar.png", "08123456712123", "Mouri123", "ROLE_KPTPH"));
        userService.createNewUser(new UserCreateRequestDTO("vermouth@email.com", "Vermouth287", "Vermouth", "avatar.png", "08123456719165", "Vermouth123", "ROLE_POPT"));
        userService.createNewUser(new UserCreateRequestDTO("kaito@email.com", "Kaito287", "Kaito", "avatar.png", "08123456733123", "Kaito123", "ROLE_KABKOTA"));
        userService.createNewUser(new UserCreateRequestDTO("forger@email.com", "Forger287", "Forger", "avatar.png", "08123421719123", "Forger123", "ROLE_ASURANSI"));
        userService.createNewUser(new UserCreateRequestDTO("shouko@email.com", "Shouko287", "Shouko", "avatar.png", "08123486719123", "Shouko123", "ROLE_BEASISWA"));
    }

    private void seedPermission() {
        permissionService.createPermission((new PermissionCreateRequestDTO("users:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("users:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("poktan:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("poktan:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("distributor:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("distributor:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("kios:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("kios:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("rdkk:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("rdkk:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("subsektor:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("subsektor:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("komoditas:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("komoditas:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("harga_saprotan:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("harga_saprotan:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("rut:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("rut:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("garap_list:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("garap_list:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("petani:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("petani:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("transaksi_tunai:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("transaksi_tunai:write")));
        permissionService.createPermission((new PermissionCreateRequestDTO("atur_rut:read")));
        permissionService.createPermission((new PermissionCreateRequestDTO("atur_rut:write")));
    }

    private void seedPermissionToRole() {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permissionRepository.findByNamaPermission("users:read"));
        permissions.add(permissionRepository.findByNamaPermission("users:write"));
        permissions.add(permissionRepository.findByNamaPermission("poktan:read"));
        permissions.add(permissionRepository.findByNamaPermission("poktan:write"));
        permissions.add(permissionRepository.findByNamaPermission("distributor:read"));
        permissions.add(permissionRepository.findByNamaPermission("distributor:write"));
        permissions.add(permissionRepository.findByNamaPermission("kios:read"));
        permissions.add(permissionRepository.findByNamaPermission("kios:write"));
        permissions.add(permissionRepository.findByNamaPermission("rdkk:read"));
        permissions.add(permissionRepository.findByNamaPermission("rdkk:write"));
        permissions.add(permissionRepository.findByNamaPermission("subsektor:read"));
        permissions.add(permissionRepository.findByNamaPermission("subsektor:write"));
        permissions.add(permissionRepository.findByNamaPermission("komoditas:read"));
        permissions.add(permissionRepository.findByNamaPermission("komoditas:write"));
        permissions.add(permissionRepository.findByNamaPermission("harga_saprotan:read"));
        permissions.add(permissionRepository.findByNamaPermission("harga_saprotan:write"));
        permissions.add(permissionRepository.findByNamaPermission("rut:read"));
        permissions.add(permissionRepository.findByNamaPermission("garap_list:read"));
        permissions.add(permissionRepository.findByNamaPermission("garap_list:write"));

        Role role = roleRepository.findByNamaRole("ROLE_ADMIN");
        role.setRolePermission(permissions);
        roleRepository.save(role);

        permissions.add(permissionRepository.findByNamaPermission("rut:read"));
        permissions.add(permissionRepository.findByNamaPermission("petani:read"));
        permissions.add(permissionRepository.findByNamaPermission("petani:write"));
        permissions.add(permissionRepository.findByNamaPermission("transaksi_tunai:read"));
        permissions.add(permissionRepository.findByNamaPermission("atur_rut:read"));
        permissions.add(permissionRepository.findByNamaPermission("atur_rut:write"));

        Role role2 = roleRepository.findByNamaRole("ROLE_PENYULUH");
        role2.setRolePermission(permissions);
        roleRepository.save(role2);
    }


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        // Here goes the seeders
//        seedRoleTable();
//        seedUsersTable();
//        seedPermission();
//        seedPermissionToRole();
    }

}
