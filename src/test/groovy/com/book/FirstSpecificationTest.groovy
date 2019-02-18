package com.book


import com.book.controller.BookController
import com.book.dao.domain.Book
import com.book.dao.repository.BookRepository

import spock.lang.Specification

class FirstSpecificationTest extends Specification {

    def "Devuelve todos los libros"() {
        List<Book> books = new ArrayList()
        Book book = Book.builder().id(2).build();
        Book book2 = Mock()

        setup:
            books << book
            books << book2

            def stubbedBookRepository = Stub(BookRepository){
                findAll() >> books
            }

            def service = new BookController(stubbedBookRepository)

        when:
            def result = service.getAllBooks().size()
        then:
            result == 2
        
    }
    
    def "Creacion de un libro"() {
        Book book = Book.builder().id(2).build();

        def stubbedBookRepository = Stub(BookRepository){
            save(_) >> book
        }

        def service = new BookController(stubbedBookRepository)

        when:
            def result = service.createBook(book)
        then:
            result.id == 2
    }
    
    def "Elimina un libro"() {
        setup:
        def mockedBookRepository = Mock(BookRepository)

        def service = new BookController(mockedBookRepository)

        when:
            service.deleteBookById(2L)
        then:
             1 * mockedBookRepository.deleteById(_)
        
    }
}