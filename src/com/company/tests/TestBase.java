import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import src.com.company.pages.HomePageHelper;

public class TestBase {

    public static final String PASSWORD = "trello0909";
    public static final String LOGIN = "lerkucij@gmail.com";

    HomePageHelper homePage;

    WebDriver driver;

    @BeforeMethod
    public void startUp() {
        driver = new ChromeDriver();
        driver.get("https://trello.com");

        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilBeforeLoginPageIsLoaded();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}