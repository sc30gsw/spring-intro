package com.example.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class BookResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bookId;
	private String name;
	private List<String> authors;
	private LocalDate publishedDate;
	private BookPublisher publisher;
	
	@Data
	public static class BookPublisher implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String name;
		private String tel;
		
	}
}
