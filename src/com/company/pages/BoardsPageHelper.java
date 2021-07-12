package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {

    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']/span[2]")
    WebElement boardsIcon;
    @FindBy(xpath = "//a[@data-test-id = 'home-team-boards-tab']")
    WebElement boardsMenuTab;
    @FindBy(xpath = "//h3")
    WebElement e;

    public BoardsPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilBoardPageIsLoaded(){
        waitUntilElementIsClickable(boardsIcon, 20);
        return this;
    }

    public String getBoardsButtonName(){
        return boardsIcon.getText();
    }

    public void openBoardsMenu(){
        waitUntilElementIsClickable(boardsMenuTab,10);
        boardsMenuTab.click();
        waitUntilElementTextIs(e,"Your Workspace boards",10);
    }
}
