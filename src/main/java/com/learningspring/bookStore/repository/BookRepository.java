package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.dto.Author_Book;
import com.learningspring.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(name = "SELECT * FROM Book WHERE b.author_Id = ?1")
    List<Book> getBookByAuthorId(Long authorId);

    @Query(value = "SELECT book_Title FROM Book WHERE author_Id = ?1")
    List<Object> getBookNameListByAuthorId(Long authorId);


    @Query("SELECT new com.learningspring.bookStore.dto.Author_Book(a.name , b.book_Title) FROM Author a JOIN a.books b")
    public List<Author_Book> getBookTitleAuthorName();
}

