package com.wether.app.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wether.app.authservice.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
    
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
