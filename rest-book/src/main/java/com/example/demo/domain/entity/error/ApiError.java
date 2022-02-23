package com.example.demo.domain.entity.error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ApiError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	@JsonProperty("documentaion_url")
	private String documentationUrl;
}
