package TestRunner;

//import Pages.AddToCart;

import Pages.AddToCartPage;
import Pages.LoginPage;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class AddToCartTestRunner extends Setup {
    LoginPage loginPage;
    AddToCartPage addToCartPage;
    String emailAddress;
    String loginPass;
    String shippingAddress1stName;
    String shippingAddressLstName;
    String shippingAddressOne;

    String shippingCity1;

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        driver.get("https://luma.enablementadobe.com/");
        loginPage = new LoginPage(driver);
        Utils utils = new Utils();
        Thread.sleep(3000);
        loginPage.linkLogin.get(1).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        utils.getUserCreds(utils.getUserCount());
        loginPage.doLogin(utils.getEmail(), utils.getPassword());
    }

    @Test
    public void doAddToCart() {
        //Thread.sleep(3000);
        addToCartPage = new AddToCartPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        shippingAddress1stName="Nusrat";
        shippingAddressLstName="Sharmin";
        shippingAddressOne="Dhanmondi";
        shippingCity1="Dhaka";
        addToCartPage.doAddToCart(shippingAddress1stName,shippingAddressLstName,shippingAddressOne,shippingCity1);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "luma";
        Assert.assertTrue(urlActual.contains(urlExpected));

    }
}
