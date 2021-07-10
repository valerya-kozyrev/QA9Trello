import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.BoardsPageHelper;
import src.com.company.pages.CurrentBoardHelper;
import src.com.company.pages.HomePageHelper;
import src.com.company.pages.LoginPageHelper;

import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

public class CurrentBoardTest extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;

    @BeforeMethod
    public void initTest() {

        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa9Board = new CurrentBoardHelper(driver, "QA9");

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();

        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        boardsPage.clickOnBoardButton();
        boardsPage.openCurrentBoard();
        qa9Board.waitUntilCurrentBoardIsLoaded();
    }

    @Test
    public void addNewListTest() {

        int numberOfListsBefore = qa9Board.getListSize();

        qa9Board.createNewList("New List");

        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore + 1);
    }

    @Test
    public void addNewCardTest() {

        int numberOfCardsBefore = qa9Board.getCardSize();
        List<WebElement> list = qa9Board.getNameElements(By.cssSelector(".js-list-content"));

        if (list.size() == 0) {
            qa9Board.createNewList("New List");
        }
        qa9Board.createNewCard();
        int numberOfCardsAfter = qa9Board.getCardSize();
        Assert.assertEquals(numberOfCardsAfter,numberOfCardsBefore + 1);
    }

    @Test
    public void copyListTest() {

        int numberOfListsBefore = qa9Board.getListSize();

        if (numberOfListsBefore == 0) {
            qa9Board.createNewList("New List");

            numberOfListsBefore++;
        }
        qa9Board.copyList("Changed");

        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore + 1);
    }

    @Test
    public void archiveListTest() {

        int numberOfListsBefore = qa9Board.getListSize();

        if (numberOfListsBefore == 0) {
            qa9Board.createNewList("Other List");

            numberOfListsBefore++;
        }
        qa9Board.archiveList();

        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore - 1);
    }

    @Test
    public void archiveNameListTest() {
        String nameList = "add";
        int numberOfListsBefore = qa9Board.getListSize();
        int number = qa9Board.getNumberOfElementWithName(nameList);
        if (number == -1) {
            qa9Board.createNewList("add");

            number = numberOfListsBefore;
            numberOfListsBefore++;
        }
        qa9Board.archiveNameList(number);

        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore - 1);
    }
}

//    @Test
//    public void archiveNameListTest() {
//
//        int numberOfListsBefore = currentBoard.getListSize();
//        List<WebElement> listWithName = getNameElements(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]"));
//
//        if (listWithName.size() == 0) {
//
//            currentBoard.createNewList("Other List");
//
//            numberOfListsBefore++;
//        }
//        waitUntilAllElementsArePresent(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]"), 10);
//
//        // click on the list menu
//        waitUntilElementIsClickable(By.className("list-header-extras-menu"), 10);
//        driver.findElement(By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'Other List')]]//*[@class='list-header-extras']")).click();
//
//        // click on "Archive this list"
//        currentBoard.clickOnArchiveList();
//
//        int numberOfListsAfter = currentBoard.getListSize();
//        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore - 1);
//    }

