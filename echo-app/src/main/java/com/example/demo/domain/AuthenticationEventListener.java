package com.example.demo.domain;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationEventListener {

	@EventListener
	public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
		log.info("Bad credentials is detected. username : {}", event.getAuthentication().getName());
	}
	
}
