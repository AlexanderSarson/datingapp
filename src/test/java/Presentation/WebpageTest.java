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
public class WebpageTest extends TestBase{

    public WebpageTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @Test
    public void clickCreateProfileTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("http://tomcat:8080/root/");
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
