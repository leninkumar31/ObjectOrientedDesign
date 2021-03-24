package com.udemy.springboot.basics.springbootdemo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return Arrays.asList(new Book(1l, "Half Lion: How P.V.Narasimha Rao transformed India", "Vinay Sitapati"));
	}
}
