package com.example.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Book implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String bookId;
	private String name;
	private LocalDate publishedDate;
}
