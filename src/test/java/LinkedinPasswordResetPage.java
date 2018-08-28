import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    public LinkedinPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }


    public LinkedinPasswordResetSuccessPage changePassword(String newPassword, String confirmNewPassword ) {
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(confirmNewPassword);
        submitButton.click();
        return new LinkedinPasswordResetSuccessPage(browser);
    }

    public boolean isLoaded() {
        return newPasswordField.isDisplayed()
                && getCurrentPageTitle().contains("Reset")
                && getCurrentPageUrl().contains("password-reset");
    }
}