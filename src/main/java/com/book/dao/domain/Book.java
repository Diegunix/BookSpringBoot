package com.book.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "books.public", name = "Book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    @Column
    @NotNull
    private String title;
    @Column(nullable = true)
    private Double price;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Integer nbofpage;
    @Column(nullable = true)
    private Boolean illustrations;

}
