package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager entityManager;


    private Author author1;
    private Author author2;

    private Author allAuthors;

    @BeforeEach
    void setUp() {
        author1 = Author.builder()
                .id(1L)
                .name("Charles")
                .build();

        author2 = Author.builder()
                .id(2L)
                .name("William")
                .build();
        List<Author> authorList = Arrays.asList(author1,author2);
        authorRepository.saveAll(authorList);
    }


    @Test
    void saveAllAuthors() {
        List<Author> authors = Arrays.asList(author1,author2);
        Iterable<Author> allAuthors = authorRepository.saveAll(authors);

        AtomicInteger validIdFound = new AtomicInteger();
        allAuthors.forEach(author -> {
            if(author.getId()>0){
                validIdFound.getAndIncrement();
            }
        });
        assertEquals(2,validIdFound.intValue());
    }

    @Test
    void findAllAuthors() {
        List<Author> allAuthor = authorRepository.findAll();
        System.out.println("all authors:" + allAuthor);
        assertEquals(5, allAuthor.size());
    }

}