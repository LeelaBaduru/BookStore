package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        List<Author> authorList = Arrays.asList(author1, author2);
        authorRepository.saveAll(authorList);
    }


    @Test
    void findAll_success() {
        //  List<Author> allAuthors = authorRepository.findAll();
        // assertEquals(2, allAuthors.size());

        String name = "Charles";
        Optional<Author> foundName = authorRepository.findByName(name);
        assertEquals(author1.getName(), foundName.get().getName());
    }
}