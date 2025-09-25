package com.KisanUnnatiBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.KisanUnnatiBackend.dto.LoginAdminDTO;
import com.KisanUnnatiBackend.dto.NewAdminAddDTO;
import com.KisanUnnatiBackend.entity.NewAdminAddEntity;
import com.KisanUnnatiBackend.service.LoginAdminService;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginAdminController {
	@Autowired
	private LoginAdminService loginAdminService;
	
	
	@GetMapping(path= {"/","/index","/login"})
	public String index() {
       return "login";
	}
	
	@GetMapping("/new_admin_form")
	public String addnewAdmin() {
	    return "admin/new_admin_add_form"; 
	}

	@GetMapping("webName")
	public String webName() {
	    return "dashboard"; 
	}

	@GetMapping("cropManagement")
	public String cropManagement(@RequestParam("name") String name, Model model) {
		  model.addAttribute("name", name);
	    return "cropsmanagement/cropManagement"; 
	}
	
	
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginAdminDTO loginAdminDTO,
    		BindingResult bindingResult, Model model,  HttpSession session) {
		/* System.out.println(1); */
		System.out.println(1);
		 List<String> errorList=new ArrayList<>();
		if(bindingResult.hasErrors()) {
			System.out.println(2);
			 List<ObjectError> list=bindingResult.getAllErrors();
			 for(ObjectError error:list) {
				 System.out.println(error.getDefaultMessage());
				 errorList.add(error.getDefaultMessage());
			 }
			 model.addAttribute("errorList",errorList);
			 return "login";
		}else {
			System.out.println(3);
			 NewAdminAddEntity loginAdmin = loginAdminService.loginAdmin(loginAdminDTO.getEmail(), loginAdminDTO.getPassword());
			if(loginAdmin.getEmail() != null && loginAdmin.getName() != null && loginAdmin.getPosition() != 0) {
				model.addAttribute("message", "Succesfull login");
				session.setAttribute("adminEmail", loginAdmin.getEmail());
				session.setAttribute("adminName", loginAdmin.getName());
				session.setAttribute("adminPosition", String.valueOf(loginAdmin.getPosition()));
				session.setAttribute("adminId", String.valueOf(loginAdmin.getId()));
				System.out.println(4);
				return "dashboard";
			}else {
				errorList.add("You are Enter wrong email and password");
				model.addAttribute("errorList", errorList);
			}
			
			
		}
		
            return "login"; // index.jsp
    }
	
	@PostMapping("/adminAdd")
	public String adminAdd(@Valid @ModelAttribute NewAdminAddDTO newAdminAddDTO,
    		BindingResult bindingResult, Model model,  HttpSession session) {
	  System.out.println("1");
		 List<String> errorList=new ArrayList<>();
			if(bindingResult.hasErrors()) {
				 List<ObjectError> list=bindingResult.getAllErrors();
				 for(ObjectError error:list) {
					 System.out.println(error.getDefaultMessage());
					 errorList.add(error.getDefaultMessage());
				 }
				 System.out.println("2");
				 model.addAttribute("errorList",errorList);
			}else {
				if(session.getAttribute("adminId")!=null) {
					String adminId=(String)session.getAttribute("adminId");
					System.out.println("3");
					if(newAdminAddDTO.getPassword().equals(newAdminAddDTO.getConfirmPassword())) {
						try {
							boolean result=loginAdminService.adminNewAdd(newAdminAddDTO, adminId);
							if(result==true) {
								System.out.println("4");
								model.addAttribute("message", "Succesfull add new admin");
							}else {
								model.addAttribute("errorMessage", "You are Enter wrong information, try again");
							}		
						}catch (Exception e) {
							model.addAttribute("errorMessage", "You are Enter wrong information, try again");
						}
					}else {
						model.addAttribute("errorMessage", "Password and Conform Password are not maching, try again");	
					}
				}
			}
		
		return "dashboard"; // This should resolve to addnewAdmin.jsp via view resolver
	}
	
	
}
