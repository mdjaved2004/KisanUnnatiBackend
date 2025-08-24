package com.KisanUnnatiBackend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KisanUnnatiBackend.dto.AddNewCropDTO;
import com.KisanUnnatiBackend.entity.AddCategoryEntity;
import com.KisanUnnatiBackend.entity.AddNewCropEntity;
import com.KisanUnnatiBackend.repo.AddNewCropRepo;



@Service
public class AddNewCropService {
	
	@Autowired
	private AddNewCropRepo addNewCropRepo;
    
	@Autowired
	private ModelMapper modelMapper;
	
	private String result="";
	
	@Transactional(rollbackOn = Exception.class)
	public String insertAddNewCrop(@Valid AddNewCropDTO addNewCropDTO, MultipartFile multipartFile, HttpSession session) throws Exception {
	      
	    AddNewCropEntity entity = modelMapper.map(addNewCropDTO, AddNewCropEntity.class);

	    AddCategoryEntity category = new AddCategoryEntity();
	    category.setCategoryId(addNewCropDTO.getCategoryId());
	    entity.setCategoryId(category);

	    String fileName = multipartFile.getOriginalFilename(); 			
	    entity.setImageLink(fileName);
	    entity.setDate(LocalDate.now());

	    int adminId = Integer.parseInt((String) session.getAttribute("adminId"));
	    entity.setAdminId(adminId);
	    entity.setImageAddAdminId(adminId);

	    byte[] bytes = multipartFile.getBytes();
	    String rootPath = System.getProperty("user.dir");
	    String path =rootPath+File.separator+"src" + File.separator + "main" + File.separator +
                "webapp" + File.separator + "assets" + File.separator + "imageOfCrops" +File.separator + fileName;
	    FileOutputStream fileOutputStream = new FileOutputStream(path);
		
		  fileOutputStream.write(bytes); fileOutputStream.close();
		  
		  addNewCropRepo.save(entity);
		 
	    return "success";
	}

}