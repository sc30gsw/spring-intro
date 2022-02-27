package com.example.demo.domain.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
class MessageServiceTest {
	
	@InjectMocks
	private MessageService service;
	
	@Mock
	private MessageSource mockMessageSource;

	@Test
	public void testGetMessageByCode() {
		
		doReturn("Hello!!").when(mockMessageSource).getMessage("greeting", null, Locale.getDefault());
		
		//テストの実施
		String actualMessage = service.getMessageByCode("greeting");
		assertThat(actualMessage, is("Hello!!"));
		
	}

}
