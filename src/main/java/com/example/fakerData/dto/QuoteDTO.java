package com.example.fakerData.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.fakerData.model.Technic;

import lombok.Data;

@Data
public class QuoteDTO implements Serializable {
	
	private static final long serialVersionUID = -8436145912544049074L;
	
	private Technic tech;
	private String source;
	private String text;
	private Date dateRecorded;

}
