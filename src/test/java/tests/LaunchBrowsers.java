package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;

import static Utilities.ScreenshotUtil.takeFullScreenshot;
import static Utilities.ScreenshotUtil.takeScreenshot;

public class LaunchBrowsers {

    @Test(enabled = true, priority = 0)
    public void launchBrowser() throws IOException {
        System.setProperty("webdriver.chrome.driver", "E:\\Learning\\JavaAutomation\\JavaSelenium\\src\\main\\java\\Drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Pass");
            takeScreenshot(driver, "screenshots/");
            takeFullScreenshot(driver, "screenshots/");
        } else System.out.println("Fail");
        driver.close();
    }

    @Test(enabled = false, priority = 1)
    public void launchBrowser1() {
        System.setProperty("webdriver.chrome.driver", "E:\\Learning\\JavaAutomation\\JavaSelenium\\src\\main\\java\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String expectedURL = "https://www.google.com";
        driver.get(expectedURL);

        String actualURL = driver.getCurrentUrl();

        if (expectedURL.equals(actualURL)) System.out.println("True");
        else System.out.println("Fail");
        driver.close();
    }

    @Test(enabled = false, priority = 2)
    public void driverMethods() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\Learning\\JavaAutomation\\JavaSelenium\\src\\main\\java\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().forward();
        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.manage().window().fullscreen();
        driver.close();

    }
}

