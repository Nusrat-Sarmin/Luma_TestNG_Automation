package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {

    @FindBy(name = "fname")
    WebElement txtFirstName;
    @FindBy(name = "lname")
    WebElement txtLastName;
    @FindBy(css = "[type=email]")
    WebElement txtEmail;
    @FindBy(css = "[placeholder=Password]")
    WebElement txtPassword;
    @FindBy(css = "[type=password]")
    List<WebElement> confirmPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(xpath = "//div[contains(text(),'That does not appear to be a valid email address')]")
    public List<WebElement> lblCredentials1;
    @FindBy(tagName = "a")
    public List<WebElement> linkLogin;
    @FindBy(xpath = "//a[contains(text(),'new account')]")
    public WebElement linkNewAccount;
//    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doRegistration(String firstName, String lastName, String txtEmail1, String txtPassword1, String confirmPassword2) {
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(txtEmail1);
        txtPassword.sendKeys(txtPassword1);
        confirmPassword.get(1).sendKeys(confirmPassword2);
        btnSubmit.click();
    }

    public void doRegistrationWithInvalidEmail(String firstName, String lastName, String txtEmail1, String txtPassword1, String confirmPassword2){
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        txtEmail.sendKeys(txtEmail1);
        txtPassword.sendKeys(txtPassword1);
        confirmPassword.get(1).sendKeys(confirmPassword2);
        btnSubmit.click();
    }
    public void clearCredential() {
        txtFirstName.sendKeys(Keys.CONTROL, "a");
        txtFirstName.sendKeys(Keys.BACK_SPACE);
        txtLastName.sendKeys(Keys.CONTROL, "a");
        txtLastName.sendKeys(Keys.BACK_SPACE);
        txtEmail.sendKeys(Keys.CONTROL, "a");
        txtEmail.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL, "a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
        confirmPassword.get(1).sendKeys(Keys.CONTROL, "a");
        confirmPassword.get(1).sendKeys(Keys.BACK_SPACE);

    }

}
