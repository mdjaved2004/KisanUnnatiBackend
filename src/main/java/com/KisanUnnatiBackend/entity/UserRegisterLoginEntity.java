package com.KisanUnnatiBackend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "register")
public class UserRegisterLoginEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "mobile_number", length = 10, nullable = true)
    private String mobileNumber;

 
    @Column(name = "stateName", length = 255)
    private String stateName; 

    @Column(name = "address",length = 150, nullable = true)
    private String address;
    
    @Column(name = "position", nullable = false)
    private int position = 3;
}
