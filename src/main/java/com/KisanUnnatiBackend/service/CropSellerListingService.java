package com.KisanUnnatiBackend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KisanUnnatiBackend.dto.CropSellerListingDTO;
import com.KisanUnnatiBackend.entity.CropSellerListingEntity;
import com.KisanUnnatiBackend.repo.CropSellerListingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropSellerListingService {

	
	@Autowired
	private ModelMapper modelMapper;
	
    private final CropSellerListingRepository cropListingRepository;

    public String addCrop(CropSellerListingDTO cropDTO, MultipartFile multipartFile, HttpSession session) throws Exception {
    	CropSellerListingEntity entity = modelMapper.map(cropDTO, CropSellerListingEntity.class);
    	 entity.setDate(LocalDate.now());
    	 
    	 String fileName = multipartFile.getOriginalFilename(); 	 
    	 entity.setImagePath(fileName);
    	 
    	 entity.setTotalQuantity(cropDTO.getQuantity());
    	 
       	 
    	 System.out.println(entity.getAddress());
    	 System.out.println(entity.getContactNumber());
    	 System.out.println(entity.getCropName());
    	 System.out.println(entity.getDescription());
    	 System.out.println(entity.getImagePath());
    	 System.out.println(entity.getSellerEmail());
    	 System.out.println(entity.getPrice());
    	 System.out.println(entity.getQuantity());
    	 System.out.println(entity.getTotalQuantity());
    	 System.out.println(entity.getDate());
    	 
    	byte[] bytes = multipartFile.getBytes();
 	    String rootPath = System.getProperty("user.dir");
 	    String path =rootPath+File.separator+"src" + File.separator + "main" + File.separator +
                 "webapp" + File.separator + "assets" + File.separator +"CropSellerListingImage"+File.separator + fileName;
 	    FileOutputStream fileOutputStream = new FileOutputStream(path);
 		
 		fileOutputStream.write(bytes);
 		fileOutputStream.close();

 	   CropSellerListingEntity save = cropListingRepository.save(entity);
	   return "successfull to add";
 	   
        
    }

    public List<CropSellerListingEntity> getAllCrops() {
        return cropListingRepository.findAll();
    }
}
