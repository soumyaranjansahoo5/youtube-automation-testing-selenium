package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.ScreenshotUtil;
import base.BaseTest;

public class TestListener implements ITestListener {

    static ExtentReports extent = ExtentManager.getReport();
    static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
        test.get().info("Test Started 🚀");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String path = ScreenshotUtil.takeScreenshot(BaseTest.driver, result.getName());

        test.get().pass("Test Passed ✅");
        test.get().addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String path = ScreenshotUtil.takeScreenshot(BaseTest.driver, result.getName());

            test.get().fail(result.getThrowable());
            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped ⚠️");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}