package com.company.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTest extends TestBase {

    @BeforeMethod
    public void initTest() throws InterruptedException {

        driver.findElement(By.cssSelector(".text-primary")).click();

        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, "lerkucij@gmail.com");

        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, "trello0909");

        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
        Thread.sleep(3000);

        WebElement board = driver.findElements(By.xpath("//div[@title='QA9']")).get(0);
        board.click();
        Thread.sleep(3000);
    }

    @Test
    public void createNewListTest() throws InterruptedException {

        WebElement openAddList = driver.findElement(By.className("placeholder"));
        openAddList.click();

        WebElement enterListTitle = driver.findElement(By.className("list-name-input"));
        editField(enterListTitle,"New List");

        WebElement addListButton = driver.findElement(By.cssSelector("input[value=\"Add list\"]"));
        addListButton.click();
        Thread.sleep(3000);

        WebElement closeAddAnotherList = driver.findElement(By.cssSelector("a[aria-label=\"Cancel list editing\"]"));
        closeAddAnotherList.click();
        Thread.sleep(3000);
    }

    @Test
    public void addNewCardTest() throws InterruptedException {
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();

        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, "card tile");

        driver.findElement(By.cssSelector(".js-add-card")).click();

        WebElement closeAddanotherCard = driver.findElement(By.cssSelector(".js-cancel"));
        closeAddanotherCard.click();
        Thread.sleep(3000);
    }

    @Test
    public void deleteListTest() throws InterruptedException {
        if(isPresent()){
            openListActions();
            delete();
        } else{
            createNewListTest();
            openListActions();
            delete();
        }
    }

    @Test
    public void copyListTest() throws InterruptedException {
        if(isPresent()){
            openListActions();
            copy();
        } else{
            createNewListTest();
            openListActions();
            copy();
        }
    }

    private boolean isPresent() {
        return driver.findElements(By.cssSelector(".list-header-target")).size()>0;
    }

    private void copy() throws InterruptedException {
        WebElement copyList = driver.findElement(By.className("js-copy-list"));
        copyList.click();
        Thread.sleep(3000);

        WebElement nameField = driver.findElement(By.className("js-autofocus"));
        nameField.sendKeys("1");
        Thread.sleep(3000);

        WebElement createListButton = driver.findElement(By.cssSelector(".js-submit"));
        createListButton.click();
        Thread.sleep(3000);
    }

    private void delete() throws InterruptedException {
        WebElement archiveThisList = driver.findElement(By.className("js-close-list"));
        archiveThisList.click();
        Thread.sleep(3000);
    }

    private void openListActions() throws InterruptedException {
        WebElement listActions = driver.findElement(By.cssSelector(".list-header-extras"));
        listActions.click();
        Thread.sleep(3000);
    }

    private void editField(WebElement field, String value) throws InterruptedException {
        field.click();
        field.sendKeys(value);
        Thread.sleep(3000);
    }


}

