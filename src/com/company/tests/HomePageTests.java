import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.HomePageHelper;

public class HomePageTests extends TestBase{

    HomePageHelper homePage;

    @BeforeMethod
    public  void initTest(){
        homePage = new HomePageHelper(driver);
        homePage.waitUntilBeforeLoginPageIsLoaded();
    }

    @Test
    public  void verifyApplTest(){
        Assert.assertTrue(homePage.isCorrectPage());
    }
}
