package com.example.demo.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo")
public class WebMvcConfig implements WebMvcConfigurer {

	public void coonfigureAsyncSupport(AsyncSupportConfigurer configurer) {
		
		configurer.setDefaultTimeout(5000);
		
		//スレッドプールを利用するようにカスタマイズしたTaskExecutorの設定
		configurer.setTaskExecutor((AsyncTaskExecutor) mvcTaskExecutor());
		
	}
	
	@Bean
	public TaskExecutor mvcTaskExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		return executor;
		
	}
}
