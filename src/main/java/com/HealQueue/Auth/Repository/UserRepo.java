package com.HealQueue.Auth.Repository;


import com.HealQueue.Auth.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findByUserName(String userName);
    boolean existsByUserName(String userName);

    @Query("SELECT u.userName FROM UserInfo u WHERE u.id = :id")
    String findUserNameById(long id);
}
