import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.com.company.pages.HomePageHelper;

public class HomePageTests extends TestBase{

//    @BeforeMethod
//    public  void initTest(){
//    }

    @Test
    public  void verifyApplTest(){
        Assert.assertTrue(homePage.isCorrectPage());
    }
}
