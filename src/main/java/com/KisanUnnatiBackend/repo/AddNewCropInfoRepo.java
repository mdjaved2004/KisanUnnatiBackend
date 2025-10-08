package com.KisanUnnatiBackend.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.AddNewCropInfoEntity;

@Repository
public interface AddNewCropInfoRepo extends JpaRepository<AddNewCropInfoEntity, Integer>{

}
