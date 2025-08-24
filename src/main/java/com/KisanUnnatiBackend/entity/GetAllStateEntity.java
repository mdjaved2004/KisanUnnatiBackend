package com.KisanUnnatiBackend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "state_india")
@Data
public class GetAllStateEntity {

    @Id
    private int stateId;

    private String stateName;
}
