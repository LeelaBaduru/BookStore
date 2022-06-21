package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a ORDER BY a.name ASC")
    List<Author> getAuthorByNameInOrder();



}
