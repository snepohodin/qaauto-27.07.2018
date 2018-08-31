import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class LinkedinRequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public LinkedinRequestPasswordResetPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return userNameField.isDisplayed()
                && getCurrentPageTitle().contains("Reset Password")
                && getCurrentPageUrl().contains("/request-password-reset");
    }

    public LinkedinPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService = new GMailService(userEmail,"0131223689");
        gMailService.connect();
        userNameField.sendKeys(userEmail);
        findAccountButton.click();

        String messageSubject = "here's the link to reset your password";
        String messageTo = "rdmntest@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        Pattern pattern = Pattern.compile("(?:<a.*href=\\\")(.*email_security_password_reset.*?)(?:\\\"\\sstyle)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            System.out.println("group 1: " + matcher.group(1));
        }
        String resetPasswordLink = matcher.group(1);



//        browser.get(matcher.group(1));
//        String url = message.substring(message.indexOf("To change your LinkedIn password, click <a href=\""),message.indexOf("\" style="));
//        System.out.println("URL here: " + url);



//        int startPosition = message.indexOf("[/a]" + "[a]".length());
//        int endPosition = message.indexOf("[/a",startPosition);
//        String url = message.substring(startPosition,endPosition);
//        System.out.println("URL here:" + url);

//       String[] url = message.split("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",4);
//        for (String row : url) {
//            System.out.println(row);
//            }

//        WebElement url = browser.findElement(By.linkText("here"));
//        System.out.println("Link name: " + url);
//        url.click();

//        String url = message.substring(message.indexOf("<a href=")+4,message.indexOf("</a>"));
//        System.out.println("URL's here: " + url);
        return new LinkedinPasswordResetSubmitPage(browser);
    }
}
