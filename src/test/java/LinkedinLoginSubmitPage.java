import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {

    private WebDriver browser;

    private WebElement alertBox;
    private WebElement sessionKeyLoginError;
    private WebElement sessionPasswordLoginError;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }

    private void initElements() {
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        sessionKeyLoginError = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
        sessionPasswordLoginError = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String getAlertBoxText() {
        return alertBox.getText();
    }

    public String getSessionKeyLoginErrorText() {
        return sessionKeyLoginError.getText();
    }

    public String getSessionPasswordLoginErrorText(){
        return sessionPasswordLoginError.getText();
    }

}
