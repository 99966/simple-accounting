package com.wuisee.hotel.repository;

import com.wuisee.hotel.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserAccount(String userAccount);
}
