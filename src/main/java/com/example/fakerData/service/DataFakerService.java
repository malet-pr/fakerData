package com.example.fakerData.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fakerData.dao.QuoteDAO;
import com.example.fakerData.dao.TechnicDAO;
import com.example.fakerData.model.Quote;
import com.example.fakerData.model.Technic;
import net.datafaker.Faker;

@Service
public class DataFakerService {
	
	@Autowired
	private TechnicDAO technicDAO;
	
	@Autowired
	private QuoteDAO quoteDAO;
	
	private static Locale locale = new Locale("es-AR");
	//private static Faker faker = new Faker(locale);
	private static Faker faker = new Faker(locale,new Random(0));
	
	public void populateTechnicTable() {
		for(int i=0; i<10; i++) {
			Technic tech = new Technic();
			tech.setFirstName(faker.name().firstName());
			tech.setLastName(faker.name().lastName());
			boolean techExists = true;
			String techNum = "";
			while(techExists) {
				techNum = faker.expression("#{bothify '##???###', 'true'}");
				Optional<Technic> t = technicDAO.findByTechNumber(techNum);
				if(!t.isPresent()) {
					techExists = false;
				}
			}
			tech.setTechNumber(techNum);
			technicDAO.save(tech);
		}
	}
	
	public void populateQuoteTable(){
		List<Technic> tech = technicDAO.findAll();
		List<String> source = Arrays.asList("Spongebob","Yoda","StrangerThings","Seinfield","Futurama");
	    Random rnd = new Random(); 
	    for(int i = 0; i < 150; i++) {
	    	Quote q = new Quote();
	    	Technic rTech = tech.get(rnd.nextInt(tech.size()));
	    	String rSource = source.get(rnd.nextInt(source.size()));
	    	String quote = getQuote(rSource);
	    	Date date = faker.date().between(getDate(3L,true), getDate(1L,false));
	    	q.setTech(rTech);
	    	q.setDateRecorded(date);
	    	q.setSource(rSource);
	    	q.setText(quote);
	    	quoteDAO.save(q);
	    }
	}
	
	private Date getDate(Long time, boolean month) {
		LocalDateTime ldt;
		if(month) {
			ldt = LocalDateTime.now().minusMonths(time);
		} else {
			ldt = LocalDateTime.now().minusDays(time);
		}
		Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return date;
	}
	
	private String getQuote(String source){
		String quote = "";
		if(source.equalsIgnoreCase("Spongebob")) {
			quote = faker.spongebob().quotes();
		} else if(source.equalsIgnoreCase("Yoda")){
			quote = faker.yoda().quote();
		} else if(source.equalsIgnoreCase("StrangerThings")) {
			quote = faker.strangerThings().quote();
		} else if(source.equalsIgnoreCase("Seinfield")) {
			quote = faker.seinfeld().quote();
		} else if(source.equalsIgnoreCase("Futurama")) {
			quote = faker.futurama().quote();
		} else {
			quote = "N/A";
		}
		return quote;
	}
}

