package ecommerce.listner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import ecommerce.project.base.BaseClass;

public class ITestListnerClass extends BaseClass implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + "Test Case Started....");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.PASS,
				MarkupHelper.createLabel("This Test Case is Passed " + result.getName() + " ", ExtentColor.GREEN));
		log.info("This Test Case is Passed..." + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest =  extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL,
				MarkupHelper.createLabel("This Test Case is Failed " + result.getName() + " ", ExtentColor.RED));
		log.info("This test Case is failed...");
		extentTest.fail(result.getThrowable());
		try {
			String ScreenShot = getScreenshots(driver, result.getName());
			extentTest.addScreenCaptureFromBase64String(ScreenShot);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.SKIP,
				MarkupHelper.createLabel("This Test Case is Skipped " + result.getName() + " ", ExtentColor.YELLOW));
		log.info("This Test Case is skipped");
		extentTest.skip(result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName() + "Test Suite Executions Started...");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName() + "Test Suite Executions Completed or Finished...");
	}
}


