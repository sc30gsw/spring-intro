package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestOperations;

import com.example.demo.domain.entity.BookResource;

@SpringBootApplication
public class RestTemplateBookApplication {
	
	@Autowired
	private static RestOperations restOperations;

	public static void main(String[] args) {
		BookResource resource = restOperations.getForObject("http://localhost:8080/books/00000000-0000-0000-0000-000000000000", BookResource.class);
		System.out.print(resource.getName());
		
		SpringApplication.run(RestTemplateBookApplication.class, args);
	}

}
