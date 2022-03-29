package Tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseClass implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test "+context.getName()+" Started");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test "+context.getName()+" ended");
        try {
            takeScreenShot("Success");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            takeScreenShot("Test Failure");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

}
