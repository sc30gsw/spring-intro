package com.example.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate publishedDate;

}
