import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;

    public LinkedinPasswordResetSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageTitle().contains("Please check your mail for reset password link.")
                && getCurrentPageUrl().contains("/rp/request-password-reset-submit");
    }

    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {


        //ToDo
        return new LinkedinSetNewPasswordPage(browser);
    }
}
