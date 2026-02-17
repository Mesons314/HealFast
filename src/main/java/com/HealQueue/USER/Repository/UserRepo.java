package com.HealQueue.USER.Repository;


import com.HealQueue.USER.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    Optional<UserInfo> findByUserAccountData_UserName(String userName);
//    boolean existsByUserName(String userName);
    String findUserAccountData_UserNameById(long id);

}
