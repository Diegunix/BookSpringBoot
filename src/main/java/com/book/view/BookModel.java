package com.book.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.book.dao.domain.Book;
import com.book.dao.repository.BookRepository;

@ManagedBean(name = "model", eager = true)
@RequestScoped
public class BookModel extends Book {
    
    @ManagedProperty(value = "#{book}")
    private BookView book;
    
    @ManagedProperty(value = "#{bookSelected}")
    private BookView bookSelected;
    
    @ManagedProperty(value = "#{bookRepository}")
    BookRepository bookRepository;

    public BookView getBookSelected() {
        return bookSelected;
    }

    public void setBookSelected(BookView bookSelected) {
        this.bookSelected = bookSelected;
    }

    public void setBook(BookView book) {
        this.book = book;
    }

    public BookView getBook() {
        return book;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    

    public String doCreateBook() {
        Book created = new Book();
        created.setId(this.book.getId());
        created.setTitle(this.book.getTitle());
        created.setPrice(this.book.getPrice());
        created.setNbofpage(this.book.getNbofpage());
        created.setDescription(this.book.getDescription());
        created.setIllustrations(this.book.getIllustrations());
        Book newBook = this.bookRepository.save(created);

        FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
                "The book " + created.getTitle() + " has been created with id=" + newBook.getId()));

        this.book.setTitle("");
        this.book.setPrice(null);
        this.book.setDescription("");
        this.book.setIllustrations(false);
        this.book.setNbofpage(null);
        this.book.setIllustrations(false);

        return "index.xhtml";
    }

    public void doFindBookById() {
        Book found = bookRepository.findById(this.book.getId()).get();
        this.book.setId(found.getId());
        this.book.setTitle(found.getTitle());
        this.book.setPrice(found.getPrice());
        this.book.setNbofpage(found.getNbofpage());
        this.book.setDescription(found.getDescription());
        this.book.setIllustrations(found.getIllustrations());
    }

    public List<BookView> findAllBooks() {
        List<BookView> books = new ArrayList<BookView>();
        for (Book entity : this.bookRepository.findAll()) {
            BookView view = new BookView();
            view.setId(entity.getId());
            view.setTitle(entity.getTitle());
            view.setPrice(entity.getPrice());
            view.setNbofpage(entity.getNbofpage());
            view.setDescription(entity.getDescription());
            view.setIllustrations(entity.getIllustrations());
            books.add(view);
        }
        return books;
    }

    public void doDeleteBook() throws IOException {
        bookRepository.deleteById(this.book.getId());

        FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage(FacesMessage.SEVERITY_WARN, "Book deleted",
                "The book with id=" + this.book.getId() + " has been deleted."));

        this.book.setTitle("");
        this.book.setPrice(null);
        this.book.setDescription("");
        this.book.setIllustrations(false);
        this.book.setNbofpage(null);
        this.book.setIllustrations(false);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }

    public void selectedBook() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/book/" + this.bookSelected.getId()+"/");
    }

}
