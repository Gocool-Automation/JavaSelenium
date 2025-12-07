package tests;


import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;


public class LaunchBrowsers extends BaseTest {

    @Test(enabled = true, priority = 0, testName = "Verify Browser Title")
    public void verifyBrowserTitle() throws IOException {

        driver.get("https://www.google.com");

        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Pass");
        } else System.out.println("Fail");
    }

    @Test(enabled = true, priority = 1, testName = "Verify Browser URL")
    public void verifyBrowserURL() {
        String expectedURL = "https://www.google.com/";
        driver.get(expectedURL);

        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL, expectedURL, "URL does not match");
    }

    @Test(enabled = true, priority = 2, testName = "Verify Browser navigation")
    public void browerNavigate() throws InterruptedException {
        driver.get("https://www.google.com");

        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().forward();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*/div[@class='FPdoLc lJ9FBc']/center/input[@value='Google Search']")));
        driver.navigate().refresh();
        Thread.sleep(2000);
    }
}

