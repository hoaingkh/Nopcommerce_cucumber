package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import cucumberOptions.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageUIs.HomePageLocator;
import pageUIs.RegisterPageLocator;

public class RegisterStepDefinitions extends BasePage {
	WebDriver driver;
	static String email;

	public RegisterStepDefinitions() {
		driver = Hooks.openAndQuitBrowser();
	}

	@When("Click to Register link")
	public void clickToRegisterLink() {
		waitForElementClickable(driver, HomePageLocator.REGISTER_LINK);
		clickToElement(driver, HomePageLocator.REGISTER_LINK);
	}

	@When("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageLocator.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageLocator.REGISTER_BUTTON);
	}

	@When("Verify error message {string} at First Name field")
	public void verifyErrorMessageAtFirstNameField(String errorMessage) {
		waitForElementVisible(driver, RegisterPageLocator.FIRST_NAME_ERROR_MESSAGE);
		verifyEquals(getElementText(driver, RegisterPageLocator.FIRST_NAME_ERROR_MESSAGE), errorMessage);
	}

	@When("Verify error message {string} at Last Name field")
	public void verifyErrorMessageAtLastNameField(String errorMessage) {
		waitForElementVisible(driver, RegisterPageLocator.LAST_NAME_ERROR_MESSAGE);
		verifyEquals(getElementText(driver, RegisterPageLocator.LAST_NAME_ERROR_MESSAGE), errorMessage);
	}

	@When("Verify error message {string} at Email field")
	public void verifyErrorMessageAtEmailField(String errorMessage) {
		waitForElementVisible(driver, RegisterPageLocator.EMAIL_ERROR_MESSAGE);
		verifyEquals(getElementText(driver, RegisterPageLocator.EMAIL_ERROR_MESSAGE), errorMessage);
	}

	@When("Verify error message {string} at Password field")
	public void verifyErrorMessageAtPasswordField(String errorMessage) {
		waitForElementVisible(driver, RegisterPageLocator.PASSWORD_ERROR_MESSAGE);
		verifyEquals(getElementText(driver, RegisterPageLocator.PASSWORD_ERROR_MESSAGE), errorMessage);
	}

	@When("Verify error message {string} at Confirm Password field")
	public void verifyErrorMessageAtConfirmPasswordField(String errorMessage) {
		waitForElementVisible(driver, RegisterPageLocator.CONFIRM_PASSWORD_ERROR_MESSAGE);
		verifyEquals(getElementText(driver, RegisterPageLocator.CONFIRM_PASSWORD_ERROR_MESSAGE), errorMessage);
	}

	@When("Input {string} to First Name field")
	public void inputValueToFirstName(String firstName) {
		waitForElementVisible(driver, RegisterPageLocator.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.FIRST_NAME_TEXTBOX, firstName);
	}

	@When("Input {string} to Last Name field")
	public void inputValueToLastName(String lastName) {
		waitForElementVisible(driver, RegisterPageLocator.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.LAST_NAME_TEXTBOX, lastName);
	}

	@When("Input {string} to Email field")
	public void inputValueToEmail(String email) {
		waitForElementVisible(driver, RegisterPageLocator.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.EMAIL_TEXTBOX, email);
	}

	@When("Input {string} to Password field")
	public void inputValueToPassword(String password) {
		waitForElementVisible(driver, RegisterPageLocator.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.PASSWORD_TEXTBOX, password);
	}

	@When("Input {string} to Confirm Password field")
	public void inputValueToConfirmPassword(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageLocator.CONFRIM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.CONFRIM_PASSWORD_TEXTBOX, confirmPassword);
	}

	@When("Input info for required fields")
	public void inputInfoForRequiredFields(DataTable tableInfo) {
		List<Map<String, String>> registerInfo = tableInfo.asMaps();

		// Send value to First Name field
		waitForElementVisible(driver, RegisterPageLocator.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.FIRST_NAME_TEXTBOX, registerInfo.get(0).get("First Name"));

		// Send value to Last Name field
		waitForElementVisible(driver, RegisterPageLocator.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.LAST_NAME_TEXTBOX, registerInfo.get(0).get("Last Name"));

		// Send value to Email field
		email = generateRandomNumber()+ registerInfo.get(0).get("Email");
		waitForElementVisible(driver, RegisterPageLocator.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.EMAIL_TEXTBOX, email);

		// Send value to Password field
		waitForElementVisible(driver, RegisterPageLocator.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.PASSWORD_TEXTBOX, registerInfo.get(0).get("Password"));

		// Send value to Confirm Password field
		waitForElementVisible(driver, RegisterPageLocator.CONFRIM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageLocator.CONFRIM_PASSWORD_TEXTBOX, registerInfo.get(0).get("Confirm Password"));
	}
	
	@Then("Verify successfully register")
	public void verifySuccessRegister() {
		waitForElementVisible(driver, RegisterPageLocator.MESSAGE_SUCCESS_REGISTER);
		verifyTrue(isElementDisplayed(driver, RegisterPageLocator.MESSAGE_SUCCESS_REGISTER));

	}

	@When("Click to Login link in Register page")
	public void clickToLoginLinkInRegisterPage() {

		waitForElementClickable(driver, HomePageLocator.LOGIN_LINK);
		clickToElement(driver, HomePageLocator.LOGIN_LINK);
	}


}
