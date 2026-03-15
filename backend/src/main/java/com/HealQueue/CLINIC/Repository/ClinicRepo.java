package com.HealQueue.CLINIC.Repository;

import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClinicRepo extends JpaRepository<ClinicInfo, Long> {
    Optional<ClinicInfo> findByUserAccountData_UserName(String userName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM ClinicInfo c where c.id = :id")
    Optional<ClinicInfo> findByClinicIdForUpdate(@Param("id") long id);

    @Query("""
    SELECT c FROM ClinicInfo c
    WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(c.clinicName) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(c.address) LIKE LOWER(CONCAT('%', :query, '%'))
       OR LOWER(c.speciality) LIKE LOWER(CONCAT('%', :query, '%'))
       """)
    List<ClinicInfo> searchClinicByQuery(@Param("query") String query);
}
