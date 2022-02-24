package com.example.demo;

import java.net.URI;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.entity.BookResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RestClientBoookApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientBoookApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			BookResource resource = new BookResource();
			resource.setName("Spring徹底入門");
			resource.setPublishedDate(LocalDate.of(2016, 4, 1));
			URI createdResourceUri = restTemplate.postForLocation("http://localhost:8080/books", resource);
			
			ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:8080/books", resource, Void.class);
			
			HttpStatus httpStatus = responseEntity.getStatusCode();
			HttpHeaders responseHeaders = responseEntity.getHeaders();
			
			log.info(createdResourceUri.toString());
			log.info(responseEntity.toString());
			log.info(httpStatus.toString());
			log.info(responseHeaders.toString());
			log.info(resource.getName());
		};
	}

}
