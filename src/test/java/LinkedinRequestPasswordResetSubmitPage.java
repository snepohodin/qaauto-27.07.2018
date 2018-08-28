import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinRequestPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLink;

    public  LinkedinRequestPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resendLink.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("/rp/request-password-reset-submit");
    }
}
