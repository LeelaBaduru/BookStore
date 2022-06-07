package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.exception.BookNotFoundException;
import com.learningspring.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        List<Book> booksList = bookRepository.findAll();
        return booksList;
    }

    @Override
    public Optional<Book> getBookById(Long id) throws BookNotFoundException {
        return bookRepository.findById(id);
    }

    @Override
    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
