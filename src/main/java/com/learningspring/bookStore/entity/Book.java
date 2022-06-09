package com.learningspring.bookStore.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true)
    @NotNull
    private Long id;
    @Column(unique = true)
    private String isbn;
    private String book_Title;
    private double price;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    public Author getAuthor() {
        return author;
    }
}
