package com.KisanUnnatiBackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.KisanUnnatiBackend.entity.NewAdminAddEntity;

@Repository
public interface AdminAddInformationRepo extends JpaRepository<NewAdminAddEntity, Integer> {
	 NewAdminAddEntity findByEmailAndPassword(String email, String password);
}

