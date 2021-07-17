import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.*;

public class MenuPageTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilLoginPageIsLoaded()
                .loginAtlassian(LOGIN, PASSWORD);
        boardsPage
                .waitUntilBoardPageIsLoaded()
                .openBoardsMenu();
        qa9Board.openPage()
                .waitUntilCurrentBoardIsLoaded();

        menuPage
                .openMenuPage()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void profileVisibilityMenuExistsTest() {
        Assert.assertEquals(menuPage.getProfileVisibilityMenuName(), "Profile and visibility");
    }
}
