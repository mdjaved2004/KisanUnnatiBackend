package com.KisanUnnatiBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.GetAllStateEntity;

@Repository
public interface GetAllStateRepo extends JpaRepository<GetAllStateEntity, Integer> {
}
