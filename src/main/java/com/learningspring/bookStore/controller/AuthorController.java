package com.learningspring.bookStore.controller;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private static Logger logger = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    //The function handles a GET request, processes it and gives back a list of Authors as a response.
    @GetMapping({"/{getAuthorList}"})
    public List<Author> getAuthorList() {
        logger.info("Inside Author list");
        return authorService.getAuthorsList();
    }
}
