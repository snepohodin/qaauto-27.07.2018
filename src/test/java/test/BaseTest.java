package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedinLoginPage;

public class BaseTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @Parameters({"browserName", "envURL"})
    @BeforeMethod
    public void beforeMethod(@Optional("firefox") String browserName,@Optional("https://www.linkedin.com/") String envURL) {
        if (browserName.toLowerCase().equals("firefox")){
            browser = new FirefoxDriver();
        }
        if (browserName.toLowerCase().equals("chrome")){
            browser = new ChromeDriver();
        } else {
            try {
                throw new Exception("browserName "+browserName+"is not supported.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        browser.get(envURL);
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

//    @Parameters({"browserName", "envURL"})
//    @BeforeMethod
//    public void beforeMethod(@Optional("firefox") String browserName,@Optional("ru.linkedin.com") String environmentUrl) {
//        if (browserName.toLowerCase().equals("firefox")){
//            browser = new FirefoxDriver();
//        }
//        if (browserName.toLowerCase().equals("chrome")){
//            browser = new ChromeDriver();
//        } else {
//            try {
//                throw new Exception("browserName "+browserName+"is not supported.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        browser.get("https://www.linkedin.com/");
//        linkedinLoginPage = new LinkedinLoginPage(browser);
//    }

    @Parameters("envURL")
    public void beforeMethod2(@Optional("linkedin.com") String envURL) {
        if (envURL.toLowerCase().equals("ua.linkedin.com")){
            browser = new FirefoxDriver();
            browser.get("ua.linkedin.com");
        }
        if (envURL.toLowerCase().equals("ru.linkedin.com")){
            browser = new FirefoxDriver();
            browser.get("ru.linkedin.com");
        }
        if (envURL.toLowerCase().equals("de.linkedin.com")){
            browser = new FirefoxDriver();
            browser.get("de.linkedin.com");
        } else {
            try {
                throw new Exception("URL "+envURL+" incorrect.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }
}
