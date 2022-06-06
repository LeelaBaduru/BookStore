package com.learningspring.bookStore.controller;

import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookStoreService bookStoreService;

    //The function receives a GET request, processes it and gives back a list of Books as a response.
    @GetMapping({"/{getBooks}"})
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> booksList = bookStoreService.getBooks();
      //  return booksList;
        return new ResponseEntity<>(bookStoreService.getBooks(), HttpStatus.OK);
    }
}
