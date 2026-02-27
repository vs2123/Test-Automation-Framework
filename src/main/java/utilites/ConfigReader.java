package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String getProperty(String key) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("C:\\Users\\Admin\\eclipse_workspace_vs_2025\\Test-Automation-Framework\\src\\test\\resources\\config.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

}
