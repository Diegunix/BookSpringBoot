package com.book.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.dao.domain.Book;
import com.book.dao.repository.BookRepository;

@RestController
public class BookController {

    private BookRepository bookRepository;
    
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/service/books")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @RequestMapping(value = "/service/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book getBookById(@PathVariable Long id) {
        return this.bookRepository.findById(id).get();
    }
    
    @RequestMapping(value = "/service/book/{id}", method = RequestMethod.DELETE)
    public void deleteBookById(@PathVariable Long id) {
        this.bookRepository.deleteById(id);
    }
    
    @RequestMapping(value = "/service/book/", method = RequestMethod.POST)
    public @ResponseBody Book createBook(@PathVariable Book book) {
        return this.bookRepository.save(book);
    }
}
