package factoryBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;

public class FirefoxDriverManager extends BrowserFactory{
	WebDriver driver;
	
	@Override
	public WebDriver getBrowserDriver() {
		FirefoxOptions options = new FirefoxOptions();
	
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.PROJECTPATH + "\\downloadFiles");
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
		options.addPreference("pdfjs.disabled", true);
		options.addArguments("-private");
		
		driver = new FirefoxDriver(options);
		
		return driver;
	}

}
