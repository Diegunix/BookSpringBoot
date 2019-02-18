package com.book.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.dao.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
