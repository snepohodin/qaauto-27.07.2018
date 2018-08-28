import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinPasswordResetSuccessPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    public LinkedinPasswordResetSuccessPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public LinkedinHomePage returnHomePage() {
        submitButton.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinHomePage(browser);
    }

    public boolean isLoaded() {
        return submitButton.isDisplayed()
                && getCurrentPageTitle().contains("You've successfully reset your password.")
                && getCurrentPageUrl().contains("/password-reset-submit");
    }
}
