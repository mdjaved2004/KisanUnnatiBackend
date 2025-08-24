package com.KisanUnnatiBackend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class AddNewCropDTO {
	
	@NotNull(message = "Category ID is required.")
	@Min(value = 1, message = "Category ID must be greater than 0.")
	private int categoryId;

	@NotBlank(message = "Crop name must not be blank.")
	@Size(max = 50, message = "Crop name must not exceed 50 characters.")
	@Pattern(
	    regexp = "^[A-Za-z0-9 ()]+$",
	    message = "Crop name can only contain letters, numbers, spaces, and parentheses."
	)
	private String cropName;


	@NotNull(message = "Price is required.")
	@Min(value = 0, message = "Price must be zero or positive.")
	private int price;

	@NotNull(message = "Country ID is required.")
	@Min(value = 1, message = "Country ID must be greater than 0.")
	private int country;

	@NotNull(message = "State ID is required.")
	@Min(value = 1, message = "State ID must be greater than 0.")
	private int state;
}
