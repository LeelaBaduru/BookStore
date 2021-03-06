package com.learningspring.bookStore.service;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.exception.ResourceNotFoundException;
import com.learningspring.bookStore.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private static Logger logger = LoggerFactory.getLogger(AuthorService.class);
    @Autowired
    private AuthorRepository authorRepository;


    public List<Author> getAuthorsList() {
        List<Author> authorsList = authorRepository.findAll();
        logger.info("Inside service Author list");
        return authorsList;
    }

    public Optional<Author> getAuthorById(Long id) throws ResourceNotFoundException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return author;
        } else throw new ResourceNotFoundException("Author is not available in store:" + id);
    }

    public Author addAuthor(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    public void deleteAuthor(Long id) throws ResourceNotFoundException {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Author is not available in store:" + id);
    }

    public Optional<Author>  getAuthorByName(String name) throws ResourceNotFoundException {
        Optional<Author> authorName = authorRepository.findByName(name);
        if (authorName.isPresent()) {
            return authorName;
        } else throw new ResourceNotFoundException("Author is not available in store:" + name);

    }

    public List<Author> getAuthorListByOrder(){
        return authorRepository.getAuthorByNameInOrder();
    }
}
