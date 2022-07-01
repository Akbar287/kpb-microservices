package com.kpb.authservice.repository;

import com.kpb.authservice.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAllByNamaDesaAndNamaKecamatanAndNamaKabupatenAndNamaProvinsi(String desa, String kecamatan, String kabupaten, String provinsi);
}
