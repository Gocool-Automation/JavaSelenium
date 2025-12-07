package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportPath;

    public static ExtentReports getExtent() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    public synchronized static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("Selenium Automation Test Report");
        reporter.config().setReportName("Selenium Test Execution Results");
        reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Gokulnath");
        extent.setSystemInfo("Browser", "Chrome");
        return extent;
    }
}
