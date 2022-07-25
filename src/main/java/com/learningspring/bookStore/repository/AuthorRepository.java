package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByName(String name);

    //Sorting query
    @Query("SELECT a FROM Author a ORDER BY a.name ASC")
    List<Author> getAuthorByNameInOrder();



}
