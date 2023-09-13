package com.example.fakerData.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fakerData.model.Technic;

@Repository
public interface TechnicDAO extends JpaRepository<Technic, Long>{
	
	long count();
	boolean existsById(Long id);
	Optional<Technic> findById(Long id);
	Optional<Technic> findByTechNumber(String number);
	@Query("select t from Technic t where t.techNumber like %:num%")
	List<Technic> findByPartialTechNumber(@Param("num") String phrase);
	@Query("select t from Technic t where t.firstName like %:fname%")
	List<Technic> findByFirstNameLike(@Param("fname") String phrase);
	@Query("select t from Technic t where t.lastName like %:lname%")
	List<Technic> findByLastNameLike(@Param("lname") String phrase);
	List<Technic> findAll(Sort sort);
	void deleteById(Long id);
	@SuppressWarnings("unchecked")
	Technic save(Technic technic);

}
