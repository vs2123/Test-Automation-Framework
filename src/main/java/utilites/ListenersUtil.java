package utilites;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;


public class ListenersUtil implements ITestListener {
	private static ExtentReports extent = ExtentManager.getInstance();
	private  ExtentTest test;
	Marker passed = MarkerManager.getMarker("passed");
	Marker failed = MarkerManager.getMarker("failed");
	Marker skipped = MarkerManager.getMarker("skipped");
	Marker testStart = MarkerManager.getMarker("Test start");
	@Override
	public void onStart(ITestContext context) {
		LoggerUtil.log.info("[Execution Start..]");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		LoggerUtil.log.info(testStart, "[Test start] "+result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String dateTimeStr = ldt.format(formatter);

		test.log(Status.FAIL, result.getThrowable());
		LoggerUtil.log.error(failed, "[FAILED] "+result.getName());
		try {
			// 1. Get the failed test object
	        BaseTest test = (BaseTest) result.getInstance();
	        // 2. Get driver FROM the test
	        WebDriver driver = test.driver;
			ScreenshotUtil.screenShot(driver, ConfigReader.getProperty("ScreenshotFilePath") + result.getName() + "_" + dateTimeStr + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(ConfigReader.getProperty("ScreenshotFilePath") + result.getName() + "_" + dateTimeStr + ".png");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "Test Passed");
        LoggerUtil.log.info(passed, "[PASSED] "+result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getName());
		LoggerUtil.log.info(skipped, "[SKIPPED] "+result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		LoggerUtil.log.info("[Execution finished..]");
	}
	

}
