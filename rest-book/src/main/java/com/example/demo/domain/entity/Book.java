package com.example.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.domain.entity.BookResource.BookPublisher;

import lombok.Data;

@Data
public class Book implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private String bookId;
	
	@NotNull
	private String name;
	private List<String> authors;
	@NotNull
	private LocalDate publishedDate;
	private BookPublisher publisher;
	public void setPublisher(BookPublisher publisher) {
		this.publisher = publisher;
		
	}
	
}
