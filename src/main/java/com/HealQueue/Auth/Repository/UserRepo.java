package com.HealQueue.Auth.Repository;

import com.HealQueue.Auth.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findByUserName(String userName);
}
