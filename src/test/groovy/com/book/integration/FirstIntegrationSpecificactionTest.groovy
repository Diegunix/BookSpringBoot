package com.book.integration

import static org.junit.Assert.*

import javax.inject.Inject

import org.springframework.boot.test.context.SpringBootTest

import com.book.BookApplication
import com.book.controller.BookController
import com.book.dao.domain.Book

import spock.lang.Specification


@SpringBootTest( classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FirstIntegrationSpecificactionTest extends Specification {
    
 @Inject
  BookController service

    def "Devuelve todos los libros"() {
        when:
            def result = service.getAllBooks().size()
        then:
            result >1
    }
    
    def "Creacion de un libro"() {
        Book book = Book.builder().title("Libro de Prueba").illustrations(false).build();

        when:
            def result = service.createBook(book)
        then:
            "Libro de Prueba" == service.getBookById(result.id).title
    }

    def "Elimina un libro"() {
        when:
            def result = service.getAllBooks().size()
        then:
            service.deleteBookById(2L)
            def result2 = service.getAllBooks().size()
        then:
            result == result2+1
       
    }

}
