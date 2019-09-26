package com.hellofresh.challenge.extent.reports;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.hellofresh.challenge.web.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestListener extends BaseTest implements ITestListener {
	private Logger log = Logger.getLogger(this.getClass());

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("onStart method " + iTestContext.getName());

	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("onFinish method " + iTestContext.getName());
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info("onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		// ExtentReports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {

		log.info("onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		// Get driver from BaseTest and assign to local webDriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		// ExtentReports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info("onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		// ExtentReports log operation for skipped tests.
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
}