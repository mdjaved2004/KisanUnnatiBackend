package com.KisanUnnatiBackend.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "crops_Information")
@Data
public class AddNewCropEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cropId")
	private int cropId;

	@Column(name = "cropName", nullable = false, length = 50)
	private String cropName;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
	private AddCategoryEntity categoryId;

	@Column(name = "adminId", nullable = false)
	private int adminId;

	@Column(name = "imageLink", length = 350)
	private String imageLink;

	@Column(name = "imageAddAdminId")
	private int imageAddAdminId;

	@Column(name = "countryId", nullable = false)
	private int country;

	@Column(name = "stateId", nullable = false)
	private int state;

	@Column(name = "deletedDate")
	private LocalDate deletedDate;

	@Column(name = "display", nullable = false)
	private boolean display = true;
 
}

