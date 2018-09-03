package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinLoginPage
 */
public class LinkedinLoginPage extends BasePage {

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@id ='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    /**
     * Constructor of LinkedinLoginPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(userEmailField, 10);
    }

    /**
     * Page Factory which returns next page(LinkedinLoginSubmitPage) after login was done
     * @param userEmail - user email
     * @param userPass - user password
     */
    public LinkedinLoginSubmitPage loginReturnLoginSubmitPage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedinLoginSubmitPage(browser);
    }

    /**
     * Page Factory which returns next page(LinkedinRequestPasswordResetPage) after clicking on forgotPasswordLink
     */
    public LinkedinRequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(browser);
    }

    /**
     * Page Factory which returns next page(LinkedinHomePage) after clicking on signInButton
     * @param userEmail - user email
     * @param userPass - user password
     */
    public LinkedinHomePage loginReturnHomePage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedinHomePage(browser);
    }

    /**
     * Page Factory which returns next page(LinkedinLoginPage) after clicking on signInButton
     * @param userEmail - user email
     * @param userPass - user password
     */
    public LinkedinLoginPage loginReturnLoginPage(String userEmail, String userPass) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        signInButton.click();
        return new LinkedinLoginPage(browser);
    }

    /**
     * Verification that page was loaded.
     * Used getCurrentPageTitle method from BasePage.
     */
    public boolean isLoaded() {
        return userEmailField.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up");
    }
}
