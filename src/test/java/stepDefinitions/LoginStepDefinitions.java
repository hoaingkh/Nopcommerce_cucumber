package stepDefinitions;

import com.google.common.base.Verify;
import commons.BasePage;
import cucumberOptions.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.When;
import pageUIs.HomePageLocator;
import pageUIs.LoginPageLocator;
import pageUIs.RegisterPageLocator;

public class LoginStepDefinitions extends BasePage {

	public LoginStepDefinitions() {driver = Hooks.openAndQuitBrowser(); }
	WebDriver driver;
	
	@When("Click to Login link")
	public void clickToLoginLink() {
		waitForElementClickable(driver, HomePageLocator.LOGIN_LINK);
		clickToElement(driver, HomePageLocator.LOGIN_LINK);
	};
	
	@When("Click to login button")
	public void clickToLoginButton() {

		waitForElementClickable(driver, LoginPageLocator.LOGIN_BUTTON);
		clickToElement(driver, LoginPageLocator.LOGIN_BUTTON);
	}

    @Then("Verify error message {string} at Email field for empty data")
	public void verifyErrorMessageEmailField(String errorMessageEmail) {
		waitForElementVisible(driver, LoginPageLocator.ERROR_MESSAGE_FOR_EMPTY_DATA);
		verifyEquals(getElementText(driver, LoginPageLocator.ERROR_MESSAGE_FOR_EMPTY_DATA), errorMessageEmail);
	}


	@When("Input {string} in Email field")
	public void inputInEmailField(String invalidEmail) {
		waitForElementVisible(driver, LoginPageLocator.EMAIL);
		sendKeyToElement(driver, LoginPageLocator.EMAIL, invalidEmail);
	}
	@When("Input {string} in Password field")
	public void inputInPasswordField(String validPassword) {
		waitForElementVisible(driver,LoginPageLocator.PASSWORD_LOGIN);
		sendKeyToElement(driver,LoginPageLocator.PASSWORD_LOGIN,validPassword);
	}

	@Then("Verify error message {string} at Email field for Wrong email")
    public void verifyErrorMessageForWrongEmail(String errorMessage) {
		verifyEquals(getElementText(driver, LoginPageLocator.MESSAGE_INVALID_EMAIL), errorMessage);
	}

	@Then("Verify error summary message {string} for Email field")
	public void verifyErrorSummaryMessageForEmail(String errorMessage) {
		verifyEquals(getElementText(driver, LoginPageLocator.MESSAGE_NOFILL_PASSWORD), errorMessage);


	}

	@When("Input registered email {string} in Email field")
	public void inputRegisteredEmailField(String email) {
		waitForElementVisible(driver, LoginPageLocator.EMAIL);
		sendKeyToElement(driver, LoginPageLocator.EMAIL, email);

	}

	@When("Click to Continue button")
	public void clickContinueButton() {
		waitForElementClickable(driver, LoginPageLocator.CONTINUE_BUTTON);
		clickToElement(driver, LoginPageLocator.CONTINUE_BUTTON);
	}

	@When("Click to Logout button")
	public void clickLogOutButton() {
		waitForElementClickable(driver, LoginPageLocator.LOGOUT_BUTTON);
		clickToElement(driver, LoginPageLocator.LOGOUT_BUTTON);
	}



	
}
