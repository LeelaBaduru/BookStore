package com.learningspring.bookStore.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Could not find Book " + id);
    }
}
