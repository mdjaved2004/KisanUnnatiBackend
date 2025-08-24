package com.KisanUnnatiBackend.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KisanUnnatiBackend.dto.GetAllCategoryOfCropsDTO;
import com.KisanUnnatiBackend.entity.AddCategoryEntity;
import com.KisanUnnatiBackend.repo.AddCategoryRepo;

@Service
public class AddCategoryService {

    @Autowired
    private AddCategoryRepo addCategoryRepo;
    // add new category
    public boolean newCategoryAdd(@Valid String categoryOfCrops, String adminId) {
        AddCategoryEntity addCategoryEntity = new AddCategoryEntity(); 

        addCategoryEntity.setCategory(categoryOfCrops);
        addCategoryEntity.setAdminId(Integer.parseInt(adminId));   
        addCategoryEntity.setDate(LocalDate.now());

        return addCategoryRepo.save(addCategoryEntity) != null;
    }
    
    public List<GetAllCategoryOfCropsDTO> getAllCategoryOfCrops() {
        return addCategoryRepo.findAllCategories();
    }
}
