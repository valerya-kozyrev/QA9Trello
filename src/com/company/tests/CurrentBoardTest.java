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
    CurrentBoardHelper currentBoard;

    @BeforeMethod
    public void initTest() {

        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        currentBoard = new CurrentBoardHelper(driver);

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();

        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        currentBoard.clickOnBoardButton();
        currentBoard.openCurrentBoard();

    }


    @Test
    public void addNewListTest() {

        int numberOfListsBefore = currentBoard.getListSize();

        currentBoard.createNewList("New List");

        int numberOfListsAfter = currentBoard.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore + 1);
    }


    @Test
    public void addNewCardTest() {

        int numberOfCardsBefore = currentBoard.getCardSize();
        List<WebElement> list = currentBoard.getNameElements(By.cssSelector(".js-list-content"));

        if (list.size() == 0) {
            currentBoard.createNewList("New List");
        }
        currentBoard.createNewCard();
        int numberOfCardsAfter = currentBoard.getCardSize();
        Assert.assertEquals(numberOfCardsAfter,numberOfCardsBefore + 1);
    }


    @Test
    public void copyListTest() {

        int numberOfListsBefore = currentBoard.getListSize();

        if (numberOfListsBefore == 0) {
            currentBoard.createNewList("New List");

            numberOfListsBefore++;
        }
        currentBoard.copyList("Changed");

        int numberOfListsAfter = currentBoard.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore + 1);
    }


    @Test
    public void archiveListTest() {

        int numberOfListsBefore = currentBoard.getListSize();

        if (numberOfListsBefore == 0) {
            currentBoard.createNewList("Other List");

            numberOfListsBefore++;
        }
        currentBoard.archiveList();

        int numberOfListsAfter = currentBoard.getListSize();
        Assert.assertEquals(numberOfListsAfter,numberOfListsBefore - 1);
    }

}

