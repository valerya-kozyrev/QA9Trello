package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenuPageHelper extends PageBase {

    public MenuPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenuPage() {
        driver.findElement(By.cssSelector(".js-open-header-member-menu")).click();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'header-member-menu-profile']"),5);
    }

    public String getProfileVisibilityMenuName() {
        return driver.findElement(By.xpath("//a[@data-test-id = 'header-member-menu-profile']")).getText();
    }

    public void openActivityList() {
        clickActivityButton();
        findLastActivityText();
    }
    public int getActivityListSize() {
        List<WebElement> activityList = driver.findElements(By.xpath("//div[@class='phenom mod-attachment-type']"));//div[@class='phenom mod-attachment-type']
        return activityList.size();
    }

    public String findLastActivityText() {
//        WebElement lastActivity = driver.findElement(By.xpath("///div[@class='phenom mod-attachment-type'][.//*[contains(text(),' added list Verify List to ')]]"));
        WebElement lastActivity = driver.findElement(By.xpath("//div[@class='phenom mod-attachment-type'][1]"));
        return lastActivity.getText();
    }

    public void returnToPreviousPage(){
        driver.navigate().back();

    }
    public void clickActivityButton() {
        WebElement activityButton = driver.findElement(By.xpath("//*[@href='/valerya_kozyrev/activity']"));
        activityButton.click();
        waitUntilElementIsClickable(By.className("//div[@class='phenom mod-attachment-type']"),5);
    }
}
