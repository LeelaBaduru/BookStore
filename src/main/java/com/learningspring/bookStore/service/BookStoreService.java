package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Book;
import java.util.List;
import java.util.Optional;

// Interface defines the basic CRUD operations

public interface BookStoreService {

    List<Book> getBooks();

    Optional<Book> getBookById(Long id);

/*
    Book insert(Book bookdetails);

    void updateBookDetails(Long id, Book bookdetails);

    void deleteBookDetails(Long Id);
*/
}