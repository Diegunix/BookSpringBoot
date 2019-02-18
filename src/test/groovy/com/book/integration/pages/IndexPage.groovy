package com.book.integration.pages

import geb.Page

class IndexPage extends Page {
    static url = "/"
    static at = { title == "Default title" }
    static content = {
        heading { $("h1").text() }
        errorHeading { $(".alert-error h4").text() }
        signupForm { $("form[id=bookForm]") }
        titleField { $("input[name=title]") }
        priceField { $("input[name=price]") }
        descriptionField { $("textarea[name=description]") }
        nbopField { $("input[name=nbop]") }
        submitButton() { $("button[type=submit]") }
        messageOutput { $("#j_id_b_container") }
        
    }
}