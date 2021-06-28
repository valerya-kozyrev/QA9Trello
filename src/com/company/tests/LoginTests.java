package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    @BeforeMethod
    public void initTest() {
        // click on "Log in" button
        waitUntilelementIsClickable(By.cssSelector(".text-primary"), 20);
        driver.findElement(By.cssSelector(".text-primary")).click();
        waitUntilelementIsClickable(By.id("login"), 10);
    }

    @Test
    public void loginNegativeTest() {

        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, "email");

        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, "password");

        // press log in button
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        // output error-message
        waitUntilelementIsVisible(By.cssSelector("p.error-message"), 10);
        Assert.assertEquals(driver.findElements(By.cssSelector("p.error-message")).get(0).getText(),
                "There isn't an account for this username",
                "The error message is not correct");
    }

    @Test
    public void loginPositiveTest() {

        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, LOGIN);

        // press 'Log in with Atlassian' button
        driver.findElement(By.id("login")).click();

        // fill in password field
        waitUntilelementIsClickable(By.id("login-submit"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, PASSWORD);

        // press login button
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();

        //assert login
        waitUntilelementIsVisible(By.xpath("//div[@title='QA9']"), 10);
        Assert.assertEquals(driver.findElements(By.xpath("//span[contains(text(), 'Boards')]")).get(0).getText(),
                "Boards", "Name of the button is not 'Boards'");

    }
}
