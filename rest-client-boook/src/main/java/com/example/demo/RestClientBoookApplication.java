package com.example.demo;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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
			
			RequestEntity<BookResource> requestEntity = (RequestEntity<BookResource>) RequestEntity
					.post(URI.create("http://localhost:8080/books"))
					.contentType(MediaType.APPLICATION_JSON)
					.header("X-Track-Id", UUID.randomUUID().toString())
					.body(resource);
			
			ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
			log.info(createdResourceUri.toString());
			log.info(requestEntity.toString());
			log.info(responseEntity.toString());
			log.info(resource.getName());
		};
	}

}
