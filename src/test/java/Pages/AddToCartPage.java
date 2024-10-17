package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddToCartPage {
    @FindBy(className = "btn-primary")
    public List<WebElement> txtCategory;
    @FindBy(className = "we-ProductsGrid-item-link")
    public List<WebElement> selectProduct;
    @FindBy(className = "btn-primary")
    public List<WebElement> addToCart;
    @FindBy(css = ".btn.btn-primary.btn-action.pull-right")
    public WebElement checkOut;
    @FindBy(className = "btn-primary")
    public List<WebElement> checkOutAgain;
    @FindBy(css = ".cm-btn.cm-btn-danger.cn-decline")
    public WebElement btnCookies;
    @FindBy(id = "form-text-260762246")
    public WebElement shippingAddressFirstName;
    @FindBy(id = "form-text-1038908588")
    public WebElement shippingAddressLastName;
    @FindBy(id="form-text-1872431321")
    public WebElement shippingAddress1;
    @FindBy(id="form-text-1526463330")
    public WebElement shippingCity;
    @FindBy(id = "form-button-1573303396")
    public WebElement btnContinue;
    @FindBy(className = "cmp-form-button")
    public WebElement placeOrder;
    WebDriver driver;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doAddToCart(String shippingAddress1stName,String shippingAddressLstName,String shippingAddressOne,String shippingCity1) {
        btnCookies.click();
        txtCategory.get(1).click();
        selectProduct.get(0).click();
        addToCart.get(0).click();
        checkOut.click();
        checkOutAgain.get(0).click();
        shippingAddressFirstName.sendKeys(shippingAddress1stName);
        shippingAddressLastName.sendKeys(shippingAddressLstName);
        shippingAddress1.sendKeys(shippingAddressOne);
        shippingCity.sendKeys(shippingCity1);
        btnContinue.click();
        placeOrder.click();
    }
}
