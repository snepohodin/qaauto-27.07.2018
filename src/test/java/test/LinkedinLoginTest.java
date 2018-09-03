package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinLoginSubmitPage;
import page.LinkedinSearchPage;

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
                {"rdmntest@gmail.com", "July222@"}
//                ,
//                {"rDmNtEST@gmail.coM", "July222@"}
        };
    }

    @Test(dataProvider = "validFieldsCombination")
    public void successfulLoginTest(String userEmail, String userPass) {
        linkedinLoginPage.loginReturnHomePage(userEmail, userPass);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"Home page is not loaded.");
    }

    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                {"",""},
                {"","P@ssword123"},
                {"someone@domain.com",""}
        };
    }

    @Test(dataProvider = "emptyFieldsCombination")
    public void validateEmptyUserEmailAndUserPassword (String userEmail, String userPass) {
        linkedinLoginPage.loginReturnLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(),"User is not on Login page.");
    }

    @DataProvider
    public Object[][] invalidDataFieldsCombination() {
        return new Object[][]{
                {"a", "a", "The text you provided is too short (the minimum length is 3 characters, your text contains 1 character).", "The password you provided must have at least 6 characters."}
//                ,
//                {"aadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comaadwsdftgy@gmail.comasssssssssadwsdftgy@gmail.com", "111111111111","The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).",""},
//                {"rdmntest@gmail.com", "JULY222@","", "Hmm, that's not the right password. Please try again or request a new one."},
//                {"+380954885956", "July222@", "Hmm, we don't recognize that email. Please try again.", ""},
//                {"<script>alert(123)</script>", "1", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
//                {"1", "111111111111", "Be sure to include \"+\" and your country code.", ""}
        };
    }

    @Test (dataProvider = "invalidDataFieldsCombination")
    public void validateUserEmailAndPassword(String userEmail, String userPass, String userEmailValidationText, String userPassValidationText) {
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.loginReturnLoginSubmitPage(userEmail, userPass);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(),"User is not on LoginSubmit page");

        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(), userEmailValidationText,
                "userEmail field has wrong validation message text.");

        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(), userPassValidationText,
                "userPassword field has wrong validation message text.");
    }
}