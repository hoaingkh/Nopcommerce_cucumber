package factoryEnvironments;

import org.openqa.selenium.WebDriver;

import factoryBrowsers.BROWSERS;
import factoryBrowsers.BrowserNotSupportedException;
import factoryBrowsers.ChromeDriverManager;
import factoryBrowsers.EdgeDriverManager;
import factoryBrowsers.FirefoxDriverManager;

public class LocalFactory {
	WebDriver driver;
	String browserName;
	
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	
	public WebDriver createDriver() {
		BROWSERS browser = BROWSERS.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
