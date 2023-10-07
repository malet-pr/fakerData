package com.example.fakerData.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.fakerData.dto.TechnicDTO;
import com.example.fakerData.service.TechService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/technic")
public class TechicController {
	
	@Autowired
	private TechService techService;

	@GetMapping("/get-by-partial-number")
	public ResponseEntity<List<TechnicDTO>> getByPartialNumber(@RequestParam String phrase){
		List<TechnicDTO> list = new ArrayList<>();
		list = techService.getByPartialNuber(phrase);
		return new ResponseEntity<List<TechnicDTO>>(list,HttpStatus.OK);
	}
	
}
