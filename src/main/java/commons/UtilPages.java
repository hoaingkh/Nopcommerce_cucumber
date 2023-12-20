package commons;

import java.time.Duration;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import factoryEnvironments.BrowserStackFactory;
import factoryEnvironments.ENVINRONMENTS;
import factoryEnvironments.EnvironmentNotSupportedException;
import factoryEnvironments.LocalFactory;

public class UtilPages {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	protected final Log log;
	
	public UtilPages() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getBrowserDriver(String envName, String osName, String osVersion, String browserName) {
		ENVINRONMENTS env = ENVINRONMENTS.valueOf(envName.toUpperCase());
		switch (env) {
		case LOCAL:
			driver = new LocalFactory(browserName).createDriver();
			break;
		case BROWSERSTACK:
			driver = new BrowserStackFactory(browserName, osName, osVersion).createDriver();
			break;
		default:
			throw new EnvironmentNotSupportedException(envName);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return driver;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}

