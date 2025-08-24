package com.KisanUnnatiBackend.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.KisanUnnatiBackend.dto.AddCategoryDTO;
import com.KisanUnnatiBackend.service.AddCategoryService;
import com.KisanUnnatiBackend.service.GetAllStateservice;

@Controller
public class CropManagementController {

	@Autowired
	private AddCategoryService addCategoryService;
	
	@Autowired
    private GetAllStateservice getAllStateservice;
	
    @GetMapping("/addCategoryForm")
    public String addCategory(){
        return "cropsmanagement/category_add_form";
    }

    @PostMapping("/addNewCategory")
    public String adminAdd(@Valid @ModelAttribute AddCategoryDTO addCategoryDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,  HttpSession session) {
    	if(bindingResult.hasErrors()) {
    		redirectAttributes.addFlashAttribute("errorMessage",bindingResult.getAllErrors());
			 return "cropsmanagement/cropManagement";
		}else {
			if(session.getAttribute("adminId")!=null) {
				String adminId=(String)session.getAttribute("adminId");
				boolean result =addCategoryService.newCategoryAdd(addCategoryDTO.getCategory(), adminId);
				if(result==true) {
					redirectAttributes.addFlashAttribute("message", "Successful new  category of crops add");
				}else {
					redirectAttributes.addFlashAttribute("errorMessage", "Somthing went wrong, try again");
				}
			}
		}
        return "redirect:/cropManagement"; 
    }
}