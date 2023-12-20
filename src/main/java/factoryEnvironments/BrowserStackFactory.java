package factoryEnvironments;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class BrowserStackFactory {
	WebDriver driver;
	String browserName,  osName,  osVersion;
	
	public BrowserStackFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}
	
	public WebDriver createDriver() {
		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("browserName", browserName);
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("os", osName);
		browserstackOptions.put("osVersion", osVersion);
		browserstackOptions.put("browserVersion", "latest");
		browserstackOptions.put("local", "false");
		browserstackOptions.put("seleniumVersion", "4.11.0");
		capabilities.setCapability("bstack:options", browserstackOptions);

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSERSTACK_URL), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
