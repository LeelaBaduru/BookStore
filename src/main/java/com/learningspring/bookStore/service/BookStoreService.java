package com.learningspring.bookStore.service;

import com.learningspring.bookStore.dto.Author_Book;
import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

// Interface defines the basic CRUD operations

public interface BookStoreService {

    List<Book> getBooks();

    Optional<Book> getBookById(Long id);

    Book addBook(Book newBook);

    void deleteBook(Long Id);

    List<Book> getBookByAuthorId(Long authorId);

    List<Object> getBookNameListByAuthorId(Long authorId) throws ResourceNotFoundException;

    List<Author_Book> getBookTitleAuthorName();
}
