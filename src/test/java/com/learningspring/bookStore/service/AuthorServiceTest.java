package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    void setUp() {
         author = Author.builder()
                .id(4L)
                .name("Charles")
                .build();
    }

    @Test
    @DisplayName("Test for getAuthorByName method")
    public void givenAuthorName_whenValidAuthorName_thenAuthorShouldFound() {
        Mockito.when(authorRepository.findByName("Charles")).thenReturn(Optional.ofNullable(author));

        String name = "Charles";
        Optional<Author> foundName = authorService.getAuthorByName(name);

        assertEquals(author.getName(), foundName.get().getName());
        assertEquals(4L, foundName.get().getId());
    }
}