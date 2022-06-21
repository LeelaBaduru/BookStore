package com.learningspring.bookStore.controller;

import com.learningspring.bookStore.dto.Author_Book;
import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.exception.ResourceNotFoundException;
import com.learningspring.bookStore.service.AuthorService;
import com.learningspring.bookStore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookStoreService bookStoreService;
    @Autowired
    private AuthorService authorService;


    //The function handles a GET request, processes it and gives back a list of Books as a response.
    @GetMapping
    public List<Book> getBooks() throws ResourceNotFoundException {
        return bookStoreService.getBooks();
    }


    //The function handles a GET request. For the given bookID, Book details are fetched from the database and returns the book details.
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        return bookStoreService.getBookById(id);
    }

    //The function handles a POST request. Insert new books into database for the given authorId
    @PostMapping("/authors/{authorId}")
    public Book addBook(@RequestBody Book newBook, @PathVariable Long authorId) throws ResourceNotFoundException {
        Book newBookCreated = newBook;
        Author author = authorService.getAuthorById(authorId).get();
        newBookCreated.setAuthor(author);
        return bookStoreService.addBook(newBookCreated);
    }


    //The function handles a PUT request. Update book details and authorId with the respective book
    @PutMapping("/{bookId}/authors/{authorId}")
    public Book updateBook(@RequestBody Book updateBook, @PathVariable Long bookId, @PathVariable Long authorId) throws ResourceNotFoundException {
        Book book = bookStoreService.getBookById(bookId).get();
        Author author = authorService.getAuthorById(authorId).get();
        book.setIsbn(updateBook.getIsbn());
        book.setBook_Title(updateBook.getBook_Title());
        book.setPrice(updateBook.getPrice());
        book.setAuthor(author);
        return bookStoreService.addBook(book);
    }


    //The function handles a DELETE request to delete book details from a database.
    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) throws ResourceNotFoundException {
        bookStoreService.deleteBook(id);
    }

    //The function handles a GET request to fetch list of books for the given authorId.
    @GetMapping("/authors/{authorId}")
    public List<Book> getBookByAuthorId(@PathVariable Long authorId) throws ResourceNotFoundException {
        return bookStoreService.getBookByAuthorId(authorId);
    }

    //The function handles a GET request to fetch list of book titles for the given authorId.
    @GetMapping("/bookTitles/authors/{authorId}")
    public List<Object> getBookNameListByAuthorId(@PathVariable Long authorId) throws ResourceNotFoundException {
        List<Object> bookTitles = bookStoreService.getBookNameListByAuthorId(authorId);
        return bookTitles;
    }

    //The function handles a GET request to fetch list of book titles and respective author name.
    @GetMapping("/authors")
    public List<Author_Book> getBookTitleAuthorName() {
        List<Author_Book> booksList = bookStoreService.getBookTitleAuthorName();
        return booksList;
    }
}