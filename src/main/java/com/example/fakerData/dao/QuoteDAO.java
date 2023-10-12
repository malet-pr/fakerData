package com.example.fakerData.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.fakerData.model.Quote;
import com.example.fakerData.model.Technic;

public interface QuoteDAO extends JpaRepository<Quote, Long>{
	
	long count();
	Optional<Quote> findById(Long id);
	List<Quote> findByTech(Technic tech);
	List<Quote> findBySource(String source);
	@Query("select q from Quote q where q.text like %:phrase%")
	List<Quote> findByTextLike(@Param("phrase") String phrase);
	List<Quote> findByDateRecorded(Date date);
	@Query("select q from Quote q where q.dateRecorded between :d1 and :d2")
	List<Quote> getByDateBetween(@Param("d1") Date d1,@Param("d2") Date d2);
	List<Quote>  findAll(Sort sort);
	void deleteById(Long id);
	@SuppressWarnings("unchecked")
	Quote save(Quote quote);
	@Query("select distinct q.source from Quote q")
	List<String> getAllSources();

}
