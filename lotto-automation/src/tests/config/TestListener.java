package tests.config;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestListener implements ITestListener {

    private static final LoggerManager loggerManager = new LoggerManager();

    //Before starting all tests, below method runs.
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
    }

    //After ending all tests, below method runs.
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extent reports reporting!
        Map<String, String> testNGParams = iTestContext.getCurrentXmlTest().getLocalParameters();
        ExtentManager.getInstance().setSystemInfo("Browser", testNGParams.get("browserName"));
        ExtentManager.getInstance().setSystemInfo("URL", testNGParams.get("url"));
        ExtentManager.getInstance().setSystemInfo("Mode", testNGParams.get("mode"));
        ExtentManager.getInstance().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + iTestResult.getMethod().getMethodName() + " start");
        //Start operation for extent reports.
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
        loggerManager.initLogs(iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + iTestResult.getMethod().getMethodName() + " succeed");
        //Extent reports log operation for passed tests.
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
        loggerManager.endLogs(iTestResult.getMethod().getDescription());

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + iTestResult.getMethod().getMethodName() + " failed");
        System.out.println("I am in onTestFailure method " + iTestResult.getThrowable());

        try {
            ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName()+ " - Test Case Failed", ExtentColor.RED));
            ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getThrowable().toString(), ExtentColor.RED));
            File file = new File(ExtentManager.folderPath + "screenshots");
            if (!file.exists()) {
                System.out.println("File created " + file);
                file.mkdir();
            }
            File scrFile = ((TakesScreenshot) TestCaseBase.driver).getScreenshotAs(OutputType.FILE);
            StringBuilder testFailed = new StringBuilder();
            testFailed.append(this.getClass().getSimpleName()).append(".").append(iTestResult.getName()).
                    append(".failedTest").append(".png");
            FileUtils.copyFile(scrFile, new File(ExtentManager.folderPath + "screenshots/" + testFailed.toString()));

            ExtentTestManager.getTest().addScreenCaptureFromPath("screenshots/" + testFailed.toString());
            loggerManager.endLogs(iTestResult.getMethod().getDescription());
        } catch (IOException io) {
            throw new RuntimeException(String.format("Path for saving the screenshot was not found: %s", io));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + iTestResult.getMethod().getMethodName() + " skipped");
        System.out.println("Skipped because " + iTestResult.getThrowable().toString());
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
        //Extent reports log operation for skipped tests.
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
        ExtentTestManager.getTest().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getThrowable() + " - Test Case Failed", ExtentColor.ORANGE));
        ExtentTestManager.getTest().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + " - Test Case Failed", ExtentColor.ORANGE));
        loggerManager.endLogs(iTestResult.getMethod().getDescription());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + iTestResult.getMethod().getMethodName());
    }
}
