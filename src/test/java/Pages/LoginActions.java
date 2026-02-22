package Pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
/**
 * LoginActions - Page Object Model for Login Page
 * Contains all login-related actions and locators
 */
public class LoginActions {
    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait longWait;
    // Locators (public for visibility)
    public By loginButton = By.xpath("//button[contains(.,'Login')]");
    public By emailField = By.id("login-email");
    public By passwordField = By.id("login-password");
    public By submitButton = By.id("login-submit");
    public By welcomeHeader = By.xpath("//*[@id='app-main-content']/section/div/h2");
    /**
     * Constructor - Initializes WebDriver and WebDriverWait
     */
    public LoginActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    /**
     * Click the Login button to open the login form
     */
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        System.out.println("[ACTION] Clicked Login Button");
    }
    /**
     * Enter email and password credentials
     */
    public void enterCredentials(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        System.out.println("[ACTION] Entered Email: " + email);
        driver.findElement(passwordField).sendKeys(password);
        System.out.println("[ACTION] Entered Password");
    }
    /**
     * Click the Submit button to login
     */
    public void clickSubmit() {
        driver.findElement(submitButton).click();
        System.out.println("[ACTION] Clicked Submit Button");
    }
    /**
     * All-in-one login shortcut (click button -> enter credentials -> submit)
     */
    public void login(String email, String password) {
        clickLoginButton();
        enterCredentials(email, password);
        clickSubmit();
    }
    /**
     * Verify that login was successful by checking the welcome message
     */
    public void verifyLoginSuccess(String expectedMessage) {
        WebElement header = longWait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeader));
        String actualMessage = header.getText();
        System.out.println("[DEBUG] Expected: [" + expectedMessage + "]");
        System.out.println("[DEBUG] Actual: [" + actualMessage + "]");
        if (!actualMessage.equals(expectedMessage)) {
            throw new AssertionError("FAILED: Expected [" + expectedMessage + "] but got [" + actualMessage + "]");
        }
        System.out.println("[VERIFY] Login successful! Message: " + actualMessage);
    }
}