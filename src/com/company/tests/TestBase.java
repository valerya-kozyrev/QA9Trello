package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String PASSWORD = "trello0909";
    public static final String LOGIN = "lerkucij@gmail.com";

    WebDriver driver;

    @BeforeMethod
    public void startUp() {
//        ChromeOptions options = new ChromeOptions(); // change language optons of the site
//        options.addArguments("lang=" + "rus");
//        options.addArguments("lang=" + "en");
//        driver = new Chromedriver(options);

        driver = new ChromeDriver();
        driver.get("https://trello.com");
//        Thread.sleep(3000); //we replaced with waitUntilelementIsClickable
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


    public void editField(WebElement field, String value) {
        field.click();
        field.sendKeys(value);
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilelementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
