package com.HealQueue.Auth.Repository;

import com.HealQueue.Auth.Entity.UserAccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<UserAccountData, Long > {

    boolean existsByUserName(String userName);

    Optional<UserAccountData> findByUserName(String userName);
}
