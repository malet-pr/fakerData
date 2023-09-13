package com.example.fakerData.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import com.example.fakerData.model.Quote;


@Component
public class EntityToResponseDto {
	
	public QuoteResponseDTO entityToResponseDto(Quote entity) {
		QuoteResponseDTO dto = new QuoteResponseDTO();
		dto.setTechNumber(entity.getTech().getTechNumber());
		dto.setFirstName(entity.getTech().getFirstName());
		dto.setLastName(entity.getTech().getLastName());
		dto.setSource(entity.getSource());
		dto.setDateRecorded(entity.getDateRecorded());
		dto.setText(entity.getText());
		return dto;
	}
	
	public List<QuoteResponseDTO> entityListToDtoList(List<Quote> entities){
		List<QuoteResponseDTO> dtoList = new ArrayList<>();
		entities.stream().forEach(e -> dtoList.add(this.entityToResponseDto(e)));
		
		return dtoList;
	}
	
	Comparator<QuoteResponseDTO> byLastName = Comparator.comparing(QuoteResponseDTO::getLastName);
	Comparator<QuoteResponseDTO> byDateRecorded = Comparator.comparing(QuoteResponseDTO::getDateRecorded);

}

