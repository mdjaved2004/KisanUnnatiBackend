package com.KisanUnnatiBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "admin_table")
@Data
public class NewAdminAddEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;

    @Column(name = "mobileNumber", nullable = false, length = 12)
    private String mobileNumber;

    @Column(name = "password", nullable = false, length = 35)
    private String password;

    @Column(name = "joiningDate", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "position", nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 3")
    private byte position=3;

    @Column(name = "joinedAdminId", nullable = false)
    private int joinedAdminId;
}
