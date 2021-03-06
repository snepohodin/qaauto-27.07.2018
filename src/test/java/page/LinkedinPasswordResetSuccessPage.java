package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinPasswordResetSuccessPage.
 */
public class LinkedinPasswordResetSuccessPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor of LinkedinPasswordResetSuccessPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinPasswordResetSuccessPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(submitButton, 10);
    }

    /**
     * PageFactory which navigates to the Home page after password was reset
     * @return - LinkedinHomePage Page Object
     */
    public LinkedinHomePage returnHomePage() {
        submitButton.click();
        return new LinkedinHomePage(browser);
    }

    /**
     * Verification that page was loaded.
     * @return - submitButton.isDisplayed, getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return submitButton.isDisplayed()
                && getCurrentPageTitle().contains("You've successfully reset your password.")
                && getCurrentPageUrl().contains("/password-reset-submit");
    }
}
