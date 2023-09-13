package com.example.fakerData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="tech")
@Data
public class Technic {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "tech_id")
	private Long id;
	private String techNumber;
	private String firstName;
	private String lastName;

}
