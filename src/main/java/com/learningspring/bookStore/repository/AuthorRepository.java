package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public  Optional<Author> findByName(String name);
}
