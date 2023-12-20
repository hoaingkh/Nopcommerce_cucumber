package cucumberOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import commons.ConfigFileReader;
import factoryBrowsers.BROWSERS;
import factoryEnvironments.BrowserStackFactory;
import factoryEnvironments.ENVINRONMENTS;
import factoryEnvironments.EnvironmentNotSupportedException;
import factoryEnvironments.LocalFactory;
import io.cucumber.java.Before;

public class Hooks {
	// Run for many thread
	private static WebDriver driver;
	private static final Logger log = Logger.getLogger(Hooks.class.getName());

	@Before
	public static WebDriver openAndQuitBrowser() {
		ConfigFileReader.initLoadFile();
		ENVINRONMENTS env = ENVINRONMENTS.valueOf(ConfigFileReader.getProperties("Evn").toUpperCase());
//		BROWSERS browser = BROWSERS.valueOf(ConfigFileReader.getProperties("browserName").toUpperCase());
		String browser = ConfigFileReader.getProperties("browserName");
		String osName = ConfigFileReader.getProperties("osName");
		String osVersion = ConfigFileReader.getProperties("osVersion");
		System.out.println("BrowserName: " + browser);

		// Check driver đã được khởi tạo hay chưa?
		if (driver == null) {
			try {
				switch (env) {
				case LOCAL:
					driver = new LocalFactory(browser).createDriver();
					break;
				case BROWSERSTACK:
					driver = new BrowserStackFactory(browser, osName, osVersion).createDriver();
					break;
				default:
					throw new EnvironmentNotSupportedException(env.toString());
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
		}
		driver.get(ConfigFileReader.getProperties("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public static void close() {
		try {
			if (driver != null) {
				openAndQuitBrowser().quit();
				log.info("------------- Closed the browser -------------");
			}
		} catch (UnreachableBrowserException e) {
			System.out.println("Can not close the browser");
		}
	}

	private static class BrowserCleanup implements Runnable {
		@Override
		public void run() {
			close();
		}
	}

}