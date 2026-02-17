package com.HealQueue.CLINIC.Repository;

import com.HealQueue.CLINIC.Entity.ClinicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface ClinicRepo extends JpaRepository<ClinicInfo, Long> {
    Optional<ClinicInfo> findByUserAccountData_UserName(String userName);
}
