package commons;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigFileReader {
	private static Properties properties;
	private static FileInputStream fileIn;

	
	public static void initLoadFile() {
		properties = new Properties();
		try {
			fileIn = new FileInputStream(GlobalConstants.PROPERTIES_FILE_PATH);
			// Load properties file
			properties.load(fileIn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String keyProp) {
		String value = null;
		try {
			// get value from properties file
			value =  properties.getProperty(keyProp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return value;
	}
}
