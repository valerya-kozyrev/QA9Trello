package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import src.com.company.pages.PageBase;

import java.util.List;

public class CurrentBoardHelper extends PageBase {

    public CurrentBoardHelper(WebDriver driver){
        this.driver = driver;
    }



    public void waitUntilCurrentBoardIsLoaded() {
        waitUntilElementIsClickable(By.className("placeholder"), 10);
    }

    public int getListSize() {
        return driver.findElements(By.cssSelector(".js-list-content")).size();
    }

    public int getCardSize() {
        return driver.findElements(By.cssSelector(".list-card-title")).size();
    }

    public void openAddList() {
        // press 'Add list button'
        WebElement openAddList = driver.findElement(By.className("placeholder"));
        openAddList.click();
    }

    public void enterListName(String name) {
        // enter name of the list
        WebElement enterListTitle = driver.findElement(By.className("list-name-input"));
        editField(enterListTitle, name);
    }

    public void clickAddListButton() {
        // click 'Add list' button
        WebElement addListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        addListButton.click();
    }

    public void closeAnotherAddList() {
        // click 'x' button to cancel new list creating
        WebElement closeAddAnotherList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        closeAddAnotherList.click();
    }

    public void createNewList(String s) {
        openAddList();
        enterListName(s);
        clickAddListButton();
        closeAnotherAddList();
    }

    public void cancelAnotherCard() {
        WebElement closeAddAnotherCard = driver.findElement(By.cssSelector(".js-cancel"));
        closeAddAnotherCard.click();
    }

    public void clickAddCardButton() {
        WebElement addCardButton = driver.findElement(By.cssSelector(".js-add-card"));
        addCardButton.click();
    }

    public void enterCardName(String s) {
        // fill in card title
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, s);
    }

    public void addNewCard() {
        // press 'Add a card'
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();
    }

    public void createNewCard() {
        addNewCard();
        enterCardName("card tile");
        clickAddCardButton();
        cancelAnotherCard();
    }

    public void copyList(String s) {
        clickOnExtraMenuButton();
        clickOnCopyList();
        enterCopiedListName("Changed");
        clickOnCreateList();
    }

    public void clickOnCreateList() {
        //click on "Create list"
        driver.findElement(By.cssSelector(".js-submit")).click();
    }

    public void enterCopiedListName(String s) {
        //enter list title
        waitUntilElementIsClickable(By.className("js-autofocus"), 10);
        WebElement nameField = driver.findElement(By.className("js-autofocus"));
        nameField.sendKeys(s);
    }

    public void clickOnCopyList() {
        // click on "Copy list"
        waitUntilElementIsClickable(By.className("js-copy-list"), 10);
        driver.findElement(By.className("js-copy-list")).click();
    }

    public void clickOnExtraMenuButton() {
        // click on the list menu
        WebElement extraMenu = driver.findElement(By.cssSelector(".list-header-extras-menu"));
        extraMenu.click();
    }

    public void clickOnArchiveList() {
        // click on "Archive this list"
        waitUntilElementIsClickable(By.className("js-close-list"), 10);
        driver.findElement(By.className("js-close-list")).click();
    }

    public void archiveList() {
        clickOnExtraMenuButton();
        clickOnArchiveList();
    }

    public List<WebElement> getNameElements(By xpath) {
        return driver.findElements(xpath);
    }

}
