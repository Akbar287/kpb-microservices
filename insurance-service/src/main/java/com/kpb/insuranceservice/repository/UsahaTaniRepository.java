package com.kpb.insuranceservice.repository;

import com.kpb.insuranceservice.domain.UsahaTani;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsahaTaniRepository extends JpaRepository<UsahaTani, Long> {
    Page<UsahaTani> findAllByPetaniIdIn(List<Long> listPetaniId, Pageable pageable);
}
