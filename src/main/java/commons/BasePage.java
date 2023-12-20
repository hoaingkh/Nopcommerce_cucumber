package commons;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends UtilPages{

	protected static BasePage getBaePage() {
		return new BasePage();
	}

	protected void getUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	protected String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendKeysToAlert(WebDriver driver, String value) {
		waitForAlertPresence(driver).sendKeys(value);
	}

	protected void switchWindowByID(WebDriver driver, String currentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			if (!windowID.equals(currentID)) {
				driver.switchTo().window(windowID);
				driver.manage().window().maximize();
			}
		}
	}

	protected void switchToWindoWByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String runWindow : allWindowID) {
			driver.switchTo().window(runWindow);
			if (driver.getTitle().equals(expectedTitle)) {
				break;
			}
		}
	}

	protected void closeAllTabWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	protected By getByXpath(String locator) {
		return By.xpath(locator);
	}

	protected By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=")) {
			locator = locator.substring(3);
			by = By.id(locator);
		} else if (locator.startsWith("class=")) {
			locator = locator.substring(6);
			by = By.className(locator);
		} else if (locator.startsWith("name=")) {
			locator = locator.substring(5);
			by = By.name(locator);
		} else if (locator.startsWith("css=")) {
			locator = locator.substring(4);
			by = By.cssSelector(locator);
		} else if (locator.startsWith("xpath=")) {
			locator = locator.substring(6);
			by = By.xpath(locator);
		}
		return by;
	}

	private String getDynamicLocator(String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		return locator;
	}

	protected WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	protected WebElement getWebElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		return driver.findElement(getByLocator(locator));
	}

	protected List<WebElement> getListWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	protected List<WebElement> getListWebElements(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		return driver.findElements(getByLocator(locator));
	}

	protected void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	protected void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		getWebElement(driver, getDynamicLocator(locator, dynamicValue)).click();
	}

	protected void sendKeyToElement(WebDriver driver, String locator, String value) {
		getWebElement(driver, locator).sendKeys(value);
	}

	protected void sendKeyToElement(WebDriver driver, String locator, String value, String... dynamicValue) {
		getWebElement(driver, getDynamicLocator(locator, dynamicValue)).clear();
		getWebElement(driver, getDynamicLocator(locator, dynamicValue)).sendKeys(value);
	}

	protected void selectItemDefaultDropdown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	protected void selectItemDefaultDropdown(WebDriver driver, String locator, String itemText, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}

	protected String getSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String locator, String xpathItemLocator, String textItems) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));

		jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", getWebElement(driver, locator));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator))).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(xpathItemLocator)));
		List<WebElement> allItems = getListWebElements(driver, xpathItemLocator);
		for (WebElement item : allItems) {
			if (item.getText().equals(textItems)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
				sleepInSecond(1);
				explicitWait.until(ExpectedConditions.elementToBeClickable(item)).click();
				break;
			}
		}
	}

	protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}

	protected String getElementText(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		return getWebElement(driver, locator).getText();
	}

	protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locator) {
		return getListWebElements(driver, locator).size();
	}

	protected int getElementSize(WebDriver driver, String locator, String... dynamicValue) {
		return getListWebElements(driver, getDynamicLocator(locator, dynamicValue)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!isElementSelected(driver, locator)) {
			element.click();
		}
	}

	protected void unCheckToDefaultCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (isElementSelected(driver, locator)) {
			element.click();
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		boolean status = true;

		try {
			WebElement element = driver.findElement(getByLocator(getDynamicLocator(locator)));
			if (element.isDisplayed()) {
				return status;
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	protected boolean isElementUnDisplayed(WebDriver driver, String locator) {
		boolean status = true;

		overrideImplicitWait(driver, 5);

		List<WebElement> listElements = getListWebElements(driver, locator);

		if (listElements.size() == 0) {
			status = true;
		} else if (listElements.size() > 0 && !listElements.get(0).isDisplayed()) {
			status = true;
		} else {
			status = false;
		}

		overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);

		return status;
	}

	private void overrideImplicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected boolean isElementEnable(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void doubleClick(WebDriver driver, String locator) {
		Actions actions = new Actions(driver);
		actions.doubleClick(getWebElement(driver, locator)).perform();
	}

	protected void hoverToElement(WebDriver driver, String locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement(driver, locator)).perform();
	}

	protected void hoverToElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement(driver, locator)).perform();
	}

	protected void rightClickToElement(WebDriver driver, String locator) {
		Actions actions = new Actions(driver);
		actions.contextClick(getWebElement(driver, locator)).perform();
	}

	protected void dragAndDrop(WebDriver driver, String locatorSource, String locatorDestination) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(getWebElement(driver, locatorSource), getWebElement(driver, locatorDestination)).perform();
	}

	protected void sendKeyToElementByAction(WebDriver driver, String locator, String value) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, locator), value).perform();
	}

	protected void pressKeyByAction(WebDriver driver, String locator, Keys key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, locator), key).perform();
	}

	String projectPath = System.getProperty("user.dir");

	protected void uploadFiles(WebDriver driver, String locator, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.FILEPATH_UPLOAD + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendKeyToElement(driver, locator, fullFileName);
	}

	protected Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	protected String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");
	}

	protected void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	protected String getWebElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected void waitForElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	protected void waitForElementClickable(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	protected void waitForElementInVisible(WebDriver driver, String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	protected void waitForElementInVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	protected boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		return getWebElement(driver, locator).isDisplayed();
	}

	private void sleepInSecond(int timeOut) {
		try {
			Thread.sleep(timeOut * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
