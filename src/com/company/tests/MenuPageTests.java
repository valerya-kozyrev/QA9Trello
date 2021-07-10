import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.*;

import java.util.List;

public class MenuPageTests extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTest() {

        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = new MenuPageHelper(driver);

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();

        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        boardsPage.clickOnBoardButton();
        boardsPage.openCurrentBoard();
        qa9Board.waitUntilCurrentBoardIsLoaded();

        menuPage.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
    }

    @Test
    public void profileVisibilityMenuExistsTest() {
        Assert.assertEquals(menuPage.getProfileVisibilityMenuName(), "Profile and visibility");

    }

    @Test
    public void verifyAddNewListTest() {
        menuPage.clickActivityButton();
        int sizeActivityBefore = menuPage.getActivityListSize();
        menuPage.returnToPreviousPage();
        qa9Board.createNewList("Activity List");
        menuPage.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openActivityList();
        int sizeActivityListAfter = menuPage.getActivityListSize();
        Assert.assertEquals(sizeActivityListAfter, sizeActivityBefore + 1);
        Assert.assertEquals(menuPage.findLastActivityText(), driver.findElement(By.xpath("//div[@class='phenom mod-attachment-type'][1]")).getText(), "Wrong activity");
    }
}
