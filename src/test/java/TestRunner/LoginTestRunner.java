package TestRunner;

import Pages.LoginPage;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    String emailAddress;
    String loginPass;

    @Test(priority = 1, description = "User can not login with wrong creds")
    public void doLoginWithInvalidCreds() throws InterruptedException {
        loginPage=new LoginPage(driver);
        Thread.sleep(5000);
        loginPage.linkLogin.get(1).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(3000);
        emailAddress= "wrongemail";
        loginPass="password";
        loginPage.doLoginWithInvalidCreds(emailAddress,loginPass);
        Thread.sleep(2000);
        String validationErrorActual = loginPage.lblCredentials.getText();
        String validationErrorExpected="User name and password do not match";

        Assert.assertEquals(validationErrorActual,validationErrorExpected);
        Allure.description("This test verifies the login functionality with invalid credentials. It ensures that the" +
                " system rejects the login attempt and shows the appropriate error message.");
    }
    @Test(priority = 2, description = "User log in successfully")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        Thread.sleep(2000);
        loginPage = new LoginPage(driver);
        Utils utils = new Utils();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        utils.getUserCreds(utils.getUserCount());
        loginPage.doLogin(utils.getEmail(), utils.getPassword());
        Thread.sleep(2000);

        String urlActual = loginPage.navBarMyAccount.getText();
        String urlExpected = "MY ACCOUNT";
        Assert.assertTrue(urlActual.contains(urlExpected));
        Allure.description("This test verifies the login functionality with valid credentials. It ensures that a " +
                "user can successfully log in and be redirected to the dashboard or home page.");
    }


}
