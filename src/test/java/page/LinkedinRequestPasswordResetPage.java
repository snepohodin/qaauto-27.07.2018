package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinRequestPasswordResetPage.
 */
public class LinkedinRequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * Constructor of LinkedinRequestPasswordResetPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinRequestPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(findAccountButton, 10);
    }


    /**
     * Verification that page was loaded.
     * @return - userNameField.isDisplayed, getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return userNameField.isDisplayed()
                && getCurrentPageTitle().contains("Reset Password")
                && getCurrentPageUrl().contains("/request-password-reset");
    }

    /**
     * PageFactory which returns LinkedinPasswordResetSubmitPage after gMailService connection
     * @param userEmail - type your email address here to connect
     * @return - LinkedinPasswordResetSubmitPage page object
     */
    public LinkedinPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService.connect();
        userNameField.sendKeys(userEmail);
        findAccountButton.click();
        return new LinkedinPasswordResetSubmitPage(browser);
    }
}
