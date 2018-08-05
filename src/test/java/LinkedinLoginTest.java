import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {


    @Test
    public void successfulLoginTest () {
        WebDriver browser = new FirefoxDriver();
        // maximize the browser window
        browser.manage().window().maximize();
        browser.get("https://www.linkedin.com/");
        WebElement userEmailField = browser.findElement(By.xpath("//input[@id ='login-email']"));
        WebElement userPasswordField = browser.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = browser.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("rdmntest@gmail.com");
        userPasswordField.sendKeys("July222@");
        signInButton.click();

        //url verification
        String url = browser.getCurrentUrl();
        Assert.assertEquals(url, "https://www.linkedin.com/feed/" ,"Invalid URL");

        //title verification
        String title = browser.getTitle();
        Assert.assertEquals(title,"LinkedIn", "Invalid title");

        //upgrade button verification
        WebElement upgradeButton = browser.findElement(By.xpath("//a[@data-control-name='premium_nav_upsell_text_click']"));
        Assert.assertEquals(upgradeButton.getText(), "Free Upgrade to Premium","Alert box has incorrect message.");
    }

    @Test
    public void negativeLoginTest () {
        WebDriver browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        WebElement userEmailField = browser.findElement(By.xpath("//input[@id ='login-email']"));
        WebElement userPasswordField = browser.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInButton = browser.findElement(By.xpath("//input[@id='login-submit']"));

        userEmailField.sendKeys("a@b/c");
        userPasswordField.sendKeys("kek");
        signInButton.click();

        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert box has incorrect message.");
    }

}
