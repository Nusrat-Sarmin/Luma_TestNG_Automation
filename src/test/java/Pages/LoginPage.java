package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "j_username")
    WebElement txtEmail;
    @FindBy(name = "j_password")
    WebElement txtPassword;
    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement btnLogin;

   @FindBy(xpath = "//div[contains(text(),'User name and password do not match')]")
    public WebElement lblCredentials;

   @FindBy(tagName = "a")
   public List<WebElement> linkLogin;
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String emailAddress, String loginPass) {
        txtEmail.sendKeys(emailAddress);
        txtPassword.sendKeys(loginPass);
        btnLogin.click();
    }

    public void doLoginWithInvalidCreds(String emailAddress, String loginPass){
        txtEmail.clear();
        txtEmail.sendKeys(emailAddress);
        txtPassword.clear();
        txtPassword.sendKeys(loginPass);
        btnLogin.click();
    }
}
