package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
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
			BookResource resource = restTemplate.getForObject("http://localhost:8080/books/00000000-0000-0000-0000-000000000000", BookResource.class);
			log.info(resource.toString());
			log.info(resource.getName());
		};
	}

}
