package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageHelper extends PageBase{


    public HomePageHelper(WebDriver driver){

        this.driver = driver;
    }

    public boolean isCorrectPage() {
        return driver.findElement(By.cssSelector(".text-primary")).getText().equals("Log in");

    }
    public void waitUntilBeforeLoginPageIsLoaded(){

        waitUntilElementIsClickable(By.cssSelector(".text-primary"), 20);
    }


}
