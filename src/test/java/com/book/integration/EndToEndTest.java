package com.book.integration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.book.dao.domain.Book;

public class EndToEndTest {

    WebDriver driver;
    Book bookCreate;
    Book bookList;

    @Before
    public void beforeTest() {
        driver = new FirefoxDriver();
        bookCreate = Book.builder().description("Magnifico libro de Alicia").title("Alicia en el país de las maravillas").nbofpage(345).price(45.5d).build();
        bookList = Book.builder().description("Functional Programming in Scala is a serious tutorial for programmers looking to learn FP and apply it to the everyday business of coding.")
                .title("Functional Programming in Scala").nbofpage(301).price(49.99d).id(0L).build();
    }

    @Test
    public void createBookTest() {
        driver.navigate().to("http://localhost:8080");
        Assert.assertEquals("Title check failed!", "Default title", driver.getTitle());
        
        WebElement element;
        element = driver.findElement(By.id("title"));
        element.sendKeys(bookCreate.getTitle());
        
        element = driver.findElement(By.id("price"));
        element.sendKeys(bookCreate.getPrice().toString());
        
        element = driver.findElement(By.id("description"));
        element.sendKeys(bookCreate.getDescription());
        
        element = driver.findElement(By.id("nbop"));
        element.sendKeys(bookCreate.getNbofpage().toString());
        
        driver.findElement(By.id("saveButton")).click();
        
        
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement element;
                element = driver.findElement(By.className("ui-growl-title"));
                return element.getText().startsWith("Book created");
            }
        });
    }
    
    
    @Test
    public void linkBookAndReturnTest() {
        driver.navigate().to("http://localhost:8080");
        Assert.assertEquals("Title check failed!", "Default title", driver.getTitle());
        
        driver.findElement(By.id("booklist:"+bookList.getId()+":lblDataTitle")).click();
        
        Assert.assertEquals("Title check failed!", "View a book", driver.getTitle());
        
        WebElement element;
        element = driver.findElement(By.id("titleLbl"));
        Assert.assertEquals("Title check failed!", bookList.getTitle(), element.getText());
        
        element = driver.findElement(By.id("priceLbl"));
        Assert.assertEquals("Price check failed!", bookList.getPrice().toString(), element.getText());
        
        element = driver.findElement(By.id("descriptionLbl"));
        Assert.assertEquals("Description check failed!", bookList.getDescription(), element.getText());
        
        element = driver.findElement(By.id("nbofLbl"));
        Assert.assertEquals("Number of Pages check failed!", bookList.getNbofpage().toString(), element.getText());
        
        driver.findElement(By.id("navigationForm:homeButton")).click();
        
        Assert.assertEquals("Title check failed!", "Default title", driver.getTitle());
        
    }


    @After
    public void afterTest() {
        driver.quit();
    }

}