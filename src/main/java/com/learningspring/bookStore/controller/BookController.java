package com.learningspring.bookStore.controller;

import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.exception.BookNotFoundException;
import com.learningspring.bookStore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookStoreService bookStoreService;

    //The function handles a GET request, processes it and gives back a list of Books as a response.
    @GetMapping({"/{getBooks}"})
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> booksList = bookStoreService.getBooks();
        return new ResponseEntity<>(bookStoreService.getBooks(), HttpStatus.OK);
    }

    //The function handles a GET request for the given ID, processes it and gives back Book details for the given id as a response.
    @GetMapping("/bookById/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return Optional.ofNullable(bookStoreService.getBookById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    //The function handles a POST request and Insert new books into database.
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book newBook) {
        return bookStoreService.addBook(newBook);
    }

    //The function handles a PUT request to update book details into database.
    @PutMapping("/updateBook/{id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable Long id) {

        return bookStoreService.getBookById(id)
                .map(book -> {
                    book.setIsbn(newBook.getIsbn());
                    book.setBook_Title(newBook.getBook_Title());
                    book.setPrice(newBook.getPrice());
                    return bookStoreService.addBook(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookStoreService.addBook(newBook);
                });
    }

    //The function handles a DELETE request to delete book details from database.
    @DeleteMapping("/deleteBook/{id}")
    void deleteBook(@PathVariable Long id) {
         bookStoreService.deleteBook(id);
    }

}
