package Utilities;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String filePath) {
        // Cast the WebDriver instance to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Get the screenshot as a File
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // Define the destination file
        File destFile = new File(filePath + "screenshot.png");

        try {
            // Copy the screenshot file to the desired location
            FileHandler.copy(srcFile, destFile);
            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + e.getMessage());
        }
    }

    public static void takeFullScreenshot(WebDriver driver, String filePath) throws IOException {

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);

        ImageIO.write(screenshot.getImage(), "PNG", new File(filePath + "fullpage.png"));
    }

}
