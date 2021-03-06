package com.example.demo.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Book;
import com.example.demo.domain.entity.BookCriteria;
import com.example.demo.domain.entity.BookResource.BookPublisher;

@Service
public class BookService {

	private final Map<String, Book> bookRepository = new ConcurrentHashMap<>();

	@PostConstruct
	public void loadDummyData() {
		Book book = new Book();
		BookPublisher publisher = new BookPublisher();
		List<String> authors = new ArrayList<>();
		authors.add("著者A");
		authors.add("著者B");
		book.setBookId("00000000-0000-0000-0000-000000000000");
		book.setName("書籍名");
		book.setAuthors(authors);
		book.setPublishedDate(LocalDate.of(2010, 4, 20));
		publisher.setName("翔泳社");
		publisher.setTel("03-xxxx-xxxx");
		book.setPublisher(publisher);
		bookRepository.put(book.getBookId(), book);
	}

	public Book find(String bookId) {
		Book book = bookRepository.get(bookId);
		return book;
	}

	public Book create(Book book) {
		String bookId = UUID.randomUUID().toString();
		book.setBookId(bookId);
		bookRepository.put(bookId, book);
		return book;
	}

	public Book update(Book book) {
		return bookRepository.put(book.getBookId(), book);
	}

	public Book delete(String bookId) {
		return bookRepository.remove(bookId);
	}

	public List<Book> findAllByCriteria(BookCriteria criteria) {
		return bookRepository.values().stream()
				.filter(book -> 
					(criteria.getName() == null 
						|| book.getName().contains(criteria.getName())) &&
					(criteria.getPublishedDate() == null 
						|| book.getPublishedDate().equals(criteria.getPublishedDate())))
				.sorted((o1, o2) -> 
					o1.getPublishedDate().compareTo(o2.getPublishedDate()))
				.collect(Collectors.toList());
	}

}
