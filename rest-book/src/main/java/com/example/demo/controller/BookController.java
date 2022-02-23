package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCriteria;
import com.example.demo.domain.entity.BookResource;
import com.example.demo.domain.entity.BookResourceQuery;
import com.example.demo.domain.error.BookResourceNotFoundException;
import com.example.demo.domain.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("books")
public class BookController {

	@Autowired
	private BookService bookService;

	@CrossOrigin(maxAge = 900)
	@GetMapping("/{bookId}")
	public BookResource getBook(@PathVariable String bookId) {

		Book book = bookService.find(bookId);

		if (book == null) {
			throw new BookResourceNotFoundException(bookId);
		}

		BookResource resource = new BookResource();
		resource.setBookId(book.getBookId());
		resource.setName(book.getName());
		resource.setPublishedDate(book.getPublishedDate());

		return resource;
	}

	@PostMapping
	public ResponseEntity<Void> createBook(@Validated @RequestBody BookResource newResource,
			UriComponentsBuilder uriBuilder) {

		Book newBook = new Book();
		newBook.setName(newResource.getName());
		newBook.setPublishedDate(newResource.getPublishedDate());

		Book createdBook = bookService.create(newBook);

		// String resourceUri = "http://localhost:8080/books/" + createdBook.getBookId();
//		URI resourceUri = uriBuilder
//				.path("books/{bookId}")
//				.buildAndExpand(createdBook.getBookId())
//				.encode()
//				.toUri();
		
		URI resourceUri = MvcUriComponentsBuilder.relativeTo(uriBuilder)
				.withMethodCall(
						getBook(createdBook.getBookId())
				).build().encode().toUri();

		//return ResponseEntity.created(URI.create(resourceUri)).build();
		return ResponseEntity.created(resourceUri).build();
	}

	@PutMapping("/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@PathVariable String bookId, @Validated @RequestBody BookResource resource) {

		Book book = new Book();
		book.setBookId(bookId);
		book.setName(resource.getName());
		book.setPublishedDate(resource.getPublishedDate());

		bookService.update(book);
	}

	@DeleteMapping("/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delte(@PathVariable String bookId) {
		bookService.delete(bookId);
	}

	@GetMapping
	public List<BookResource> searchBooks(@Validated BookResourceQuery query) {

		BookCriteria criteria = new BookCriteria();
		criteria.setName(query.getName());
		criteria.setPublishedDate(query.getPublishedDate());

		List<Book> books = bookService.findAllByCriteria(criteria);

		return books.stream().map(book -> {
			BookResource resource = new BookResource();
			resource.setBookId(book.getBookId());
			resource.setName(book.getName());
			resource.setPublishedDate(book.getPublishedDate());
			return resource;
		}).collect(Collectors.toList());
	}
}
