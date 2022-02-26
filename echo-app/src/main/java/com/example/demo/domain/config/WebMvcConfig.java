package com.example.demo.domain.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.app.controller.aspect.CommonRequestDataMethodArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo")
public class WebMvcConfig implements WebMvcConfigurer {

	public void coonfigureAsyncSupport(AsyncSupportConfigurer configurer) {
		
		configurer.setDefaultTimeout(5000);
		
	}
	
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new CommonRequestDataMethodArgumentResolver());
	}
}
