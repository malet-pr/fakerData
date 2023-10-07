package com.example.fakerData.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.fakerData.dto.QuoteDTO;
import com.example.fakerData.dto.QuoteRequestDTO;
import com.example.fakerData.dto.QuoteResponseDTO;
import com.example.fakerData.service.QuoteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/quote")
public class QuoteController {
	
	@Autowired
	private QuoteService quoteService;
	
	@GetMapping("/get-by-tech-num")
	public ResponseEntity<List<QuoteDTO>> getByTechNum(@RequestParam String techNum){
		List<QuoteDTO> list = new ArrayList<>();
		list = quoteService.getByTechNumm(techNum);
		return new ResponseEntity<List<QuoteDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/get-by-source")
	public ResponseEntity<List<QuoteDTO>> getBySource(@RequestParam String source){
		List<QuoteDTO> list = new ArrayList<>();
		list = quoteService.getBySource(source);
		return new ResponseEntity<List<QuoteDTO>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/get-by-filters")
	public ResponseEntity<List<QuoteResponseDTO>> getByFilters(@RequestBody QuoteRequestDTO filters){
		List<QuoteResponseDTO> list = new ArrayList<>();
		list = quoteService.getByFilters(filters);
		return new ResponseEntity<List<QuoteResponseDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-sources")
	public ResponseEntity<List<String>> getAllSources(){
		List<String> list = new ArrayList<>();
		list = quoteService.getAllSources();
		return new ResponseEntity<List<String>>(list,HttpStatus.OK);
	}

}
