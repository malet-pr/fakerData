package com.example.fakerData.model;

import java.util.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="quote")
@Data
public class Quote {
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "quote_id")
	private Long id;
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "tech_id")
	private Technic tech;
	private String source;
	private String text;
	private Date dateRecorded;

}

