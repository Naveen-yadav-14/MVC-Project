package com.sathya.mvc.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
	
	@NotBlank(message = "Product name is required")
    private String proName;

    @NotBlank(message = "Product brand is required")
    private String proBrand;

    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    private double proPrice;

    @NotBlank(message = "Product description is required")
    private String proDescription;

    @NotBlank(message = "Product category is required")
    private String proCategory;
	
	
	

}
