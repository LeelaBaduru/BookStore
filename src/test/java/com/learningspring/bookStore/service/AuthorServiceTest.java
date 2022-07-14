package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.exception.ResourceNotFoundException;
import com.learningspring.bookStore.repository.AuthorRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeAll
    void setUp() {
         author = Author.builder()
                .id(4L)
                .name("Charles")
                .build();
    }

    @Test
    public void getAuthorByName() {
        String name = "Charles";

        Mockito.when(authorRepository.findByName(name)).thenReturn(Optional.ofNullable(author));
        Optional<Author> foundName = authorService.getAuthorByName(name);

        assertAll(() -> assertEquals("Charles", foundName.get().getName()),
                  () -> assertEquals(1L, foundName.get().getId()));
    }

    @Test
    public void TestExceptionGetAuthorByName() {
        ResourceNotFoundException resourceNotFoundException =  assertThrows(ResourceNotFoundException.class,
                () -> authorService.getAuthorByName("Charles1"));
        assertEquals("Author is not available in store:Charles1", resourceNotFoundException.getMessage());

    }
}