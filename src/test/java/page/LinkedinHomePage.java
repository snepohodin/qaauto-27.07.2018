package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinHomePage
 */
public class LinkedinHomePage extends BasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavigationItem;

    @FindBy(xpath = "//input[@role='combobox']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@placeholder='Search' and @role]")
    private WebElement searchField;

    /**
     * Constructor of LinkedinHomePage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(profileNavigationItem, 10);
    }

    /**
     * Verification that page was loaded.
     * @return - profileNavigationItem.isDisplayed, getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return profileNavigationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }

    /**
     * Page Factory which returns next page(LinkedinSearchPage) after search was done
     * @param searchTerm - string parameter for a text which should be find in search field
     */
    public LinkedinSearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedinSearchPage(browser);
    }
}
