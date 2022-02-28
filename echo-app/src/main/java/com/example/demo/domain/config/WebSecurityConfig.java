package com.example.demo.domain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/resources/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/login").permitAll()
				.anyRequest().authenticated();
		
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate")
				.usernameParameter("uid")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/menu")
				.failureUrl("/loginFailure");
	}
}
