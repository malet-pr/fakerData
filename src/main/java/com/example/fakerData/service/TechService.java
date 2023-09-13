package com.example.fakerData.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fakerData.dao.TechnicDAO;
import com.example.fakerData.dto.TechnicDTO;
import com.example.fakerData.model.Technic;

@Service
public class TechService {

	@Autowired
	private TechnicDAO techDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<TechnicDTO> getByPartialNuber(String phrase) {
		List<Technic> entities = techDAO.findByPartialTechNumber(phrase.toUpperCase());
		List<TechnicDTO> result = new ArrayList<>();
		if(!entities.isEmpty()){
			entities.stream().forEach(e -> result.add(modelMapper.map(e,TechnicDTO.class)));
		}
		return result;
	}

}



