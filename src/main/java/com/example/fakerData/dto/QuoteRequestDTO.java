package com.example.fakerData.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class QuoteRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 2203976077291287153L;

	String techNum;
	List<SourceDTO> sources;
	String text;
	//@JsonFormat(pattern = "dd-MM-yyyy") 
	Date dateFrom;
	//@JsonFormat(pattern = "dd-MM-yyyy") 
	Date dateTo;
	
}
