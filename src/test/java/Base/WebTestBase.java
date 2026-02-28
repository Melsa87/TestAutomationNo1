package Base;

import Pages.LoginActions;
import Utilities.BrowserSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebTestBase {

    public final String url = "https://ndosisimplifiedautomation.vercel.app/";
    public final String browserChoice = "chrome";

    public WebDriver driver;
    public LoginActions loginActions;

    @BeforeMethod
    public void setUp() {
        driver = BrowserSetup.startBrowser(browserChoice, url);
        // Initialising the helper with the driver
        loginActions = new LoginActions(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            BrowserSetup.closeBrowser(driver);
        }
    }
}