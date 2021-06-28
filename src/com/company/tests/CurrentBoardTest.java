package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentBoardTest extends TestBase {

    @BeforeMethod
    public void initTest() {

        // click 'Log in' button
        waitUntilelementIsClickable(By.cssSelector(".text-primary"), 20);
        driver.findElement(By.cssSelector(".text-primary")).click();

        // fill in email field
        waitUntilelementIsClickable(By.id("login"), 10);
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, LOGIN);

        // press 'Log in with Atlassian' button
        driver.findElement(By.id("login")).click();

        // fill in password field
        waitUntilelementIsClickable(By.id("login-submit"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, PASSWORD);

        // press log-in button
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();

        // click on board QA9 tab
        waitUntilelementIsClickable(By.xpath("//div[@title='QA9']"), 10);
        WebElement board = driver.findElements(By.xpath("//div[@title='QA9']")).get(0);
        board.click();
        waitUntilelementIsVisible(By.cssSelector(".js-list-content"), 10);
    }

    @Test
    public void createNewListTest() {

        int numberOfListsBefore = driver.findElements(By.cssSelector(".js-list-content")).size();

        // press 'Add list button'
        WebElement openAddList = driver.findElement(By.className("placeholder"));
        openAddList.click();

        // enter name of the list
        WebElement enterListTitle = driver.findElement(By.className("list-name-input"));
        editField(enterListTitle,"New List");

        // click 'Add list' button
        WebElement addListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        addListButton.click();

        // click 'x' button to cancel new list creating
        WebElement closeAddAnotherList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        closeAddAnotherList.click();

        int numberOfListsAfter = driver.findElements(By.cssSelector(".js-list-content")).size();
        Assert.assertEquals(numberOfListsBefore+1, numberOfListsAfter);
    }

    @Test
    public void addNewCardTest() {

        // press 'Add a card'
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();

        // fill in card title
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, "card tile");

        driver.findElement(By.cssSelector(".js-add-card")).click();

        WebElement closeAddAnotherCard = driver.findElement(By.cssSelector(".js-cancel"));
        closeAddAnotherCard.click();
    }

    @Test
    public void archiveListTest() {

        int numberOfListsBefore = driver.findElements(By.cssSelector(".js-list-content")).size();

        if (numberOfListsBefore == 0) {
            // press 'Add list button'
            WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
            createListButton.click();

            // enter name of the list
            WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
            editField(nameListField, "Test List");

            // click 'Add list' button
            WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
            saveListButton.click();

            // click 'x' button to cancel new list creating
            WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelListCreatingButton.click();

            numberOfListsBefore = driver.findElements(By.cssSelector(".js-list-content")).size();
        }

        // click on the list menu
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // click on "Archive this list"
        waitUntilelementIsClickable(By.cssSelector(".js-close-list"), 20);
        driver.findElement(By.cssSelector(".js-close-list")).click();

        int numberOfListsAfter = driver.findElements(By.cssSelector(".js-list-content")).size();
        Assert.assertEquals(numberOfListsBefore-1, numberOfListsAfter);
    }

    @Test
    public void copyListTest() {

        int numberOfListsBefore = driver.findElements(By.cssSelector(".js-list-content")).size();
        if (numberOfListsBefore == 0) {
            // press 'Add list button'
            WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
            createListButton.click();

            // enter name of the list
            WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
            editField(nameListField, "Test List");

            // click 'Add list' button
            WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
            saveListButton.click();

            // click 'x' button to cancel new list creating
            WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
            cancelListCreatingButton.click();

            numberOfListsBefore = driver.findElements(By.cssSelector(".js-list-content")).size();
        }
        // click on the list menu
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // click on "Copy list"
        driver.findElement(By.cssSelector(".js-copy-list")).click();

        //enter list title
        WebElement nameField = driver.findElement(By.cssSelector(".js-autofocus"));
        nameField.sendKeys("nameChanged");

        //click on "Create list"
        driver.findElement(By.cssSelector(".js-submit")).click();

        int numberOfListsAfter = driver.findElements(By.cssSelector(".js-list-content")).size();
        Assert.assertEquals(numberOfListsBefore+1, numberOfListsAfter);
    }
}

