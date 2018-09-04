package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class LinkedinResetPasswordTest extends BaseTest{

    @Test
    public void basicForgotPasswordTest () throws InterruptedException {
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "page.LinkedinLoginPage is not loaded");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(),"'RequestPasswordResetPage' page is not loaded.");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.findAccount("rdmntest@gmail.com");
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "PasswordResetSubmitPage page is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),"SetNewPasswordPage is not loaded.");

        page.LinkedinPasswordResetSuccessPage linkedinPasswordResetSuccessPage = linkedinSetNewPasswordPage.changePassword("July333#", "July333#");
        Assert.assertTrue(linkedinPasswordResetSuccessPage.isLoaded(),"'Password reset success' page is not loaded.");

        page.LinkedinHomePage linkedinHomePage = linkedinPasswordResetSuccessPage.returnHomePage();
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is not loaded.");
    }
}
