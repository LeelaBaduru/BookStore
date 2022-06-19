package com.learningspring.bookStore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

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
    }


    @Test
    @DisplayName("Test to check if valid Author is fetched by given name")
    public void getAuthorByName() throws Exception {
        Mockito.when(authorService.getAuthorByName("Charles")).thenReturn(Optional.ofNullable(author1));
        mvc.perform(MockMvcRequestBuilders
                        .get("/authors/name/{name}", "Charles")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Charles"));
    }

    @Test
    @DisplayName("Test to check if all authors are retrieved")
    public void getAuthorList() throws Exception {
        Mockito.when(authorService.getAuthorsList()).thenReturn(Arrays.asList(author1, author2));
        mvc.perform(MockMvcRequestBuilders
                        .get("/authors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath(("$"), hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Charles"));

    }

    @Test
    @DisplayName("Test to check if new author inserted into database")
    public void addAuthor() throws Exception {
        Author newAuthor = Author.builder()
                     .id(1L)
                    .name("Charles")
                    .build();
        Mockito.when(authorService.addAuthor(newAuthor)).thenReturn(author1);
        mvc.perform(MockMvcRequestBuilders
                        .post("/authors")
                        .content(asJsonString(author1))
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

}