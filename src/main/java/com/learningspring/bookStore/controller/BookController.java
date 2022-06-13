package com.learningspring.bookStore.controller;

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
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookStoreService bookStoreService;
    @Autowired
    private AuthorService authorService;


    //The function handles a GET request, processes it and gives back a list of Books as a response.
    @GetMapping({"/{getBooks}"})
    public  List<Book> getBooks() throws ResourceNotFoundException {
        return bookStoreService.getBooks();
    }

    //The function handles a GET request for the given ID, processes it and gives back Book details for the given id as a response.
    @GetMapping("/bookById/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        return bookStoreService.getBookById(id);
    }

    //The function handles a POST request and Insert new books into database.
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book newBook) {
        return bookStoreService.addBook(newBook);
    }

    //The function handles a PUT request to update book details and for the give authorId with the respective book
    @PutMapping("/updateBook/{bookId}/author/{authorId}")
    public Book updateBook(@RequestBody Book updateBook, @PathVariable Long bookId, @PathVariable Long authorId) throws ResourceNotFoundException {
        Book book = bookStoreService.getBookById(bookId).get();
        Author author = authorService.getAuthorById(authorId).get();
                    book.setIsbn(updateBook.getIsbn());
                    book.setBook_Title(updateBook.getBook_Title());
                    book.setPrice(updateBook.getPrice());
                    book.setAuthor(author);
                    return bookStoreService.addBook(book);
                }
  /*              .orElseGet(() -> {
                    updateBook.setId(bookId);
                    return bookStoreService.addBook(updateBook);
                });
    } */

    //The function handles a PUT request to assign author to a book
    @PutMapping("/{bookId}/author/{authorId}")
    Book assignAuthorToBook(
            @PathVariable Long bookId,
            @PathVariable Long authorId
    ) throws ResourceNotFoundException {
        Book book = bookStoreService.getBookById(bookId).get();
        Author author = authorService.getAuthorById(authorId).get();
        book.setAuthor(author);
        return bookStoreService.addBook(book);
    }

    /*
    //The function handles a DELETE request to delete book details from database.
    @DeleteMapping("/deleteBook/{id}")
    void deleteBook(@PathVariable Long id) {
         bookStoreService.deleteBook(id);
    }
    */

}
