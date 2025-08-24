package com.KisanUnnatiBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KisanUnnatiBackend.entity.GetAllStateEntity;
import com.KisanUnnatiBackend.repo.GetAllStateRepo;


@Service
public class GetAllStateservice {

    @Autowired
    private GetAllStateRepo stateRepo;

    public List<GetAllStateEntity> getAllStates() {
        return stateRepo.findAll();
    }
}
