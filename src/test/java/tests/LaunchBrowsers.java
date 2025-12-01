package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static Utilities.ScreenshotUtil.takeFullScreenshot;
import static Utilities.ScreenshotUtil.takeScreenshot;

public class LaunchBrowsers {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "E:\\Learning\\JavaAutomation\\JavaSelenium\\src\\main\\java\\Drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test(enabled = true, priority = 0)
    public void launchBrowser() throws IOException {

        driver.get("https://www.google.com");

        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Pass");
            takeScreenshot(driver, "screenshots/");
            takeFullScreenshot(driver, "screenshots/");
        } else System.out.println("Fail");
    }

    @Test(enabled = true, priority = 1)
    public void launchBrowser1() {
        String expectedURL = "https://www.google.com";
        driver.get(expectedURL);


        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL, "URL does not match");
        /*if (expectedURL.equals(actualURL)) {
            System.out.println("True");
        } else
            System.out.println("Fail");*/

    }

    @Test(enabled = true, priority = 2)
    public void driverMethods() throws InterruptedException {
        driver.get("https://www.google.com");

        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().forward();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='FPdoLc lJ9FBc']/center/input[@value='Google Search']")));
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.manage().window().fullscreen();
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }
}

