import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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

    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");

        loginPage
                .openPage()
                .waitUntilLoginPageIsLoaded()
                .loginAtlassian(LOGIN, PASSWORD);
        boardsPage
                .waitUntilBoardPageIsLoaded()
                .openBoardsMenu();
        qa9Board
                .openPage()
                .waitUntilCurrentBoardIsLoaded();
    }

    @Test
    public void addNewListTest() {
        int numberOfListsBefore = qa9Board.getListSize();
        qa9Board.createNewList("New List");
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
    }

    @Test
    public void addNewCardTest() {
        qa9Board.getNumberOfListsBefore("New Card List");
        int numberOfCardsBefore = qa9Board.getCardSize();
        qa9Board.createNewCard();
        int numberOfCardsAfter = qa9Board.getCardSize();
        Assert.assertEquals(numberOfCardsAfter, numberOfCardsBefore + 1);
    }

    @Test
    public void copyListTest() {
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("New List");
        qa9Board.copyList("Changed");
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore + 1);
    }

    @Test
    public void archiveListTest() {
        int numberOfListsBefore = qa9Board.getNumberOfListsBefore("Other List");
        qa9Board.archiveList();
        int numberOfListsAfter = qa9Board.getListSize();
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
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
        Assert.assertEquals(numberOfListsAfter, numberOfListsBefore - 1);
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

