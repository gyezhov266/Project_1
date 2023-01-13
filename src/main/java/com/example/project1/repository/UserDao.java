package com.example.project1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.project1.entities.User;

public interface UserDao extends JpaRepository<User,Integer>{
    Optional<User> findByusername(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into users values (default, :username, :password)", nativeQuery = true)
    void createUser(String username, String password);
}
