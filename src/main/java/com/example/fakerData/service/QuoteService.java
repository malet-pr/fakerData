package com.example.fakerData.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fakerData.dao.QuoteDAO;
import com.example.fakerData.dao.TechnicDAO;
import com.example.fakerData.dto.EntityToResponseDto;
import com.example.fakerData.dto.QuoteDTO;
import com.example.fakerData.dto.QuoteRequestDTO;
import com.example.fakerData.dto.QuoteResponseDTO;
import com.example.fakerData.model.Quote;
import com.example.fakerData.model.Technic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuoteService {
	
	@Autowired
	private TechnicDAO techDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EntityToResponseDto entityToDto;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<QuoteDTO> getByTechNumm(String techNum) {
		List<QuoteDTO> result = new ArrayList<>();
		Optional<Technic> tech = techDAO.findByTechNumber(techNum);
		if(tech.isPresent()) {
			List<Quote> entities = quoteDAO.findByTech(tech.get());
			if(!entities.isEmpty()) {
				entities.stream().forEach(e -> result.add(modelMapper.map(e,QuoteDTO.class)));
			}
		}
		return result;
	}

	public List<QuoteDTO> getBySource(String source) {
		List<QuoteDTO> result = new ArrayList<>();
		List<Quote> entities = quoteDAO.findBySource(source);
		if(!entities.isEmpty()) {
			entities.stream().forEach(e -> result.add(modelMapper.map(e,QuoteDTO.class)));
		}
		return result;
	}

	public List<QuoteResponseDTO> getByFilters(QuoteRequestDTO filters) {
		List<QuoteResponseDTO> result = new ArrayList<>();
		List<Quote> entities = new ArrayList<>();
		try{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Quote> cq = cb.createQuery(Quote.class);
			Root<Quote> root = cq.from(Quote.class);
			Predicate predicate = cb.conjunction();
			if(!filters.getTechNum().isBlank()) {
				Predicate p = cb.equal(root.get("tech").get("techNumber"),filters.getTechNum());
				predicate = cb.and(predicate,p);
			}
			if(filters.getSources() != null && !filters.getSources().isEmpty()) {
				In<String> inClause = cb.in(root.get("source"));
				for (String source : filters.getSources()) {
				    inClause.value(source);
				}
				predicate = cb.and(predicate,inClause);			
			}
			if(!filters.getText().isBlank()) {
				Predicate p = cb.like(cb.upper(root.get("text")), "%"+filters.getText().toUpperCase()+"%");
				predicate = cb.and(predicate,p);
			}
			if(filters.getDateFrom() != null) {
				Predicate p = cb.greaterThanOrEqualTo(root.get("dateRecorded"),filters.getDateFrom());
				predicate = cb.and(predicate,p);
			}
			if(filters.getDateTo() != null) {
				Predicate p = cb.lessThanOrEqualTo(root.get("dateRecorded"),filters.getDateTo());
				predicate = cb.and(predicate,p);
			}
			cq.select(root);
			cq.orderBy(
				cb.asc(root.get("source")),
				cb.desc(root.get("dateRecorded"))
			);
			cq.select(root).where(predicate).distinct(true);
			entities = em.createQuery(cq).getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if(!entities.isEmpty()) {
			result = entityToDto.entityListToDtoList(entities);
		}
		return result;
	}

	public List<String> getAllSources() {
		List<String> result = new ArrayList<>();
		result = quoteDAO.getAllSources();
		return result;
	}

}

