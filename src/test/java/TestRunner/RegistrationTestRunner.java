package TestRunner;

import Pages.LoginPage;
import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    RegistrationPage registrationPage;
    String firstName;
    String lastName;
    String txtEmail1;

    String invalidEmail;
    String txtPassword1;
    String confirmPassword2;
    @Test(priority = 1, description = "User can not registration with wronge emailaddress")
    public void doRegistrationWithInvalidEmail() throws InterruptedException {
        driver.get("https://luma.enablementadobe.com/");
        registrationPage = new RegistrationPage(driver);
        registrationPage.linkLogin.get(1).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        registrationPage.linkNewAccount.click();
        Thread.sleep(3000);
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        invalidEmail="nusrat89";
        txtPassword1="admin@123";
        confirmPassword2="admin@123";
        registrationPage.doRegistrationWithInvalidEmail(firstName, lastName, invalidEmail, txtPassword1, confirmPassword2);

        String validationErrorActual = registrationPage.lblCredentials1.get(0).getText();
        String validationErrorExpected="That does not appear to be a valid email address";

        Assert.assertEquals(validationErrorActual,validationErrorExpected);
        //Allure.description("User can not login with wrong creds");
    }

    @Test(priority = 2, description = "Registration in successfully")
    public void doRegistration() throws IOException, ParseException {
        registrationPage = new RegistrationPage(driver);
        Utils utils = new Utils();
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        txtEmail1 = "nusrat" + utils.generateRandomNumber(000, 999) + "@gmail.com";
        txtPassword1 = "admin@123";
        confirmPassword2 = "admin@123";
        registrationPage.doRegistration(firstName, lastName, txtEmail1, txtPassword1, confirmPassword2);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "signup";
        Assert.assertTrue(urlActual.contains(urlExpected));
        utils.saveJsonList(txtEmail1, txtPassword1);
    }
}
