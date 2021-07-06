package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardsPageHelper extends PageBase {
    public BoardsPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilBoardPageIsLoaded() {
        waitUntilElementIsClickable(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]"), 20);
    }

    public String getBoardsButtonName() {
        return driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/span[2]")).getText();
    }
}
