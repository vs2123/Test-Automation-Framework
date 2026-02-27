package utilites;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static void screenShot(WebDriver driver, String filePath) throws IOException {
		File ssFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		FileUtils.copyFile(ssFile, destFile);
		
	}
}
