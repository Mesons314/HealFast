package com.HealQueue.Repository;

import com.HealQueue.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findByUserName(String userName);
}
