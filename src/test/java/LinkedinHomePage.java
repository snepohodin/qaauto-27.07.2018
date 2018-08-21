import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinHomePage extends BasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavigationItem;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement searchBox;

    public  LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }

    public LinkedinSearchPage homePageReturnSearchPage(String searchInputField) {
        searchBox.sendKeys(searchInputField, Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinSearchPage(browser);
    }
}
