package com.kpb.memberservice.seeder;

import com.kpb.memberservice.dto.farmerGroup.FarmerGroupAddFarmerRequestDTO;
import com.kpb.memberservice.service.FarmerGroupService;
import com.kpb.memberservice.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class MemberSeeder {

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private FarmerGroupService farmerGroupService;

    public void seedFarmer() {
        farmerGroupService.addFarmerToGroup(3L, new FarmerGroupAddFarmerRequestDTO("6515841191746646", "Mikasa Ackerman", "Ibu Mikasa", "Wanita", "Aktif", "083645271849", "mikasa@email.com", "Mikasa123", "Jl. Kemenangan Titan No.12"));
        farmerGroupService.addFarmerToGroup(2L, new FarmerGroupAddFarmerRequestDTO("6515841191744223", "Levi Ackerman", "Ibu Levi", "Pria", "Aktif", "083645777749", "levi@email.com", "Levi12345", "Jl. Kemenangan Titan No.10"));
        farmerGroupService.addFarmerToGroup(3L, new FarmerGroupAddFarmerRequestDTO("6515841191746646", "Natsu Dragnell", "Ibu Natsu", "Pria", "Aktif", "0836412111849", "natsu@email.com", "Natsu12345", "Jl. Kemenangan Titan No.15"));
        farmerGroupService.addFarmerToGroup(2L, new FarmerGroupAddFarmerRequestDTO("6515841191555646", "Becky Blackbell", "Ibu Becky", "Wanita", "Aktif", "083643331849", "becky@email.com", "Becky123", "Jl. Kemenangan Titan No.16"));
        farmerGroupService.addFarmerToGroup(3L, new FarmerGroupAddFarmerRequestDTO("6515841196546646", "Damian Desmond", "Ibu Damian", "Pria", "Aktif", "083635271849", "damian@email.com", "Damian123", "Jl. Kemenangan Titan No.17"));
        farmerGroupService.addFarmerToGroup(1L, new FarmerGroupAddFarmerRequestDTO("6515841191757046", "Henry Henderson", "Ibu Henry", "Pria", "Aktif", "083644471849", "henry@email.com", "Henry123", "Jl. Kemenangan Titan No.18"));
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
//        seedFarmer();
    }
}
