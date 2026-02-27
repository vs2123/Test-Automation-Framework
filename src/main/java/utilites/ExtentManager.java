package utilites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			
			ExtentSparkReporter spark = new ExtentSparkReporter(ConfigReader.getProperty("reportPath"));
			spark.config().setReportName("Automation Test Report");
			spark.config().setDocumentTitle("Test Results");
			spark.config().setTheme(Theme.DARK);
			spark.config().setTimeStampFormat("yyyy:mm:dd hh:mm:ss:ms");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Automation Tester", "VSS");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;

	}
}
