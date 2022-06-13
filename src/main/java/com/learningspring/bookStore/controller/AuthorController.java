package com.learningspring.bookStore.controller;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    //The function handles a GET request, processes it and gives back a list of Authors as a response.
    @GetMapping
    public List<Author> getAuthorList() {
        logger.info("Inside Author list");
        return authorService.getAuthorsList();
    }

    //The function handles a POST request. Insert new Author into database
    @PostMapping
    public Author addAuthor(@RequestBody Author newAuthor) {
        return authorService.addAuthor(newAuthor);
    }

    //The function handles a DELETE request to delete book details from database.
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
       authorService.deleteAuthor(id);
    }

}
