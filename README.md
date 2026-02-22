# Mel Automation - Test Suite

Simple beginner-friendly Selenium automation project using **static WebDriver** management with TestNG.

## Project Structure

```
src/test/java/
├── Base/
│   └── WebTestBase.java          # Base test class with lifecycle (setUp/tearDown)
├── Basics/
│   ├── TestNo1.java              # Login test extending WebTestBase
│   └── StaticDriverDemo.java      # Simple demo using static driver
├── Pages/
│   └── LoginActions.java          # Page Object Model (future)
└── Utilities/
    └── BrowserSetup.java          # Static WebDriver factory & management
```

## Key Features

- **Static WebDriver**: Single shared browser instance across tests (managed in `BrowserSetup`)
- **WebDriverManager**: Automatically handles ChromeDriver and EdgeDriver binaries
- **TestNG Lifecycle**: `@BeforeClass` initializes driver, `@AfterClass` quits it
- **Simple API**: `BrowserSetup.startBrowser(browser, url)` and `BrowserSetup.quitBrowser()`

## Dependencies

- **Selenium 4.40.0**
- **TestNG 7.12.0**
- **WebDriverManager 6.1.0** (handles driver binaries automatically)
- **Java 21**

## How to Run Tests

### In IDE (Recommended)
1. Open project in IntelliJ IDEA or Eclipse
2. Right-click `TestNo1.java` → **Run As → TestNG Test**

### From Command Line
```bash
cd MelAutomation2026
mvn clean test
```

### Run Specific Test
```bash
mvn test -Dtest=TestNo1
```

## Usage Example

### Using WebTestBase (Recommended for Tests)
```java
public class TestNo1 extends WebTestBase {
    @Test
    public void myTest() {
        // driver is initialized automatically by setUp()
        driver.findElement(By.id("some-id")).click();
        // driver is quit automatically by tearDown()
    }
}
```

### Direct Usage
```java
WebDriver driver = BrowserSetup.startBrowser("chrome", "https://example.com");
try {
    // do automation
} finally {
    BrowserSetup.quitBrowser();
}
```

## Supported Browsers

- **Chrome** (default): `BrowserSetup.startBrowser("chrome", url)`
- **Edge** (fallback): `BrowserSetup.startBrowser("edge", url)`

## Notes for Beginners

- **WebDriverManager**: Automatically downloads and manages the correct driver version. No manual setup needed!
- **Static Driver**: All tests share one browser instance. This is fast but means tests run sequentially.
- **TestNG Lifecycle**: `@BeforeClass` runs once per test class, `@AfterClass` runs after all tests in that class complete.
- **Safe Quit**: `quitBrowser()` checks if driver exists before quitting, so it's safe to call multiple times.

## Troubleshooting

**"Cannot find ChromeDriver"**
- WebDriverManager should handle this. Try: `mvn clean install`
- Ensure Maven can access the internet to download drivers.

**Tests hang or don't quit**
- Check that tests extend `WebTestBase` or explicitly call `BrowserSetup.quitBrowser()`
- Use Ctrl+C to kill stuck Java processes.

**Import errors in IDE**
- Right-click project → **Maven → Update Project** (or reload)
- This downloads dependencies and refreshes the IDE.

## Next Steps

- Add more tests to `Basics/` folder
- Implement Page Object Model in `Pages/` (LoginActions, etc.)
- Add waiting strategies and better error handling
- Configure CI/CD (GitHub Actions, Jenkins) to run tests headless

---

**Happy testing!** 🚀

