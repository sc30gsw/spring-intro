package com.example.demo.domain.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextHierarchy({
	@ContextConfiguration(classes = {WebSecurityConfig.class})
})
@WebAppConfiguration
class WebSecurityTest {
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUpMockMvc() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	@WithMockUser
	@Test
	public void testCreateAccount() throws Exception {
		mockMvc.perform(post("/login").with(csrf()))
			.andExpect(status().isOk());
	}

}
