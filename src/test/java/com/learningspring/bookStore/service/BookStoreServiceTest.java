package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookStoreServiceTest {


    @InjectMocks
    private BookStoreServiceImpl bookStoreService;

    @Mock
    private BookRepository bookRepository;

    private Book book1;
    private Book book2;

    private Author author;

    @BeforeEach
    void setUp() {

        author = Author.builder()
                .id(1L)
                .name("Charles")
                .build();

        book1 = Book.builder()
                .id(1L)
                .isbn("854-1-62544-248-2")
                .book_Title("Circus Tiger")
                .price(10.0)
                .author(author)
                .build();

        book2 = Book.builder()
                .id(2L)
                .isbn("994-1-62544-248-2")
                .book_Title("All my friends")
                .price(18.0)
                .author(author)
                .build();
    }

    @Test
    @DisplayName("Test to check if all books are retrieved")
    void getBooks() {
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book1);
        bookList.add(book2);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);

        //test
        List<Book> list = bookStoreService.getBooks();
        assertEquals(2, list.size());
        assertEquals("All my friends", list.get(0).getBook_Title());
    }

    @Test
    void getBookById() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(book1));
        Optional<Book> book = bookStoreService.getBookById(1L);
        assertEquals(10.0, book.get().getPrice());
    }

    @Test
    void addBook() {
        Book newBook = Book.builder()
                .id(3L)
                .isbn("994-1-897-248-2")
                .book_Title("Penguins at the Zoo")
                .price(20.0)
                .author(author)
                .build();
       Book createdBook = bookStoreService.addBook(newBook);
        verify(bookRepository, times(1)).save(newBook);
    }

}