package factoryBrowsers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import commons.GlobalConstants;

public class ChromeDriverManager extends BrowserFactory{
	WebDriver driver;

	@Override
	public WebDriver getBrowserDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=vi");
		options.addArguments("--disable-geolocation");
		
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", GlobalConstants.PROJECTPATH + "\\downloadFiles");
		options .setExperimentalOption("prefs", chromePrefs);

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		
//		options.addArguments("--incognito");
		
//		options.addArguments("--headless");
		
		driver = new ChromeDriver(options);
		
		return driver;
	}

}
