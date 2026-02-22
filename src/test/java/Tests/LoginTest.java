package Tests;
import Base.WebTestBase;
import Pages.LoginActions;
import org.testng.annotations.Test;
@Test
public class LoginTest extends WebTestBase {
    public void validLoginTest() throws InterruptedException {
        // 1. Create LoginActions helper (pass driver from WebTestBase)
        LoginActions login = new LoginActions(driver);
        Thread.sleep(5000);
        // 2. Use the actions
        login.clickLoginButton();
        login.enterCredentials("melsa@gmail.co.za", "pass@1234");
        login.clickSubmit();
        // 3. Wait for page to load after login
        Thread.sleep(10000);
        // 4. Verify success
        login.verifyLoginSuccess("Welcome back, Melsa! \uD83D\uDC4B");
    }
}