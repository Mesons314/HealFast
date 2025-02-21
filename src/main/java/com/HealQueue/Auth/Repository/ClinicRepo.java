package com.HealQueue.Auth.Repository;

import com.HealQueue.Auth.Entity.ClinicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepo extends JpaRepository<ClinicInfo, Long> {
    ClinicInfo findByUserName(String userName);
    boolean existsByUserName(String userName);
}
