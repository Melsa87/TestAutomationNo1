# How to Add More Tests - Quick Guide

## Adding a New Test Using Page Objects (Recommended)

### Step 1: Create a Page Object (e.g., DashboardPage.java)
```java
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    
    // Locators
    public By welcomeHeader = By.xpath("//*[@id='welcome']");
    public By logoutButton = By.id("logout-btn");
    public By userMenu = By.id("user-menu");
    
    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Actions
    public String getWelcomeText() {
        return driver.findElement(welcomeHeader).getText();
    }
    
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
    
    public void openUserMenu() {
        driver.findElement(userMenu).click();
    }
}
```

### Step 2: Create a Test Class (e.g., DashboardTest.java)
```java
package Tests;

import Base.WebTestBase;
import Pages.LoginActions;
import Pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends WebTestBase {
    
    @Test
    public void testDashboardAfterLogin() throws InterruptedException {
        // 1. Login first
        LoginActions login = new LoginActions(driver);
        login.clickLoginButton();
        Thread.sleep(2000);
        login.enterCredentials("melsa@gmail.co.za", "pass@1234");
        login.clickSubmit();
        Thread.sleep(3000);
        
        // 2. Test dashboard
        DashboardPage dashboard = new DashboardPage(driver);
        String welcomeText = dashboard.getWelcomeText();
        Assert.assertTrue(welcomeText.contains("Welcome"), 
            "Dashboard should show welcome message");
        
        // 3. Logout
        dashboard.clickLogout();
    }
}
```

### Step 3: Run the Test
Right-click `DashboardTest.java` → **Run As → TestNG Test**

---

## Adding a Test Without Page Objects (Simple)

```java
package Tests;

import Base.WebTestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SimpleTest extends WebTestBase {
    
    @Test
    public void simpleTest() {
        // driver is ready from WebTestBase
        driver.findElement(By.id("some-button")).click();
        driver.findElement(By.name("input")).sendKeys("some text");
        
        // Assert or verify
        String actualText = driver.findElement(By.id("result")).getText();
        assert actualText.equals("Expected");
    }
}
```

---

## Best Practices Checklist

- ✅ Extend `WebTestBase` in all test classes (gets automatic setUp/tearDown)
- ✅ Use Page Objects for reusable UI interactions
- ✅ One Page Object per page/component
- ✅ Use `Thread.sleep()` sparingly (use WebDriverWait instead)
- ✅ Make locators public so they're visible to maintainers
- ✅ Use meaningful method names (`clickLoginButton`, not `click1`)
- ✅ Use assertions to verify results (`Assert.assertTrue`, `Assert.assertEquals`)
- ✅ Add comments explaining complex logic
- ✅ Keep tests focused (one scenario per test method)

---

## Useful TestNG Annotations

```java
@BeforeClass      // Runs once before test class
@AfterClass       // Runs once after test class
@BeforeMethod     // Runs before each test method
@AfterMethod      // Runs after each test method
@Test             // Marks a method as a test
@Test(enabled=false)  // Disable a test
@Test(priority=1) // Run tests in priority order
@DataProvider     // Provide multiple test data sets
```

---

## Common Assertions

```java
import org.testng.Assert;

Assert.assertTrue(condition, "message if fails");
Assert.assertFalse(condition, "message if fails");
Assert.assertEquals(actual, expected, "message if fails");
Assert.assertNotEquals(actual, expected, "message if fails");
Assert.assertNull(object, "message if fails");
Assert.assertNotNull(object, "message if fails");
Assert.fail("Force test to fail with message");
```

---

## Waiting for Elements (Instead of Thread.sleep)

```java
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

// Wait for element to be visible (max 10 seconds)
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(
    By.id("element-id")
));

// Wait for element to be clickable
wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//button")
));

// Wait for text to appear
wait.until(ExpectedConditions.textToBePresentInElement(
    driver.findElement(By.id("message")), 
    "Expected text"
));
```

---

## File Organization Tips

```
Tests/
├── LoginTest.java          ← Tests related to login
├── DashboardTest.java      ← Tests related to dashboard
├── SettingsTest.java       ← Tests related to settings
└── ...

Pages/
├── LoginActions.java       ← Login page object
├── DashboardPage.java      ← Dashboard page object
├── SettingsPage.java       ← Settings page object
└── ...

Utilities/
├── BrowserSetup.java       ← Driver management
├── TestDataReader.java     ← Read test data from files/DB
├── GeneralUtilities.java   ← Helper methods
└── ...
```

---

## Quick Checklist for New Test

- [ ] Created new test class in Tests/ folder
- [ ] Class extends WebTestBase
- [ ] Test methods have @Test annotation
- [ ] Using Page Objects for UI interactions
- [ ] Added assertions/verifications
- [ ] Code compiles without errors
- [ ] Test runs successfully from IDE
- [ ] Test cleans up after itself (WebTestBase handles driver quit)

**Happy Testing!** 🚀

