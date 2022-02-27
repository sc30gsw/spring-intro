package com.example.demo.domain.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.domain.config.TestConfig;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
class MessageServiceIntegrationTest {
	
	@Autowired
	private MessageService service;

	@Test
	public void testGetMessageByCode() {
		String actualMessage = service.getMessageByCode("greeting");
		assertThat(actualMessage, is("Hello!!"));
	}

}
