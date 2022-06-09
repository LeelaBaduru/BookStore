package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import com.learningspring.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
