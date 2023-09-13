package com.example.fakerData.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.fakerData.service.TechService;

@Configuration
public class TechConfig {
	
	  @Bean
	  public TechService techBean() {
	      return new TechService();
	  }

	  @Bean
	  public ModelMapper modelMapperBean() {
	      return new ModelMapper();
	  }

}
