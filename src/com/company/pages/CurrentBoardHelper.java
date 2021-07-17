package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import src.com.company.pages.PageBase;

import java.util.List;

public class CurrentBoardHelper extends PageBase {

    @FindBy(xpath = "//a[@class = 'board-tile'][.//div[@title='QA9']]")
    WebElement qa9Board;
    @FindBy(css=".placeholder")
    WebElement addAnotherListElement;
    @FindBy(css=".js-list-content")
    List<WebElement> listOfLists;
    @FindBy(css=".list-card-title")
    List<WebElement> listOfCards;
    @FindBy(className = "list-name-input")
    WebElement titleListField;
    @FindBy(css = ".js-save-edit")
    WebElement addListButton;
    @FindBy(css=".js-cancel-edit")
    WebElement cancelAddAnotherList;
    @FindBy(css=".card-composer-container")
    WebElement addACardButton;
    @FindBy(css=".js-card-title")
    WebElement cardTitleField;
    @FindBy(css=".js-add-card")
    WebElement addCardButton;
    @FindBy(css=".js-cancel")
    WebElement cancelAddAnotherCard;
    @FindBy(css=".list-header-extras-menu")
    WebElement listMenuButton;
    @FindBy(css=".js-copy-list")
    WebElement copyListButton;
    @FindBy(css=".js-autofocus")
    WebElement copiedListTitleField;
    @FindBy(css=".js-submit")
    WebElement createCopiedListButton;
    @FindBy(className="js-close-list")
    WebElement archieveThisListButton;
    @FindBy(css=".list-header-name")
    List<WebElement> listName;

    String boardName;

    public CurrentBoardHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }

    public CurrentBoardHelper openPage() {
        waitUntilElementIsClickable(getLocatorBoardButton(),10);
        WebElement qa9Board = driver.findElement(getLocatorBoardButton());
        qa9Board.click();
        return this;
    }

    public By getLocatorBoardButton() {
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='" + boardName + "']]");
    }

    public void waitUntilCurrentBoardIsLoaded() {
        waitUntilElementIsClickable(addAnotherListElement, 10);
        if (addAnotherListElement.getText().equals("Add another list")) {
            waitUntilAllElementsAreVisible(listOfLists, 5);
        }
    }

    public int getListSize(){
        return listOfLists.size();
    }

    public int getCardSize(){
        return listOfCards.size();
    }

    public int getNumberOfListsBefore(String s) {
        int numberOfListsBefore = getListSize();
        if (numberOfListsBefore == 0) {
            createNewList(s);
            numberOfListsBefore++;
        }
        return numberOfListsBefore;
    }

//  create new list
    public void createNewList(String s) {
        int beginListsQuantity = this.getListSize();
        openAddList();
        enterListName(s);
        clickAddListButton();
        waitUntilElementsBecome(listOfLists,beginListsQuantity+1,10);
        closeAnotherAddList();
    }

    public void openAddList() {
        addAnotherListElement.click();
    }

    public void enterListName(String name) {
        editField(titleListField, name);
    }

    public void clickAddListButton() {
        addListButton.click();
    }

    public void closeAnotherAddList() {
        waitUntilElementIsClickable(cancelAddAnotherList,5);
        cancelAddAnotherList.click();
    }

//  create new card
    public void createNewCard() {
        int beginCards = this.getCardSize();
        addNewCard();
        enterCardName("card tile");
        clickAddCardButton();
        waitUntilElementsBecome(listOfLists,beginCards+1,10);
        cancelAnotherCard();
    }

    public void addNewCard() {
        addACardButton.click();
    }

    public void enterCardName(String s) {
        editField(cardTitleField, s);
    }

    public void clickAddCardButton() {
        addCardButton.click();
    }

    public void cancelAnotherCard() {
        waitUntilElementIsClickable(cancelAddAnotherCard,5);
        cancelAddAnotherCard.click();
    }

//  copy
    public void copyList(String s) {
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnCopyList();
        enterCopiedListName("Changed");
        clickOnCreateList();
        waitUntilElementsBecome(listOfLists,beginListsQuantity+1,10);
    }

    public void clickOnExtraMenuButton() {
        waitUntilElementIsClickable(listMenuButton,5);
        listMenuButton.click();
    }

    public void clickOnCopyList() {
        waitUntilElementIsClickable(copyListButton, 10);
        copyListButton.click();
    }

    public void enterCopiedListName(String s) {
        waitUntilElementIsClickable(copiedListTitleField, 10);
        copiedListTitleField.sendKeys(s);
    }

    public void clickOnCreateList() {
        createCopiedListButton.click();
    }

//  delete
    public void archiveList() {
        int beginListsQuantity = this.getListSize();
        clickOnExtraMenuButton();
        clickOnArchiveList();
        waitUntilElementsBecome(listOfLists,beginListsQuantity-1,10);
    }

    public void clickOnArchiveList() {
        waitUntilElementIsClickable(archieveThisListButton, 10);
        archieveThisListButton.click();
    }

    public List<WebElement> getNameElements(By xpath) {
        return driver.findElements(xpath);
    }

    public void archiveNameList(int number) {
        int beginLists = this.getListSize();
        clickOnExtraMenuButtonAccordingName(number);
        clickOnArchiveList();
        waitUntilElementsBecome(listOfLists,beginLists-1,5);
    }

    public void clickOnExtraMenuButtonAccordingName(int number) {
        waitUntilElementIsClickable(listMenuButton,5);
        driver.findElements(By.cssSelector(".list-header-extras-menu")).get(number).click();
    }

    public int getNumberOfElementWithName(String nameList) { //if there's no list with this name we'll get -1
        int number = -1;
        int counter = 0;
        for(WebElement element: listName){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }


    String thisListName = "Other List";

    public By getLocatorListName() {
        return By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+ thisListName +"')]]");
    }

    public void archiveNameList1(String s) {
        List<WebElement> nameList = driver.findElements(getLocatorListName());
        int beginLists = this.getNameListSize();
        clickOnExtraMenuButtonAccordingName1(s);
        clickOnArchiveList();
        waitUntilElementsBecome(nameList,beginLists-1,5);
    }

    public int getNameListSize(){
        List<WebElement> nameList = driver.findElements(getLocatorListName());
        return nameList.size();
    }

    public void clickOnExtraMenuButtonAccordingName1(String s) {
        WebElement otherList = driver.findElement(getLocatorListName());
        waitUntilElementIsClickable(listMenuButton,5);
        otherList.findElement(By.cssSelector(".list-header-extras-menu")).click();
    }

//    public int getNumberOfListsBefore1(String s) {
//        int numberOfListsBefore = getListSize();
//        if (getNameListSize() == 0) {
//            createNewList(s);
//            numberOfListsBefore++;
//        }
//        return numberOfListsBefore;
//    }
}
