package com.KisanUnnatiBackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "state_india")
@Data
public class GetAllStateEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "stateId")
	    private int stateId;

	 @Column(name = "stateName", nullable = false, length = 255, unique = true)
	 private String stateName;
}
