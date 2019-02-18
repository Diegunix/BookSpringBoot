package com.book.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.book.dao.domain.Book;
import com.book.dao.repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    @Resource
    BookController service;
    
    @Mock
    private BookRepository bookRepository;
    
    @Test
    public void testGetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book());
        when(bookRepository.findAll()).thenReturn(bookList);
        
        Iterable<Book> result = service.getAllBooks();
        int size = 0;
        if (result instanceof Collection) {
            size= ((Collection<?>) result).size();
        }
        assertThat(1,is(size));
       
    }
    
}