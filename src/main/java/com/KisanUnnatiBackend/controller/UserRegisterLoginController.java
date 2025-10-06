package com.KisanUnnatiBackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.KisanUnnatiBackend.entity.UserContactdetailsEntity;
import com.KisanUnnatiBackend.entity.UserRegisterLoginEntity;
import com.KisanUnnatiBackend.service.UserRegisterLoginService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRegisterLoginController {

    private final UserRegisterLoginService userRegisterService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegisterDTO userRegisterDTO,
            BindingResult bindingResult,
            HttpSession session) {

        List<String> errorList = new ArrayList<>();

        // Validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            //Password match check
            if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
                return ResponseEntity.badRequest()
                        .body(Map.of("errors", "Password and confirmPassword do not match"));
            }

            //Call service layer
            Map<String, Object> information = userRegisterService.registerUser(userRegisterDTO);

            //Set session attributes
            UserRegisterLoginEntity userInfo = (UserRegisterLoginEntity) information.get("user");
            if (userInfo != null) {
                UserContactdetailsEntity userContactId = userInfo.getUserContactId();

                session.setAttribute("userName", userInfo.getName());
                session.setAttribute("userEmail", userInfo.getEmail());
                session.setAttribute("userState", userContactId.getStateName());
                session.setAttribute("userDistrict", userContactId.getDistrict());
                session.setAttribute("userCity", userContactId.getCity());
                session.setAttribute("userAddress", userContactId.getAddress());

                Map<String, Object> sessionUserMap = getSessionUserMap(session);
                System.out.println("===============successful==================");
                return ResponseEntity.ok(Map.of(
                        "message", "User registered successfully",
                        "user", sessionUserMap,
                        "buyingCropInfo", information.get("buyingCropInfo")
                ));
            } else {
                return ResponseEntity.ok(Map.of(
                        "message", "Something went wrong, try again",
                        "userName", userRegisterDTO.getName()
                ));
            }

        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }


    // ------------------- LOGIN -------------------
    @PostMapping(value = "/login", produces = "application/json") 
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult, HttpSession session) {
    	System.out.println("bhn hjdjnjs================");
        List<String> errorList = new ArrayList<>();
        //Handle validation errors
        if (bindingResult.hasErrors()) {
        	System.out.println("bhn hjdjnjs================");
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            Map<String, Object> information = userRegisterService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());

            //Check if user exists
            UserRegisterLoginEntity user = (UserRegisterLoginEntity) information.get("user");
            if (user != null) {
                UserContactdetailsEntity userContactId = user.getUserContactId();

                //Set session attributes
                session.setAttribute("userName", user.getName());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userState", userContactId.getStateName());
                session.setAttribute("userDistrict", userContactId.getDistrict());
                session.setAttribute("userCity", userContactId.getCity());
                session.setAttribute("userAddress", userContactId.getAddress());

                Map<String, Object> sessionUserMap = getSessionUserMap(session);
                System.out.println("===============successful==================");
                return ResponseEntity.ok(Map.of(
                        "message", "Login successful",
                        "user", sessionUserMap,
                        "buyingCropInfo", information.get("buyingCropInfo")
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "errors", "Something went wrong, try again"
                ));
            }

        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }
 
    private Map<String, Object> getSessionUserMap(HttpSession session) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userName", session.getAttribute("userName"));
        userMap.put("userEmail", session.getAttribute("userEmail"));
        userMap.put("userState", session.getAttribute("userState"));
        userMap.put("userDistrict", session.getAttribute("userDistrict"));
        userMap.put("userCity", session.getAttribute("userCity"));
        userMap.put("userAddress", session.getAttribute("userAddress"));
        return userMap;
    }

    
    
    
}
