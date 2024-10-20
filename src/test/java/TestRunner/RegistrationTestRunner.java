package TestRunner;

import Pages.LoginPage;
import Pages.RegistrationPage;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    RegistrationPage registrationPage;
    Utils utils;
    String firstName;
    String lastName;
    String email;

    String invalidEmail;
    String password;
    String confirmPassword;
    public void basicInfo(){
        utils = new Utils();
        Faker faker = new Faker();
        firstName= faker.name().firstName();
        lastName = faker.name().lastName();
        email = "nusrat" + utils.generateRandomNumber(000, 999) + "@gmail.com";
        invalidEmail="nusrat89";
        password ="admin@123";
        confirmPassword="admin@123";
    }
    @Test(priority = 1, description = "User can not registration with wrong email address")
    public void doRegistrationWithInvalidEmail() throws InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.linkLogin.get(1).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        registrationPage.linkNewAccount.click();
        Thread.sleep(3000);
        basicInfo();
        registrationPage.doRegistrationWithInvalidEmail(firstName, lastName, invalidEmail, password, confirmPassword);

        String validationErrorActual = registrationPage.lblCredentials1.get(0).getText();
        String validationErrorExpected="That does not appear to be a valid email address";

        Assert.assertTrue(validationErrorActual.contains(validationErrorExpected));
        Allure.description("This test verifies the system's behavior when registering with an invalid email format. " +
                "The system should not accept invalid emails and must show an appropriate error message.");
    }

    @Test(priority = 2, description = "Registration in successfully")
    public void doRegistration() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        utils = new Utils();
        registrationPage.clearCredential();
        basicInfo();
        Thread.sleep(2000);
        registrationPage.doRegistration(firstName, lastName, email, password, confirmPassword);

        Thread.sleep(2000);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "signup";
        Assert.assertTrue(urlActual.contains(urlExpected));

        utils.saveJsonList(firstName,lastName,email, password);
        Allure.description("This test verifies the registration process with valid input data. It ensures that a user" +
                " can successfully register and receive confirmation.");

    }
}
