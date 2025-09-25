package com.KisanUnnatiBackend.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KisanUnnatiBackend.dto.UserRegisterDTO;


import com.KisanUnnatiBackend.entity.UserRegisterLoginEntity;
import com.KisanUnnatiBackend.repo.GetAllStateRepo;
import com.KisanUnnatiBackend.repo.UserRegisterLoginRepo;

@Service
public class UserRegisterLoginService {
	
	@Autowired
    private UserRegisterLoginRepo userRegisterRepo;
	
	 private GetAllStateRepo stateRepo;

    public UserRegisterLoginEntity registerUser(UserRegisterDTO dto) {
        // Check duplicate email
        if (userRegisterRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // Find state entity using stateId coming from frontend
       
        // Map DTO to Entity
        UserRegisterLoginEntity entity = new UserRegisterLoginEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setStateName(dto.getState());
        entity.setPassword(dto.getPassword()); // Optionally hash password
        entity.setJoiningDate(LocalDate.now());
        entity.setPosition(3); // default
        // Optional fields null by default
        return userRegisterRepo.save(entity);
    }
    
 // Login with email AND password check
    public UserRegisterLoginEntity loginUser(String email, String password) {
        Optional<UserRegisterLoginEntity> optionalUser = userRegisterRepo.findByEmailAndPassword(email, password);
        System.out.println("\n\n\n"+optionalUser);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
