package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Utils;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.get("https://luma.enablementadobe.com/");
    }

    @AfterMethod
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils util = new Utils();
                util.takeScreenShot(driver);
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }

        }

    }

    @AfterTest
    public void quitBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            driver.quit();
        }

    }
}
