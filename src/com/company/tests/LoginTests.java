import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    @BeforeMethod
    public void initTest() {
        // click on "Log in" button
        waitUntilElementIsClickable(By.cssSelector(".text-primary"), 20);
        driver.findElement(By.cssSelector(".text-primary")).click();
        waitUntilElementIsClickable(By.id("login"), 10);
    }

    @Test
    public void loginNegativeTest() {

        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, "email");

        // fill in password field
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, "password");

        // press log in button
        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        // output error-message
        waitUntilElementIsVisible(By.cssSelector("p.error-message"), 10);
        Assert.assertEquals(driver.findElements(By.cssSelector("p.error-message")).get(0).getText(),
                "There isn't an account for this username",
                "The error message is not correct");
    }

    @Test
    public void loginPositiveTest() {

        // fill in email field
        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, LOGIN);

        // press 'Log in with Atlassian' button
        waitUntilElementIsClickable(By.xpath("//input[@value='Log in with Atlassian']"), 10);
        driver.findElement(By.id("login")).click();
//        driver.findElement(By.xpath("//input[@value='Log in with Atlassian']")).click();

        // fill in password field
        waitUntilElementIsClickable(By.id("login-submit"), 10);
//        waitUntilElementIsClickable(By.id("password"), 10);
        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, PASSWORD);

        // press login button
        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();

        //assert login
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'Boards')]"), 20);
        Assert.assertEquals(driver.findElements(By.xpath("//span[contains(text(),'Boards')]")).get(0).getText(),
                "Boards", "Name of the button is not 'Boards'");

    }
}