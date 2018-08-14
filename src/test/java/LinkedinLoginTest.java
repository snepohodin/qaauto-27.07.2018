import com.sun.istack.internal.localization.NullLocalizable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

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

    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                {"rdmntest@gmail.com", "July222@"},
                {"RDMNTEST@gmail.com", "July222@"},
                {"rDmNtEST@gmail.coM", "July222@"}
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is not loaded.");
    }

    @Test
    public void negativeLoginTest() {
        linkedinLoginPage.login("a@b/c","kek");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                {"", ""},
                {"", "P@ssword123"},
                {"someone@domain.com", ""}
        };
    }

    @Test(dataProvider = "emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword (String userEmail, String userPass) {
        linkedinLoginPage.login(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(),"User is not on Login page.");
    }

    @Test
    public void validateShortUserEmailAndPassword() {
        linkedinLoginPage.login("a","a");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(),"User is not on LoginSubmit page");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "userEmail field has wrong validation message text.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(),
                "The password you provided must have at least 6 characters.",
                "userPassword field has wrong validation message text.");
    }

    //Verification of 'Not the right password' error message
    @Test
    public void negativeLoginTestNotRightPasswordErrorText() {
        linkedinLoginPage.login("rdmntest@gmail.com","JULY222@");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionPasswordLoginErrorText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Alert box has incorrect message.");
    }

    //Verification of 'can't recognize email' error message
    @Test
    public void negativeLoginCantRecognizeLoginText() {
        linkedinLoginPage.login("+380954885956","July222@");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionKeyLoginErrorText(),
                "Hmm, we don't recognize that email. Please try again.",
                "Session key login error is wrong.");
    }

    //Verification of 'country code' error message
    @Test
    public void negativeLoginTestbeSureToIncludeLoginErrorText() {
        linkedinLoginPage.login("1","111111111111");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionKeyLoginErrorText(),
                "Be sure to include \"+\" and your country code.",
                "Session key login error is wrong.");
    }

    //Password field verification with 1 symbol
    @Test
    public void negativeLoginTestOneSymbolPassword() {
        linkedinLoginPage.login("1","1");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionPasswordLoginErrorText(),
                "The password you provided must have at least 6 characters.",
                "Password login error text is wrong.");
    }

    //Password field verification with 5 symbols
    @Test
    public void negativeLoginTestFiveSymbolPassword() {
        linkedinLoginPage.login("1","12345");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionPasswordLoginErrorText(),
                "The password you provided must have at least 6 characters.",
                "Password login error text is wrong.");
    }

    //Password field verification with 6 symbols
    @Test
    public void negativeLoginTestSixSymbolPassword() {
        linkedinLoginPage.login("1","123456");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionPasswordLoginErrorText(),
                "", "Password login error text isn't empty.");
    }

    //Verification of 'text too short' error message, one character in Email field
    @Test
    public void negativeLoginTestTextTooShort() {
        linkedinLoginPage.login("a","111111111111");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionKeyLoginErrorText(),
                "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).",
                "Session key login error is wrong.");
    }

    //XSS injection
    @Test
    public void negativeLoginTestXss() {
        linkedinLoginPage.login("<script>alert(123)</script>","1");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionKeyLoginErrorText(),
                "Please enter a valid email address.",
                "Alert box has incorrect message.");
        Assert.assertEquals(linkedinLoginSubmitPage.getSessionPasswordLoginErrorText(),
                "The password you provided must have at least 6 characters.",
                "Password login error text is wrong.");
    }

    //Verification of 'The text you provided is too long' error message, 129 characters in Email field
    @Test
    public void negativeLoginTestTextTooLong() {
        linkedinLoginPage.login(
                "aadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comasssssssssadwsdftgy@gmail.com",
                "111111111111");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);

        Assert.assertEquals(linkedinLoginSubmitPage.getSessionKeyLoginErrorText(),
                "The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).",
                "Session key login error is wrong.");
    }

    //Verification of disabled SUBMIT button in case of empty login and password fields
    @Test
    public void negativeLoginTestButtonIsDisabled() {
        linkedinLoginPage.login("","");
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);

        Assert.assertFalse(linkedinLoginPage.isSignInButtonIsDisabled(),"Button is enabled");
    }
}