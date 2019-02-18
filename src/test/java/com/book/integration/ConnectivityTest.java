package com.book.integration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConnectivityTest {

    WebDriver driver;

    @Before
    public void beforeTest() {
        System.out.println("Running before test");
        String PROXY = "proxy.map.es:8080";

        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);

        FirefoxOptions options = new FirefoxOptions(cap);

        driver = new FirefoxDriver(options);
    }

    @Test
    public void firefoxTest() {
        driver.navigate().to("http://www.seat.mpr.gob.es/es/portal/index.html");
        Assert.assertEquals("Title check failed!", "Inicio:: Ministerio de Política Territorial y Función Pública ::", driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("Inicio:: Ministerio de Política Territorial y Función Pública ::");
            }
        });
        
    }

    @After
    public void afterTest() {
        System.out.println("Running after method");
        driver.quit();
    }

}