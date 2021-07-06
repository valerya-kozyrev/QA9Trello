package src.com.company.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPageHelper extends PageBase{

    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public void openPage() {
        // click on "Log in" button
        waitUntilElementIsClickable(By.cssSelector(".text-primary"), 20);
        driver.findElement(By.cssSelector(".text-primary")).click();
    }

    public void waitUntilLoginPageIsLoaded(){

        waitUntilElementIsClickable(By.id("login"), 10);
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
        // fill in email field
        waitUntilElementIsClickable(By.id("login"), 20);
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, value);
    }

    public void fillInPasswordField(String value) {
        // fill in password field
        waitUntilElementIsClickable(By.id("password"), 20);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitLogin() {
        // press log in button
        waitUntilElementIsClickable(By.id("login"), 20);
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
    }

    public void submitLoginAtlassian() {
        // press 'Log in with Atlassian' button
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();
    }

    public void fillInPasswordAtlassianField(String value) {
        // fill in password field
        waitUntilElementIsClickable(By.id("password"), 20);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitPasswordAtlassian() {
        // press log in button
        waitUntilElementIsClickable(By.id("login-submit"), 20);
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
    }

    public String getErrorMessage(){
        // output error-message
        waitUntilElementIsVisible(By.cssSelector("p.error-message"), 10);
        return driver.findElement(By.cssSelector("p.error-message")).getText();
    }

}
