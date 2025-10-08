package com.KisanUnnatiBackend.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KisanUnnatiBackend.dto.AddNewCropInfoDTO;
import com.KisanUnnatiBackend.entity.AddCategoryEntity;
import com.KisanUnnatiBackend.entity.AddNewCropInfoEntity;
import com.KisanUnnatiBackend.repo.AddNewCropInfoRepo;



@Service
public class AddNewCropInfoService {
	
	@Autowired
	private AddNewCropInfoRepo addNewCropInfoRepo;
    
	@Autowired
	private ModelMapper modelMapper;
	
	private String result="";
	
	@Transactional(rollbackOn = Exception.class)
	public boolean insertAddNewCrop(@Valid AddNewCropInfoDTO addNewCropDTO, MultipartFile imageFile, MultipartFile textFile, HttpSession session) throws Exception {
		boolean valueCheck=false; 
		String rootPath,path,originalImagePath,imageFilePathWitchSave,originalTextFilePath,textFilePathWitchSave;
		int adminId =(Integer)session.getAttribute("adminId");
		System.out.println("===============3================");
		rootPath = System.getProperty("user.dir");
		path =rootPath+File.separator+"src" + File.separator + "main" + File.separator +
				"webapp" + File.separator + "assets" + File.separator + "CropInformation";
	   
		originalImagePath=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + imageFile.getOriginalFilename();
		originalTextFilePath=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) + textFile.getOriginalFilename(); 			
		
		imageFilePathWitchSave=path+ File.separator + "ImageOfCrops" + File.separator +originalImagePath;
		textFilePathWitchSave=path+ File.separator + "CropInfoFiles" + File.separator +originalTextFilePath;
		
		AddNewCropInfoEntity entity = modelMapper.map(addNewCropDTO, AddNewCropInfoEntity.class);
		System.out.println("===============4================");
	    AddCategoryEntity category = new AddCategoryEntity();
	    category.setCategoryId(addNewCropDTO.getCategoryId());
	    entity.setCategoryId(category);

	    entity.setImageLink(originalImagePath);
	    entity.setFileLink(originalTextFilePath);
	    entity.setDate(LocalDate.now());
	    entity.setAdminId(adminId);
	    entity.setImageAddAdminId(adminId);
	    entity.setFileAddAdminId(adminId);
	    
	    String fileNameText=textFile.getOriginalFilename();	    
	    if(fileNameText.substring(fileNameText.lastIndexOf(".") + 1).equalsIgnoreCase("txt")) {
		    byte[] bytes = imageFile.getBytes();
		    FileOutputStream fileOutputStream = new FileOutputStream(imageFilePathWitchSave);
		    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		    bufferedOutputStream.write(bytes);
		    bufferedOutputStream.flush();
		    
		    BufferedReader fileReader = new BufferedReader(new InputStreamReader(textFile.getInputStream(), StandardCharsets.UTF_8));
		    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(textFilePathWitchSave));
	        String line;
	        while ((line = fileReader.readLine()) != null) {
	        	fileWriter.write(line);
	        	fileWriter.newLine();
	        }
	        fileWriter.flush();
	        
	        valueCheck=addNewCropInfoRepo.save(entity)!=null;
        }else {
        	valueCheck=false;
        }
		
    	System.out.println("===============2================");
		  
		 
	    return valueCheck;
	}

}