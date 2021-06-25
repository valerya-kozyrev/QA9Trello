package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginNegativeTest() throws InterruptedException {
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("email");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("password");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Thread.sleep(3000);

        System.out.println("Error message: " + driver.findElements(By.cssSelector("p.error-message")).get(0).getText());
    }

    @Test
    public void loginPositiveTest() throws InterruptedException {
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys("lerkucij@gmail.com");
        Thread.sleep(3000);

        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("trello0909");

        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        Thread.sleep(3000);

        System.out.println("Title: " + driver.findElements(By.xpath("//span[contains(text(), 'Boards')]")).get(0).getText());
    }
}
