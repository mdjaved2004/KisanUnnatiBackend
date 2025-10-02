package com.KisanUnnatiBackend.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KisanUnnatiBackend.dto.UserRegisterDTO;
import com.KisanUnnatiBackend.entity.UserContactdetailsEntity;
import com.KisanUnnatiBackend.entity.UserRegisterLoginEntity;
import com.KisanUnnatiBackend.repo.CropSellerListingRepository;
import com.KisanUnnatiBackend.repo.UserRegisterLoginRepo;

@Service
public class UserRegisterLoginService {
	
	@Autowired
    private UserRegisterLoginRepo userRegisterRepo;
	
	@Autowired
	private  CropSellerListingRepository cropSellerListingRepository;
	
	@Transactional
	public Map<String, Object> registerUser(UserRegisterDTO dto) {
		String state=null, district=null, cityVillage=null;
	    Map<String, Object> responseMap = new HashMap<>();
	    
	    state=dto.getState();
    	district=dto.getDistrict();
    	cityVillage=dto.getCityVillage();
    	
	    //Check duplicate email
	    if (userRegisterRepo.existsByEmail(dto.getEmail())) {
	        throw new RuntimeException("Email already exists");
	    }

	    //Create main user entity
	    UserRegisterLoginEntity userRegEntity = new UserRegisterLoginEntity();
	    userRegEntity.setName(dto.getName());
	    userRegEntity.setEmail(dto.getEmail());
	    userRegEntity.setPassword(dto.getPassword()); // TODO: hash password
	    userRegEntity.setJoiningDate(LocalDate.now());
	    userRegEntity.setPosition(3); // default

	    //Create contact details entity
	    UserContactdetailsEntity contact = new UserContactdetailsEntity();
	    contact.setMobileNumber(dto.getMobileNumber());
	    contact.setStateName(state);
	    contact.setDistrict(district);
	    contact.setCity(cityVillage);
	    contact.setAddress(dto.getFullAddress());

	    //Map both entities
	    contact.setUserRegisterLoginEntity(userRegEntity);
	    userRegEntity.setUserContactId(contact);

	    //Save user
	    UserRegisterLoginEntity savedUser = userRegisterRepo.save(userRegEntity);

	    //Add user to response map
	    responseMap.put("user", savedUser);
	    
	    CropBuyingServiceOnlyGet sellingListget = new CropBuyingServiceOnlyGet(cropSellerListingRepository);
    	List<Object[]> byCity = sellingListget.buyingCropCityVice(state, district, cityVillage);    
    	List<Object[]> byDistrict = sellingListget.buyingCropDistrictVice(state, district, cityVillage);  	
    	List<Object[]> byState =sellingListget.buyingCropStateVice(state, district, cityVillage);
	   
    	responseMap.put("byCity", byCity);
	    responseMap.put("byDistrict", byDistrict);
	    responseMap.put("byState", byState);

	    return responseMap;
	}
	
	
	
	
	
	
 // Login with email AND password check
	   public Map<String, Object> loginUser(String email, String password) {
		   String state=null, district=null, cityVillage=null;
		   Map<String, Object> responseMap = new HashMap<>();
		   
		   Optional<UserRegisterLoginEntity> optionalUser =userRegisterRepo.findByEmailAndPassword(email, password);

	        if (optionalUser.isPresent()) {
	        	UserRegisterLoginEntity userLoginInfo=optionalUser.get();
	        	UserContactdetailsEntity usercontactInfo=userLoginInfo.getUserContactId();
	        	state=usercontactInfo.getStateName();
	        	district=usercontactInfo.getDistrict();
	        	cityVillage=usercontactInfo.getCity();
	        	
	        	responseMap.put("user", userLoginInfo);
	            CropBuyingServiceOnlyGet sellingListget = new CropBuyingServiceOnlyGet(cropSellerListingRepository);
	        	List<Object[]> byCity = sellingListget.buyingCropCityVice(state, district, cityVillage);    
	        	List<Object[]> byDistrict = sellingListget.buyingCropDistrictVice(state, district, cityVillage);  	
	        	List<Object[]> byState =sellingListget.buyingCropStateVice(state, district, cityVillage);
	    	   
	        	responseMap.put("byCity", byCity);
	    	    responseMap.put("byDistrict", byDistrict);
	    	    responseMap.put("byState", byState);
	    	    return responseMap;
	        } else {
	            throw new RuntimeException("Invalid email or password");
	        }
	    }
	   
}
