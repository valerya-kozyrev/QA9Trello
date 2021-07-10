package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import src.com.company.pages.PageBase;

import java.util.List;

public class CurrentBoardHelper extends PageBase {
    String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {

        this.driver = driver;
        this.boardName = boardName;
    }

    public By getLocatorBoardButton() {
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='" + boardName + "']]");
    }

    public void waitUntilCurrentBoardIsLoaded() {
        waitUntilElementIsClickable(By.cssSelector(".placeholder"), 10);
        WebElement addButton = driver.findElement(By.cssSelector(".placeholder"));

        if (addButton.getText().equals("Add another list")) {
            waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"), 5);
        }
    }

    public int getListSize() {
        return driver.findElements(By.cssSelector(".js-list-content")).size();
    }

    public int getCardSize() {
        return driver.findElements(By.cssSelector(".list-card-title")).size();
    }

//  create new list
    public void createNewList(String s) {
        int beginListsQuantity = this.getListSize();
        openAddList();
        enterListName(s);
        clickAddListButton();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
//        System.out.println("After adding: " + this.getListSize());
        closeAnotherAddList();
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
        waitUntilElementIsClickable(By.cssSelector(".js-cancel-edit"),5);
        WebElement closeAddAnotherList = driver.findElement(By.cssSelector(".js-cancel-edit"));
        closeAddAnotherList.click();
    }

//  create new card
    public void createNewCard() {
        int beginCards = this.getCardSize();
        addNewCard();
        enterCardName("card tile");
        clickAddCardButton();
        waitUntilElementsBecome(By.cssSelector(".list-card-title"),beginCards+1,10);
        cancelAnotherCard();
    }

    public void addNewCard() {
        // press 'Add a card'
        WebElement addCardButton = driver.findElement(By.cssSelector(".card-composer-container"));
        addCardButton.click();
    }

    public void enterCardName(String s) {
        // fill in card title
        WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
        editField(cardTitleField, s);
    }

    public void clickAddCardButton() {
        WebElement addCardButton = driver.findElement(By.cssSelector(".js-add-card"));
        addCardButton.click();
    }

    public void cancelAnotherCard() {
        waitUntilElementIsClickable(By.cssSelector(".js-cancel"),5);
        WebElement closeAddAnotherCard = driver.findElement(By.cssSelector(".js-cancel"));
        closeAddAnotherCard.click();
    }

//  copy
    public void copyList(String s) {
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnCopyList();
        enterCopiedListName("Changed");
        clickOnCreateList();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
    }

    public void clickOnExtraMenuButton() {
        // click on the list menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        WebElement extraMenu = driver.findElement(By.cssSelector(".list-header-extras-menu"));
        extraMenu.click();
    }

    public void clickOnCopyList() {
        // click on "Copy list"
        waitUntilElementIsClickable(By.className("js-copy-list"), 10);
        driver.findElement(By.className("js-copy-list")).click();
    }

    public void enterCopiedListName(String s) {
        //enter list title
        waitUntilElementIsClickable(By.className("js-autofocus"), 10);
        WebElement nameField = driver.findElement(By.className("js-autofocus"));
        nameField.sendKeys(s);
    }

    public void clickOnCreateList() {
        //click on "Create list"
        driver.findElement(By.cssSelector(".js-submit")).click();
    }

//  delete
    public void archiveList() {
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnArchiveList();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity-1,10);
    }

    public void clickOnArchiveList() {
        // click on "Archive this list"
        waitUntilElementIsClickable(By.className("js-close-list"), 10);
        driver.findElement(By.className("js-close-list")).click();
    }

    public List<WebElement> getNameElements(By xpath) {
        return driver.findElements(xpath);
    }

    public void archiveNameList(int number) {
        int beginLists = this.getListSize();
        clickOnExtraMenuButtonAccordingName(number);
        clickOnArchiveList();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
    }

    public void clickOnExtraMenuButtonAccordingName(int number) {
        // click on the list menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElements(By.cssSelector(".list-header-extras-menu")).get(number).click();
    }

    public int getNumberOfElementWithName(String nameList) { //if there's no list with this name we'll get -1
        int number = -1;
        int counter = 0;
        for(WebElement element: driver.findElements(By.cssSelector(".list-header-name"))){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }

}
