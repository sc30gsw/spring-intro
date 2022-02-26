package com.example.demo.domain.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo")
public class WebMvcConfig implements WebMvcConfigurer {

	public void coonfigureAsyncSupport(AsyncSupportConfigurer configurer) {
		
		configurer.setDefaultTimeout(5000);
		
	}
}
