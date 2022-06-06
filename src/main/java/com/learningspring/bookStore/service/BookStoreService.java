package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Book;
import java.util.List;

// Interface defines the basic CRUD operations

public interface BookStoreService {

    List<Book> getBooks();
/*
    Book getBookById(Long id);

    Book insert(Book bookdetails);

    void updateBookDetails(Long id, Book bookdetails);

    void deleteBookDetails(Long Id);
*/
}
