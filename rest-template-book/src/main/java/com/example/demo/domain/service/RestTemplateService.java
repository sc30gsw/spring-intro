package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class RestTemplateService {

	@Autowired
	private RestOperations restOperations;
	
	public void getResource() {
		
		String resource = restOperations.getForObject("http://localhost:8080/books/00000000-0000-0000-0000-000000000000", String.class);
		
		
	}
	
}
