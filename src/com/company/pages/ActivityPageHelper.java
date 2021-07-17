package src.com.company.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {
    @FindBy (css = ".phenom-desc")
    List<WebElement> activityRecordsList;
//    @FindBy(xpath = "//div[@class='phenom mod-attachment-type']")
//    List<WebElement> activityList;
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type'][1]")
    WebElement lastActivityElement;

    public ActivityPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded(){
        waitUntilAllElementsAreVisible(activityRecordsList,10);
//        waitUntilAllElementsAreVisible(activityList, 5);
    }

    public int getActivityListSize() {
//        return activityList.size();
        return activityRecordsList.size();
    }


    public void returnToPreviousPage() {
        driver.navigate().back();
    }

    public String findLastActivityText() {
//        WebElement lastActivity = driver.findElement(By.xpath("///div[@class='phenom mod-attachment-type'][.//*[contains(text(),' added list Verify List to ')]]"));
        return lastActivityElement.getText();
    }
}
