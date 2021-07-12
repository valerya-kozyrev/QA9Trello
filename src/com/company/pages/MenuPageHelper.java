package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.cert.X509Certificate;
import java.util.List;

public class MenuPageHelper extends PageBase {

    @FindBy(css = ".js-open-header-member-menu")
    WebElement openMenuPage;
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profileAndVisibilityButton;
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type']")
    List<WebElement> activityList;
    @FindBy(xpath = "//div[@class='phenom mod-attachment-type'][1]")
    WebElement lastActivityElement;
    @FindBy(xpath = "//*[@href='/valerya_kozyrev/activity']")
    WebElement activityButton;

    public MenuPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public MenuPageHelper openMenuPage() {
        openMenuPage.click();
        return this;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(profileAndVisibilityButton, 5);
    }

    public String getProfileVisibilityMenuName() {
        return profileAndVisibilityButton.getText();
    }

    public void openActivityList() {
        clickActivityButton();
        findLastActivityText();
    }

    public int getActivityListSize() {
        return activityList.size();
    }

    public String findLastActivityText() {
//        WebElement lastActivity = driver.findElement(By.xpath("///div[@class='phenom mod-attachment-type'][.//*[contains(text(),' added list Verify List to ')]]"));
        return lastActivityElement.getText();
    }

    public void returnToPreviousPage() {
        driver.navigate().back();
    }

    public void clickActivityButton() {
        activityButton.click();
        waitUntilAllElementsAreVisible(activityList, 5);
    }
}
