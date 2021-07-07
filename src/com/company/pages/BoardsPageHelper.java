package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void clickOnBoardButton(){
        //  go to the 'Boards' tab
        waitUntilElementIsClickable(By.xpath("//a[@data-test-id = 'home-team-boards-tab']"),10);
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click();
    }

    public void openCurrentBoard(){
        waitUntilElementTextIs(By.xpath("//h3"),"Your Workspace boards",10);
        waitUntilElementIsClickable(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA9']]"),10);
        // open 'QA Haifa9' board
        WebElement qa9Board = driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA9']]"));
        qa9Board.click();
    }
}
