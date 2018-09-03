package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinSetNewPasswordPage.
 */
public class LinkedinSetNewPasswordPage extends BasePage {
    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor of LinkedinPasswordResetSuccessPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(newPasswordField, 10);
    }

    /**
     * Method for verification that page was loaded.
     * Used getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return newPasswordField.isDisplayed()
                && getCurrentPageTitle().contains("Reset")
                && getCurrentPageUrl().contains("password-reset");
    }


    /**
     * Page Factory which returns next page(LinkedinPasswordResetSuccessPage) after the password was changed.
     * @param newPassword - field for a new password.
     * @param confirmNewPassword - field where you should to repeat a new password.
     */
    public LinkedinPasswordResetSuccessPage changePassword(String newPassword, String confirmNewPassword ) {
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(confirmNewPassword);
        submitButton.click();
        return new LinkedinPasswordResetSuccessPage(browser);
    }
}
