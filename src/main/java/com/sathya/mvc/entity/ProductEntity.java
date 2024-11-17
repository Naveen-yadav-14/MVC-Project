package com.sathya.mvc.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Naveen")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long proId;
	private String proName;
	private double proPrice;
	private double disPrice;
	
	private String proBrand;
	private String proDescription;
	private String proCategory;
	
	private LocalDate createdAt;
	private String createdBy;
	
	
	

}
