package Utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void startTest(String testName) {
        ExtentTest test = ExtentManager.getExtent().createTest(testName);
        extentTest.set(test);
    }
}