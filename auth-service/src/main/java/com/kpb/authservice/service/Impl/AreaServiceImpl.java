package com.kpb.authservice.service.Impl;

import com.kpb.authservice.domain.Area;
import com.kpb.authservice.domain.Role;
import com.kpb.authservice.domain.User;
import com.kpb.authservice.dto.area.AreaDetailResponseDTO;
import com.kpb.authservice.dto.area.AreaListCreateUpdateDTO;
import com.kpb.authservice.exception.ResourceNotFoundException;
import com.kpb.authservice.repository.AreaRepository;
import com.kpb.authservice.repository.UserRepository;
import com.kpb.authservice.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class AreaServiceImpl implements AreaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<AreaDetailResponseDTO> findDetail(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user.not.found"));

        List<AreaDetailResponseDTO> response = user.getArea().stream().map(area->{
            AreaDetailResponseDTO dto = new AreaDetailResponseDTO();
            dto.setAreaId(area.getAreaId());
            dto.setKodeDesa(area.getKodeDesa());
            dto.setNamaDesa(area.getNamaDesa());
            dto.setKodeKecamatan(area.getKodeKecamatan());
            dto.setNamaKecamatan(area.getNamaKecamatan());
            dto.setKodeKabupaten(area.getKodeKabupaten());
            dto.setNamaKabupaten(area.getNamaKabupaten());
            dto.setKodeProvinsi(area.getKodeProvinsi());
            dto.setNamaProvinsi(area.getNamaProvinsi());
            return dto;
        }).toList();
        return response;
    }

    @Override
    public List<Long> getUserIdInAreaByForm(String provinsi, String kabupaten, String kecamatan, String desa) {
        List<Area> area = areaRepository.findAllByNamaDesaAndNamaKecamatanAndNamaKabupatenAndNamaProvinsi(desa, kecamatan, kabupaten, provinsi);
        return area.stream().map(a -> a.getUser().getUserId()).toList();
    }

    public AreaDetailResponseDTO findDetailArea(Long areaId) {
        Area area = areaRepository.findById(areaId).orElseThrow(()->new ResourceNotFoundException("area.not.found"));
        AreaDetailResponseDTO dto = new AreaDetailResponseDTO();
        dto.setAreaId(area.getAreaId());
        dto.setKodeDesa(area.getKodeDesa());
        dto.setNamaDesa(area.getNamaDesa());
        dto.setKodeKecamatan(area.getKodeKecamatan());
        dto.setNamaKecamatan(area.getNamaKecamatan());
        dto.setKodeKabupaten(area.getKodeKabupaten());
        dto.setNamaKabupaten(area.getNamaKabupaten());
        dto.setKodeProvinsi(area.getKodeProvinsi());
        dto.setNamaProvinsi(area.getNamaProvinsi());
        return dto;
    }

    @Override
    public AreaDetailResponseDTO createArea(Long userId, AreaListCreateUpdateDTO dto) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user.not.found"));
        Area area = new Area();
        area.setKodeDesa(dto.getKodeDesa());
        area.setNamaDesa(dto.getNamaDesa());
        area.setKodeKecamatan(dto.getKodeKecamatan());
        area.setNamaKecamatan(dto.getNamaKecamatan());
        area.setKodeKabupaten(dto.getKodeKabupaten());
        area.setNamaKabupaten(dto.getNamaKabupaten());
        area.setKodeProvinsi(dto.getKodeProvinsi());
        area.setNamaProvinsi(dto.getNamaProvinsi());
        area.setUser(user);
        areaRepository.save(area);

        return this.findDetailArea(area.getAreaId());
    }

    @Override
    public void updateArea(Long areaId, AreaListCreateUpdateDTO dto) {
        Area area = areaRepository.findById(areaId).orElseThrow(()->new ResourceNotFoundException(("area.not.found")));
        area.setKodeDesa(dto.getKodeDesa());
        area.setNamaDesa(dto.getNamaDesa());
        area.setKodeKecamatan(dto.getKodeKecamatan());
        area.setNamaKecamatan(dto.getNamaKecamatan());
        area.setKodeKabupaten(dto.getKodeKabupaten());
        area.setNamaKabupaten(dto.getNamaKabupaten());
        area.setKodeProvinsi(dto.getKodeProvinsi());
        area.setNamaProvinsi(dto.getNamaProvinsi());
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Long areaId) {
        areaRepository.deleteById(areaId);
    }
}
