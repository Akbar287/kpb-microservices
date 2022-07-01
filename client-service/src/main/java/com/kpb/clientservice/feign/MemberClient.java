package com.kpb.clientservice.feign;

import com.kpb.clientservice.web.Member.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("member-service")
public interface MemberClient {
    @GetMapping("/members/api/farmer/{petaniId}")
    PetaniResponse findPetani(@PathVariable("petaniId") Long petaniId);

    @GetMapping("/members/api/farmer/{userId}/user")
    PetaniResponse findPetaniByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/members/api/farmer-group/{userId}/user")
    PoktanResponse findPoktanByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/members/api/farmer/komoditas")
    KomoditasSubsektorResponse findKomoditasSubsektor(@RequestParam("petaniId") Long petaniId, @RequestParam("masa_tanam") int masa_tanam );

    @GetMapping("/members/api/farmer-group/poktan")
    PoktanResponse findPoktanByPoktanId(@RequestParam("poktanId") Long poktanId);

    @GetMapping("/members/api/farmer-group/list-petani")
    List<Long> findListPetaniByPoktanUserId(@RequestParam("poktanUserId") Long poktanUserId);

    @GetMapping("/members/api/farmer/search-nik")
    List<Long> findListPetaniBySearchNik(@RequestParam("nik") String nik);

    @GetMapping("/members/api/penyuluh/list-kios")
    List<Long> findListKiosByPenyuluhUserId(@RequestParam("penyuluhUserId") Long penyuluhUserId);

    @GetMapping("/members/api/kios/{kiosId}")
    KiosResponse findKios(@PathVariable("kiosId") Long kiosId);

    @GetMapping("/members/api/kios/{distributor}")
    List<KiosResponse> findAllKiosByDistributor(@PathVariable("distributor") Long distributor);

    @GetMapping("/members/api/kios/{distributor}")
    KiosResponse findKiosByDistributor(@PathVariable("distributor") Long distributor);

    @GetMapping("/members/api/kios/user")
    KiosResponse findKiosByUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/kios/petani")
    KiosResponse findKiosByPetaniUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/kios/poktan")
    KiosResponse findKiosByPoktanUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/distributor/kios")
    DistributorResponse findDistributorByKiosFromUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/distributor/petani")
    DistributorResponse findDistributorByPetaniFromUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/distributor/poktan")
    DistributorResponse findDistributorByPoktanFromUser(@RequestParam("user") Long user);

    @GetMapping("/members/api/distributor/{distributorId}")
    DistributorResponse findDistributor(@PathVariable("distributorId") Long distributorId);

    @GetMapping("/members/api/pabrikan/user")
    PabrikanResponse findPabrikanByUserId(@RequestParam("pabrikanUserId") Long pabrikanUserId);

    @GetMapping("/members/api/pabrikan/list-kios")
    List<Long> findListKiosByPabrikanUserId(@RequestParam("pabrikanUserId") Long pabrikanUserId);

    @GetMapping("/members/api/distributor/list-kios")
    List<Long> findListKiosIdByDistributorUserId(@RequestParam("distributorUserId") Long distributorUserId);

    @GetMapping("/members/api/distributor/list-all-distributors")
    List<Long> findAllListDistributors();
}
