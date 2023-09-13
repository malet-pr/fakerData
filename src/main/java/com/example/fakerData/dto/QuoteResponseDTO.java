package com.example.fakerData.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class QuoteResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -343420291033568864L;
	
	private String techNumber;
	private String firstName;
	private String lastName;
	private String source;
	private String text;
	private Date dateRecorded;

}
