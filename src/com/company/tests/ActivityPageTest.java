import org.openqa.selenium.By;
import src.com.company.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityPageTest extends TestBase{
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardHelper qa9Board;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9Board = new CurrentBoardHelper(driver, "QA9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver,ActivityPageHelper.class);

        homePage.waitUntilBeforeLoginPageIsLoaded();
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

        menuPage
                .openMenuPage()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void verifyAddNewListTest() {
        menuPage.clickActivityButton();
        activityPage.waitUntilPageIsLoaded();
        int sizeActivityBefore = activityPage.getActivityListSize();
        activityPage.returnToPreviousPage();
        qa9Board.waitUntilCurrentBoardIsLoaded();
        qa9Board.createNewList("Activity List");
        menuPage.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.clickActivityButton();
        activityPage.waitUntilPageIsLoaded();
        activityPage.findLastActivityText();
        int sizeActivityListAfter = activityPage.getActivityListSize();
        Assert.assertEquals(sizeActivityListAfter, sizeActivityBefore + 1);
        Assert.assertEquals(activityPage.findLastActivityText(),
                driver.findElement(By.xpath("//div[@class='phenom mod-attachment-type'][1]")).getText(),
                "Wrong activity");
    }

}
