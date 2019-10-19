/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author Alex
 */
@Ignore
public class WebpageTest extends TestBase{

    public WebpageTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void clickCreateProfileTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("http://tomcat:8080/root/");
        WebElement link = driver.findElement(By.id("2"));
        link.click();
        WebElement firstName, lastName, birthday;
        firstName = driver.findElement(By.id("3"));
        firstName.sendKeys("Mads");
        lastName = driver.findElement(By.id("4"));
        lastName.sendKeys("Brandt");
//        birthday = driver.findElement(By.id("birthday"));
//        birthday.sendKeys("1990-05-05");
    }
    
    @Test
    public void clickShowProfileTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("http://tomcat:8080/root/");
        WebElement link = driver.findElement(By.id("1"));
        link.click();
    }
    

}
