package com.example.fakerData.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class TechnicDTO implements Serializable {

	private static final long serialVersionUID = 5562193484573253082L;

	private String techNumber;
	private String firstName;
	private String lastName;
	
	
}
