package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinPasswordResetSubmitPage.
 */
public class LinkedinPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;

    /**
     * Constructor of LinkedinPasswordResetSubmitPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resendLinkButton, 10);
    }

    /**
     * Verification that page was loaded.
     * @return - resendLinkButton.isDisplayed, getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("/rp/request-password-reset-submit");
    }


    /**
     * Page Factory which returns next page(LinkedinSetNewPasswordPage), taking a resetPasswordLink and going on it
     * @return - LinkedinSetNewPasswordPage Page Object
     */
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "rdmntest@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String resetPasswordLink = StringUtils.substringBetween(message,"To change your LinkedIn password, click <a href=\\\"<a href=&quot;",
                "&quot;>[text]</a>").replace("amp;","");
        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);
        return new LinkedinSetNewPasswordPage(browser);
    }
}
