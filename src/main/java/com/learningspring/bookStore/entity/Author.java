package com.learningspring.bookStore.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }
}
