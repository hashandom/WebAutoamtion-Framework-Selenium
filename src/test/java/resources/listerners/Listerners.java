package resources.listerners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import utility.ExtentReport;

import java.io.IOException;


public class Listerners extends Base implements ITestListener {
    ExtentReports extentReport = utility.ExtentReport.getExtentReport();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName + "execution started");
        extentTestThread.set(extentTest);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
//        extentTest.log(Status.PASS, testName + " passed");
        extentTestThread.get().log(Status.PASS, testName + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        String testMethodName = result.getName();
//        extentTest.fail(result.getThrowable());
        extentTestThread.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        extentTest.log(Status.SKIP, result.getName() + " skipped");
        extentTestThread.get().log(Status.SKIP, result.getName() + " skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        extentTestThread.remove();
    }
}
