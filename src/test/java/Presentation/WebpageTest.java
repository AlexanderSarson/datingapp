/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Alex
 */
public class WebpageTest {

    WebDriver driver;
    

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","C:\\temp\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void clickCreateProfileTest() throws InterruptedException {
        driver.get("http://localhost:8080/DatingApp/index.html");
        WebElement link = driver.findElement(By.xpath("//*[text()='Create Profile']"));
        Thread.sleep(3000);
        link.click();
        WebElement firstName, lastName, birthday;
        firstName = driver.findElement(By.name("firstName"));
        firstName.sendKeys("Mads");
        lastName = driver.findElement(By.name("lastName"));
        lastName.sendKeys("Brandt");
        birthday = driver.findElement(By.name("birthday"));
        birthday.sendKeys("1990-05-05");
        Thread.sleep(5000);
    }

}
