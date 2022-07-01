package com.kpb.transactionalservice.repository;

import com.kpb.transactionalservice.domain.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    Page<Transaksi> findAllByNamaTransaksiLike(String nama, Pageable pageable);

    @Query(value = "SELECT * FROM transaksi WHERE nama_transaksi LIKE :nama AND is_penebusan ", nativeQuery = true)
    Page<Transaksi> findAllByNamaTransaksiLikeAndPenebusanIsTrue(@Param("nama") String nama, Pageable pageable);
    Page<Transaksi> findAllByNamaTransaksiLikeAndPetaniId(String nama, Long petaniId, Pageable pageable);

    @Query(value = "SELECT * FROM transaksi WHERE nama_transaksi LIKE :nama AND petani_id = :petani_id AND is_penebusan ", nativeQuery = true)
    Page<Transaksi> findAllByNamaTransaksiLikeAndPetaniIdAndPenebusanIsTrue(@Param("nama") String nama,@Param("petani_id") Long petaniId, Pageable pageable);
    Page<Transaksi> findAllByNamaTransaksiLikeAndKiosId(String nama, Long kios, Pageable pageable);

    @Query(value = "select * from transaksi t join penebusan_pupuk pp on t.penebusan_pupuk_id = pp.penebusan_pupuk_id where t.nama_transaksi like :search and t.is_penebusan = :isPenebusan and pp.is_poktan =true and t.petani_id = :petaniId", nativeQuery = true)
    Page<Transaksi> findForPoktan(@Param("search") String search, @Param("petaniId") Long petaniId, @Param("isPenebusan") Boolean isPenebusan, Pageable pageable);

    @Query(value = "select * from transaksi t join penebusan_pupuk pp on t.penebusan_pupuk_id = pp.penebusan_pupuk_id where t.nama_transaksi like :search and pp.is_poktan =true and t.petani_id = :petaniId", nativeQuery = true)
    Page<Transaksi> findForPoktanNotCondition(@Param("search") String search, @Param("petaniId") Long petaniId, Pageable pageable);

    @Query("SELECT t FROM Transaksi t WHERE t.namaTransaksi Like :search AND t.kiosId IN :kiosId AND t.isPenebusan = :isPenebusan")
    Page<Transaksi> findAllByNamaTransaksiLikeAndKiosIdInAndPenebusanTrue(@Param("search") String search, @Param("kiosId") List<Long> kiosId, @Param("isPenebusan") Boolean isPenebusan, Pageable pageable);

    Page<Transaksi> findAllByNamaTransaksiLikeAndKiosIdIn(String search, List<Long> kiosId, Pageable pageable);
}
