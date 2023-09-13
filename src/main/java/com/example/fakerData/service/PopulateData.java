package com.example.fakerData.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PopulateData {

    private volatile boolean dataPopulated = false;

    @Autowired
    private DataFakerService dataFakerService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // Check if database is populated. If not, populates it before continuing loading the api.
        if (!dataPopulated) {
        	dataFakerService.populateTechnicTable();
        	dataFakerService.populateQuoteTable();
            dataPopulated = true;
        }
    }
	
}
