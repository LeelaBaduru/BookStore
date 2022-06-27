package com.learningspring.bookStore.service;

import com.learningspring.bookStore.dto.Author_Book;
import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.exception.ResourceNotFoundException;
import com.learningspring.bookStore.repository.AuthorRepository;
import com.learningspring.bookStore.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private static Logger logger = LoggerFactory.getLogger(BookStoreServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getBooks() throws ResourceNotFoundException {
        List<Book> booksList = bookRepository.findAll();
        if (booksList.isEmpty()) {
            throw new ResourceNotFoundException("Currently there are no books in store");
        }
        return booksList;
    }

    @Override
    public Optional<Book> getBookById(Long id) throws ResourceNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else throw new ResourceNotFoundException("Book is not available in store:" + id);
    }

    @Override
    public Book addBook(Book newBook) {

        return bookRepository.save(newBook);
    }

    @Override
    public void deleteBook(Long id) throws ResourceNotFoundException {

        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Book is not available in store:" + id);
    }

    @Override
    public List<Book> getBookByAuthorId(Long authorId) throws ResourceNotFoundException {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return bookRepository.getBookByAuthorId(authorId);
        } else throw new ResourceNotFoundException("Author is not available in store:" + authorId);
    }

    @Override
    public List<Author_Book> getBookNameListByAuthorId(Long authorId) throws ResourceNotFoundException {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
         //   logger.info("Book Titles" + bookRepository.getBookTitleByAuthorId(authorId));
            return bookRepository.getBookNameListByAuthorId(authorId);

        } else throw new ResourceNotFoundException("Author is not available in store:" + authorId);
    }

    @Override
    public List<Author_Book> getBookTitleAuthorName() {
        return bookRepository.getBookTitleAuthorName();

    }

}
