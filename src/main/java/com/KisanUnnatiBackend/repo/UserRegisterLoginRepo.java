package com.KisanUnnatiBackend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.UserRegisterLoginEntity;



@Repository
public interface UserRegisterLoginRepo extends JpaRepository<UserRegisterLoginEntity, Integer> {
    boolean existsByEmail(String email); // check duplicate email
    
    // Find user by email AND password
    Optional<UserRegisterLoginEntity> findByEmailAndPassword(String email, String password);

}
