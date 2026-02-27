package Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
public class BrowserSetup {

    //The compiler needs to know what 'driver' is

    static WebDriver driver;
    public static WebDriver startBrowser(@org.jetbrains.annotations.NotNull String browserChoice, String url) {
        if (browserChoice.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browserChoice.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else {
            System.out.println("Browser not supported. Defaulting to Chrome...");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
}