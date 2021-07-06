import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.BoardsPageHelper;
import src.com.company.pages.HomePageHelper;
import src.com.company.pages.LoginPageHelper;


public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTest() {
        homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        boardsPage = new BoardsPageHelper(driver);

        homePage.waitUntilBeforeLoginPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilLoginPageIsLoaded();
    }

    @Test
    public void loginNegativeTest() {
        loginPage.login("email","password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username",
                "The error message is not correct");
    }

    @Test
    public void loginPositiveTest() {

        loginPage.loginAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilBoardPageIsLoaded();
        Assert.assertEquals(boardsPage.getBoardsButtonName(),
                "Boards", "Name of the button is not 'Boards'");

    }
}