package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPageHelper extends PageBase{

    @FindBy(css=".text-primary")
    WebElement loginIcon;
    @FindBy(id="login")
    WebElement loginButton;
    @FindBy(id="user")
    WebElement emailField;
    @FindBy(id="password")
    WebElement passwordField;
    @FindBy(xpath = "//input[@value='Log in with Atlassian']")
    WebElement loginWithAtlassianButton;
    @FindBy(id="login-submit")
    WebElement submitLoginWithAtlassianButton;
    @FindBy(css="p.error-message")
    WebElement errorMessage;

    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public LoginPageHelper openPage() {
        waitUntilElementIsClickable(loginIcon, 20);
        loginIcon.click();
        return this;
    }

    public LoginPageHelper waitUntilLoginPageIsLoaded(){
        waitUntilElementIsClickable(loginButton, 10);
        return this;
    }

    public void login(String login, String password) {
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLogin();
    }

    public void loginAtlassian(String login, String password) {
        fillInEmailField(login);
        submitLoginAtlassian();
        fillInPasswordAtlassianField(password);
        submitPasswordAtlassian();
    }

    public void fillInEmailField(String value) {
        waitUntilElementIsClickable(loginButton, 20);
        editField(emailField, value);
    }

    public void fillInPasswordField(String value) {
        waitUntilElementIsClickable(passwordField, 20);
        editField(passwordField, value);
    }

    public void submitLogin() {
        waitUntilElementIsClickable(loginButton, 20);
        loginButton.click();
    }

    public void submitLoginAtlassian() {
        waitUntilElementIsClickable(loginWithAtlassianButton, 10);
        loginWithAtlassianButton.click();
    }

    public void fillInPasswordAtlassianField(String value) {
        waitUntilElementIsClickable(passwordField, 20);
        editField(passwordField, value);
    }

    public void submitPasswordAtlassian() {
        waitUntilElementIsClickable(submitLoginWithAtlassianButton, 20);
        submitLoginWithAtlassianButton.click();
    }

    public String getErrorMessage(){
        waitUntilElementIsVisible(errorMessage, 10);
        return errorMessage.getText();
    }

}
