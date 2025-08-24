package com.KisanUnnatiBackend.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.AddNewCropEntity;

@Repository
public interface AddNewCropRepo extends JpaRepository<AddNewCropEntity, Integer>{

}
