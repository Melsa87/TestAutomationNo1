package Basics;

import Base.WebTestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

@Test

public class TestNo1 extends WebTestBase {

    @Test
    public void myFirstAutomationTest() {
        // driver is initialized by WebTestBase.setUp()
        // No need to create a new driver or navigate again - already done

        driver.findElement(By.xpath("//*[@id=\"app-root\"]/nav/div[1]/div[3]/button/span[2]")).click();

        //Enter Credentials
        driver.findElement(By.id("login-email")).sendKeys("melsa@gmail.co.za");
        driver.findElement(By.id("login-password")).sendKeys("pass@1234");

        // Click the submit button
        driver.findElement(By.id("login-submit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"app-main-content\"]/section/div[1]/h2")
        )).isDisplayed();

    }
}
