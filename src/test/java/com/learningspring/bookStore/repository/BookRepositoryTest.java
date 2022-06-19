package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Book book = Book.builder()
                .id(1L)
                .book_Title("Circus Lion")
    //            .price(10)
                .build();
        entityManager.merge(book);
    }

    @Test
    public void whenFindById_thenReturnBookTitle() {

        Book bookID = bookRepository.findById(1L).get();
        assertEquals("Circus Lion", bookID.getBook_Title());

    }

    @Test
    public void whenFindById_thenReturnBookPrice() {

        Book bookID = bookRepository.findById(1L).get();
        assertEquals(10.0, bookID.getPrice());

    }


}