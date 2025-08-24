package com.KisanUnnatiBackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KisanUnnatiBackend.dto.NewAdminAddDTO;
import com.KisanUnnatiBackend.entity.NewAdminAddEntity;
import com.KisanUnnatiBackend.repo.AdminAddInformationRepo;

import javax.validation.Valid;
import java.time.LocalDate;

@Service
public class LoginAdminService {

    @Autowired
    private AdminAddInformationRepo adminAddInformationRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Login check
    public NewAdminAddEntity loginAdmin(String email, String password) {
    	System.out.println(5);
    	
        return adminAddInformationRepo.findByEmailAndPassword(email, password);
    }

    // Add new admin
    public boolean adminNewAdd(@Valid NewAdminAddDTO newAdminAddDTO, String adminId) {
		NewAdminAddEntity entity =modelMapper.map(newAdminAddDTO, NewAdminAddEntity.class);
		entity.setJoiningDate(LocalDate.now());
		entity.setJoinedAdminId(Integer.parseInt(adminId));
		entity.setPosition(Byte.parseByte("2"));
		boolean isSaved = adminAddInformationRepo.save(entity) != null;
		System.out.println(isSaved);
		return isSaved;
	}
}
