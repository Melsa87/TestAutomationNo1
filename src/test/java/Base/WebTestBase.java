package Base;
import Utilities.BrowserSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class WebTestBase {
    protected WebDriver driver;
    // Define your URL here for easy access
    String testUrl = "https://ndosisimplifiedautomation.vercel.app";
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Now passing the variable 'testUrl' into the method
        driver = BrowserSetup.startBrowser("chrome", testUrl);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}