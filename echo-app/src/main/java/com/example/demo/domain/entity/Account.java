package com.example.demo.domain.entity;

import lombok.Data;

@Data
public class Account {

	private String username;
	private String password;
	
	public boolean isEnabled() {
		return true;
	}

}
