package com.book.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.book.dao.domain.Book;

@ManagedBean(name = "book", eager = true)
@RequestScoped
public class BookView extends Book {

    public BookView() {
    }
}
