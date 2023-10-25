package com.jspider.cardekho_case_study_restapp.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CarPOJO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String carName;
	private String brandName;
	private String fuelType;
	private double price;
	
}
