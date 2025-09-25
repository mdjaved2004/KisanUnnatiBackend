package com.KisanUnnatiBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.CropSellerListingEntity;



@Repository
public interface CropSellerListingRepository extends JpaRepository<CropSellerListingEntity, Long> {
}

