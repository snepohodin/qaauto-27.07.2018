import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinForgotPasswordTest {

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
        LinkedinForgotPasswordPage linkedinForgotPasswordPage = linkedinLoginPage.loginReturnForgotPasswordPage();
        Assert.assertTrue(linkedinForgotPasswordPage.isLoaded(),"'Forgot password' page is not loaded.");

        LinkedinRequestPasswordResetSubmitPage linkedinRequestPasswordResetSubmitPage = linkedinForgotPasswordPage.enterEmail("rdmntest@gmail.com");
        Assert.assertTrue(linkedinRequestPasswordResetSubmitPage.isLoaded(), "'Password reset submit' page is not loaded.");


        try {
            sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        LinkedinPasswordResetPage linkedinPasswordResetPage = new LinkedinPasswordResetPage(browser);
        Assert.assertTrue(linkedinPasswordResetPage.isLoaded(),"'Password reset' page is not loaded.");

        LinkedinPasswordResetSuccessPage linkedinPasswordResetSuccessPage = linkedinPasswordResetPage.changePassword("July333#", "July333#");
        Assert.assertTrue(linkedinPasswordResetSuccessPage.isLoaded(),"'Password reset success' page is not loaded.");

        LinkedinHomePage linkedinHomePage = linkedinPasswordResetSuccessPage.returnHomePage();
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is not loaded.");
    }
}
