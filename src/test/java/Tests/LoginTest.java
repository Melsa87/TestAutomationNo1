package Tests;

import Base.WebTestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends WebTestBase {


    public void loginActions(String email, String password) {
        loginActions.clickLoginButton();
        loginActions.enterEmailAddress(email);
        loginActions.enterPassword(password);
        loginActions.clickSubmitButton();
    }


    @Test(dataProvider = "loginData")
    public void validLoginTest(String email, String password) {


        loginActions(email, password);

        loginActions.verifyLoginSuccess("Here's an overview of your learning journey");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"melsa@gmail.co.za", "Pass@1234"}
        };
    }
}