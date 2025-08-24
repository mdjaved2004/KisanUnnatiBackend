package com.KisanUnnatiBackend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.KisanUnnatiBackend.dto.AddNewCropDTO;
import com.KisanUnnatiBackend.dto.GetAllCategoryOfCropsDTO;
import com.KisanUnnatiBackend.entity.GetAllStateEntity;
import com.KisanUnnatiBackend.service.AddCategoryService;
import com.KisanUnnatiBackend.service.AddNewCropService;
import com.KisanUnnatiBackend.service.GetAllStateservice;

@Controller
public class AddNewCropController {
	 
	@Autowired
	private AddCategoryService addCategoryService;
	
	@Autowired
    private GetAllStateservice getAllStateservice;
	
	@Autowired
	private AddNewCropService addNewCropService;
	
	@GetMapping("/addNewCropsform")
    public String addNewCropsform(Model model){
        List<GetAllCategoryOfCropsDTO> allCategoryOfCrops = addCategoryService.getAllCategoryOfCrops();
        model.addAttribute("categoryList", allCategoryOfCrops);
    
		List<GetAllStateEntity> allStates =getAllStateservice.getAllStates();
        
		model.addAttribute("stateAll", allStates);
        return "cropsmanagement/addNewCrops";
    }
 
	
	
	
	@InitBinder
	public void initBinding(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("image_input");
		
	}
	
    @PostMapping("/addNewCrop")
    public String addNewCropInsert(@Valid @ModelAttribute AddNewCropDTO addNewCropDTO,
    		BindingResult bindingResult, RedirectAttributes redirectAttributes,@RequestParam("image_input") MultipartFile multipartFile,  HttpSession session, Model model){
    	 List<String> errorList=new ArrayList<>();
 		if(bindingResult.hasErrors()) {
 			System.out.println(2);
 			 List<ObjectError> list=bindingResult.getAllErrors();
 			 for(ObjectError error:list) {
 				 System.out.println(error.getDefaultMessage());
 				 errorList.add(error.getDefaultMessage());
 			 }
 			 model.addAttribute("errorList",errorList);
 			 return "cropsmanagement/addNewCrops";
 		}else {
 			try {
 				String result=addNewCropService.insertAddNewCrop(addNewCropDTO, multipartFile, session);
 			}catch (Exception e) {
				e.printStackTrace();
			}
 			
 			
 		}
    	
        return "redirect:/cropManagement";
    }
	

}
