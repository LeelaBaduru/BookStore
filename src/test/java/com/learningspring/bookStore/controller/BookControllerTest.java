package com.learningspring.bookStore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import com.learningspring.bookStore.service.AuthorService;
import com.learningspring.bookStore.service.BookStoreService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest {


    @MockBean
    private BookStoreService bookStoreService;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    private Book book;
    private Book book2;
    private Author author;

    @BeforeAll
    void setUp() {
        author = Author.builder()
                .id(1L)
                .name("Chetan Bhagat")
                .build();

        book = Book.builder()
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
    @DisplayName("Test to check if book is fetched for the given id")
    void getBookById() throws Exception {
        Mockito.when(bookStoreService.getBookById(1L)).thenReturn(Optional.ofNullable(book));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.book_Title").value("Circus Tiger1"));
    }


    @Test
    @DisplayName("Test to check if new book is inserted")
    void addBook() throws Exception {
        Book inputBook = Book.builder()
                .id(2L)
                .isbn("954-1-62544-248-2")
                .book_Title("Circus Fish")
                .author(author)
                .build();
        Mockito.when(authorService.getAuthorById(1L)).thenReturn(Optional.ofNullable(author));

        mockMvc.perform(MockMvcRequestBuilders.post("/books/authors/{authorId}", 1L)
                        .content(asJsonString(inputBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
       }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Test to check if book details are updated")
    void updateBook() throws Exception {
        Book inputBook = Book.builder()
                .id(1L)
                .isbn("854-1-62544-248-2")
                .book_Title("Circus Lion")
                .price(10.0)
                .author(author)
                .build();
        Mockito.when(authorService.getAuthorById(1L)).thenReturn(Optional.ofNullable(author));
        Mockito.when(bookStoreService.getBookById(1L)).thenReturn(Optional.ofNullable(book));

        mockMvc.perform(MockMvcRequestBuilders.put("/books/{bookId}/authors/{authorId}", 1L, 1L)
                        .content(asJsonString(inputBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<Book> bookID = bookStoreService.getBookById(1L);
        assertEquals("Circus Lion", bookID.get().getBook_Title());

    }

    @Test
    @DisplayName("Test to check if book is deleted for the given bookId")
    public void deleteBook() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/{id}", 1))
                .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());

    }
}


