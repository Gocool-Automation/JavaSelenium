package base;

import Utilities.ExtentManager;
import Utilities.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;


public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method method) {
        System.setProperty("webdriver.chrome.driver", "E:\\Learning\\JavaAutomation\\JavaSelenium\\src\\main\\java\\Drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        ExtentTestManager.startTest(method.getName());
    }

    public String takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            String path = System.getProperty("user.dir") +
                    "/reports/screenshots/" + testName + ".png";
            byte[] source = ts.getScreenshotAs(OutputType.BYTES);

            java.nio.file.Files.write(java.nio.file.Paths.get(path), source);
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest()
                    .fail("Test Failed: " + result.getThrowable());

            String screenshotPath = takeScreenshot(result.getName());
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().pass("Test Passed");
        }
        flushReports();
        driver.quit();


    }

    public void flushReports() {
        ExtentManager.getExtent().flush();
    }

}
