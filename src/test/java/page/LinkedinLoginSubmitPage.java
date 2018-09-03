package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinLoginSubmitPage.
 */
public class LinkedinLoginSubmitPage extends BasePage{

    @FindBy(xpath = "//*[@role='alert']")
    private WebElement alertBox;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    /**
     * Constructor of LinkedinLoginSubmitPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(alertBox, 10);
    }

    /**
     * Method for verification that page was loaded.
     * Used getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return alertBox.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/uas/login-submit");
    }

    /**
     * Method for a text verification of webElement alertBox.
     */
    public String getAlertBoxText() {
        return alertBox.getText();
    }

    /**
     * Method for a text verification of webElement userEmailValidationText.
     */
    public String getUserEmailValidationText() {
        return userEmailValidationText.getText();
    }

    /**
     * Method for a text verification of webElement userPasswordValidationText.
     */
    public String getUserPasswordValidationText() {
        return userPasswordValidationText.getText();
    }
}
