package com.example.demo.domain.entity.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ApiError implements Serializable {
	
	@Data
	private static class Detail implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private final String target;
		private final String message;
		private Detail(String target, String message) {
			this.target = target;
			this.message = message;
		}
	}

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	@JsonProperty("documentaion_url")
	private String documentationUrl;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final List<Detail> details = new ArrayList<>();
	
	public void addDetail(String target, String message) {
		details.add(new Detail(target, message));
	}
	
	public List<Detail> getDetails() {
		return details;
	}
}
