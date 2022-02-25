package com.example.demo.domain.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountCreateForm implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	@NotBlank
	private String password;
}
