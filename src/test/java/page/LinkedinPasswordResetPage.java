package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinPasswordResetPage
 */
public class LinkedinPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor of LinkedinPasswordResetPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(newPasswordField, 10);
    }

    /**
     * Verification that page was loaded.
     * Used getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return newPasswordField.isDisplayed()
                && getCurrentPageTitle().contains("Reset")
                && getCurrentPageUrl().contains("password-reset");
    }
}
