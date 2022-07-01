package com.kpb.allocationservice.repository;

import com.kpb.allocationservice.domain.PupukSubsidi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupukSubsidiRepository extends JpaRepository<PupukSubsidi, Long> {
     Page<PupukSubsidi> findByPetaniId(Long petaniId, Pageable pageable);
     PupukSubsidi findByTahunAndMasaTanamAndPetaniId(int tahun, int masaTanam, Long petaniId);
     List<PupukSubsidi> findAllByTahunAndMasaTanamAndPetaniIdIn(int tahun, int masaTanam, List<Long> petani);
     Page<PupukSubsidi> findAllByPetaniIdIn(List<Long> petaniId, Pageable pageable);
     Page<PupukSubsidi> findAllByTahunAndPetaniIdIn(int tahun, List<Long> petaniId, Pageable pageable);
     Page<PupukSubsidi> findAllByKiosIdIn(List<Long> kiosId, Pageable pageable);
     List<PupukSubsidi> findAllByPetaniIdAndTahunAndMasaTanam(Long petaniId, int tahun, int masa_tanam);
}
