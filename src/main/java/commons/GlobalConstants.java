package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PROJECTPATH = System.getProperty("user.dir");
	public static final String PROPERTIES_FILE_PATH =  PROJECTPATH + "/src/test/resources/Configs.properties";
	public static final String EXTENT_PATH = PROJECTPATH + "/ExtentReportsV2/";
	public static final String FILEPATH_UPLOAD = PROJECTPATH + File.separator + "uploadFiles" + File.separator;
	public static final String USERNAME_BROWSERSTACK = "automationwarrio_u8nFwz";
	public static final String TOKEN_BROWSERSTACK  = "RfuTyBNSHfHR5nPE7bqQ";
	public static final String BROWSERSTACK_URL = "https://" + USERNAME_BROWSERSTACK + ":" + TOKEN_BROWSERSTACK + "@hub-cloud.browserstack.com/wd/hub";

	public static final int SHORT_TIMEOUT = 20;
	public static final int LONG_TIMEOUT = 30;
}
