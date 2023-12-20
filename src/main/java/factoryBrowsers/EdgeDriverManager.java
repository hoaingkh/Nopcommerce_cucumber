package factoryBrowsers;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import commons.GlobalConstants;

public class EdgeDriverManager extends BrowserFactory{
	WebDriver driver;
	
	@Override
	public WebDriver getBrowserDriver() {
		EdgeOptions options = new EdgeOptions();
		
		options.addArguments("--lang=vi");
		options.addArguments("--disable-geolocation");
		
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", GlobalConstants.PROJECTPATH + "\\downloadFiles");
		options .setExperimentalOption("prefs", chromePrefs);
		
//		options.addArguments("--incognito");
		
//		options.addArguments("--headless");
		
		driver = new EdgeDriver(options);
		
		return driver;
	}

}
