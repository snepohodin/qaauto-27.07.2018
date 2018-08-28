import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinForgotPasswordPage extends BasePage {

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    public  LinkedinForgotPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

//    public LinkedinForgotPasswordPage loginReturnForgotPasswordPage() {
//        forgotPasswordLink.click();
//        try {
//            sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return new LinkedinForgotPasswordPage(browser);
//    }

    public boolean isLoaded() {
        return userNameField.isDisplayed()
                && getCurrentPageTitle().contains("Reset Password")
                && getCurrentPageUrl().contains("/request-password-reset");
    }

    public LinkedinRequestPasswordResetSubmitPage enterEmail(String enterEmail) {
        userNameField.sendKeys(enterEmail);
        userNameField.sendKeys(Keys.ENTER);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinRequestPasswordResetSubmitPage(browser);
    }
}
