package com.example.fakerData.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.fakerData.service.QuoteService;

@Configuration
public class QuoteConfig {
	
	  @Bean
	  public QuoteService quoteBean() {
	      return new QuoteService();
	  }

	  @Bean
	  public ModelMapper modelMapperBean() {
	      return new ModelMapper();
	  }

}
