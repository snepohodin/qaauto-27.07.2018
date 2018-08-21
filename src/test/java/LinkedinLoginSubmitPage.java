import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage extends BasePage{

    @FindBy(xpath = "//*[@role='alert']")
    private WebElement alertBox;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement sessionKeyLoginError;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement sessionPasswordLoginError;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return alertBox.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/uas/login-submit");
    }

    public String getAlertBoxText() {
        return alertBox.getText();
    }

    public String getUserEmailValidationText() {
        return userEmailValidationText.getText();
    }

    public String getUserPasswordValidationText() {
        return userPasswordValidationText.getText();
    }
}
