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
import com.example.fakerData.dto.SourceDTO;
import com.example.fakerData.service.QuoteService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/quote")
@Slf4j
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
		log.info(list.get(0).getDateRecorded().toString());
		return new ResponseEntity<List<QuoteResponseDTO>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/get-all-sources")
	public ResponseEntity<List<SourceDTO>> getAllSources(){
		List<SourceDTO> list = new ArrayList<>();
		list = quoteService.getAllSources();
		return new ResponseEntity<List<SourceDTO>>(list,HttpStatus.OK);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<QuoteResponseDTO>> getAll(){
		List<QuoteResponseDTO> list = new ArrayList<>();
		list = quoteService.findAll();
		return new ResponseEntity<List<QuoteResponseDTO>>(list,HttpStatus.OK);
	}

	@GetMapping("/get-all-paginated")
	public ResponseEntity<List<QuoteResponseDTO>> getAllPaginated(@RequestParam(defaultValue = "0") Integer pageNo,
																  @RequestParam(defaultValue = "10") Integer pageSize,
																  @RequestParam(defaultValue = "id") String sortBy){
		List<QuoteResponseDTO> list = new ArrayList<>();
		list = quoteService.getAllPaginated(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<QuoteResponseDTO>>(list,HttpStatus.OK);
	}

}
