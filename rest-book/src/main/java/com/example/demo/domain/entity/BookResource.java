package com.example.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String bookId;
	@NotNull
	private String name;
	private List<String> authors;
	@NotNull
	private LocalDate publishedDate;
	private BookPublisher publisher;
	
	@Data
	public static class BookPublisher implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private String name;
		private String tel;
		
	}
}
