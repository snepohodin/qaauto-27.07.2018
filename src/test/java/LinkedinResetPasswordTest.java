import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest {

    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinPasswordResetPage linkedinPasswordResetPage1;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

    @Test
    public void basicForgotPasswordTest () {
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "LinkedinLoginPage is not loaded");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(),"'RequestPasswordResetPage' page is not loaded.");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.findAccount("rdmntest@gmail.com");
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "PasswordResetSubmitPage page is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),"SetNewPasswordPage is not loaded.");

        LinkedinPasswordResetSuccessPage linkedinPasswordResetSuccessPage = linkedinSetNewPasswordPage.changePassword("July333#", "July333#");
        Assert.assertTrue(linkedinPasswordResetSuccessPage.isLoaded(),"'Password reset success' page is not loaded.");

        LinkedinHomePage linkedinHomePage = linkedinPasswordResetSuccessPage.returnHomePage();
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is not loaded.");
    }
}
