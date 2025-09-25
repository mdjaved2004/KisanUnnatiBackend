package com.KisanUnnatiBackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.*;

import com.KisanUnnatiBackend.dto.UserLoginDTO;
import com.KisanUnnatiBackend.dto.UserRegisterDTO;
import com.KisanUnnatiBackend.entity.UserRegisterLoginEntity;
import com.KisanUnnatiBackend.service.UserRegisterLoginService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRegisterLoginController {

    private final UserRegisterLoginService userRegisterService;

    
    
    // ------------------- REGISTER -------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO,
                                      BindingResult bindingResult,
                                      HttpSession session) {
        List<String> errorList = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            if(userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
                UserRegisterLoginEntity savedUser = userRegisterService.registerUser(userRegisterDTO);

                // Set session attributes
                session.setAttribute("userEmail", savedUser.getEmail());
                session.setAttribute("userName", savedUser.getName());

                return ResponseEntity.ok(Map.of(
                        "message", "User registered successfully",
                        "userName", savedUser.getName()
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "errors", "Password and confirmPassword do not match"
                ));
            }
        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }

    // ------------------- LOGIN -------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO,
                                   BindingResult bindingResult,
                                   HttpSession session) {
        List<String> errorList = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            UserRegisterLoginEntity user = userRegisterService.loginUser(
                    userLoginDTO.getEmail(), userLoginDTO.getPassword());

            // Set session attributes
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userName", user.getName());

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "userName", user.getName(),
                    "userEmail", user.getEmail()  // ✅ include email here
            ));
        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }

    // ------------------- GET SESSION INFO -------------------
    @GetMapping("/session")
    public ResponseEntity<?> getSession(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        String userName = (String) session.getAttribute("userName");

        if (userEmail == null || userName == null) {
            return ResponseEntity.status(401).body(Map.of("message", "No active session"));
        }

        return ResponseEntity.ok(Map.of(
                "userEmail", userEmail,
                "userName", userName
        ));
    }
}
