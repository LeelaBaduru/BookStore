package com.learningspring.bookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)

    private Long id;

    @NotBlank(message = "Book isbn should not be empty or null")
    private String isbn;

    @NotBlank(message = "Book Title should not be empty or null")
    private String book_Title;

    //  @NotEmpty(message = "Book price should not be null")
    @Positive(message = "Enter valid book price")
    private double price;


    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_Title() {
        return book_Title;
    }

    public void setBook_Title(String book_Title) {
        this.book_Title = book_Title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
