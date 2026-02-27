package Utilities;


import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserSetup {

    public static @NotNull WebDriver startBrowser(String browserChoice, String url) {
        WebDriver driver;

        if (browserChoice.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserChoice.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            System.out.println("Browser '" + browserChoice + "' not supported. Defaulting to Chrome...");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    public void closeBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void initElements(WebDriver driver, Object pages){

    }


}