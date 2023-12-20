package pageUIs;

public class LoginPageLocator {

    public static final String LOGIN_LINK = "xpath=//a[text()='Log in']";

    public static final String LOGIN_BUTTON = "xpath=(//button[@type='submit'])[2]";

    public static final String ERROR_MESSAGE_FOR_EMPTY_DATA = "xpath=//span[@id='Email-error']";

    public static final String EMAIL = "xpath=(//input[@id='Email']";

    public static final String PASSWORD = "xpath=(//input[@type='password'])[1]";

    public static final String MESSAGE_INVALID_EMAIL = "xpath=//span[@id='Email-error']";

    public static final String MESSAGE_NONEXISTED_EMAIL = "xpath=//div[contains(@class,'validation-summary-errors')]//li";

    public static final String PASSWORD_LOGIN = "xpath=(//input[@type='password'])[1]";

    public static final String MESSAGE_NOFILL_PASSWORD = "xpath=//div[contains(@class,'validation-summary-errors')]//li";

    public static final String CONTINUE_BUTTON = "xpath=//a[contains(@class,'register-continue-button')]";

    public static final String LOGOUT_BUTTON = "//a[text()='Log out']";
}
