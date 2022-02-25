package com.example.demo;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.domain.entity.BookResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RestClientBoookApplication {

	private static final int T = 0;

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
			String bookId = "00000000-0000-0000-0000-000000000000";
			
			BookResource resource = restTemplate.getForObject("http://localhost:8080/books/{bookId}", BookResource.class, bookId);
			
			RequestEntity<Void> requestEntity = (RequestEntity<Void>) RequestEntity
					.get(UriComponentsBuilder
							.fromUriString("http://localhost:8080/books/{bookId}")
							.buildAndExpand(bookId)
							.encode()
							.toUri()
							)
					.header("X-Track-Id", UUID.randomUUID().toString())
					.build();
			
			log.info(resource.toString());
			log.info(requestEntity.toString());
		};
	}

}
