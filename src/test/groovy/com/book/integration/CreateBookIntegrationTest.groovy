package com.book.integration

import static org.junit.Assert.*

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

import com.book.BookApplication
import com.book.integration.pages.IndexPage

import geb.spock.GebReportingSpec


@SpringBootTest( classes = BookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateBookIntegrationTest extends GebReportingSpec {
    
    @LocalServerPort int port
    
    def setup() {
        baseUrl = "http://localhost:$port"
    }
 
    def "Creacion de un libro"() {
 
        given: "En el formulario de creación de un libro"
            to IndexPage
 
        when: "Relleno el formulario y pulso el boton"
            titleField = "Un libro de prueba test"
            priceField = "23.8"
            descriptionField = "El libro de prueba"
            nbopField = "256"
            submitButton.click()
 
        then: "Se ha agregado un nuevo libro "
            waitFor {messageOutput.displayed }
            assert messageOutput.text().contains("Book created")
        cleanup:
            browser.quit()
    }
}