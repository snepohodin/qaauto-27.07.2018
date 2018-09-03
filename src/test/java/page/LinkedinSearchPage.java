package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Page Object class for LinkedinSearchPage.
 */
public class LinkedinSearchPage extends BasePage {

    @FindBy(xpath = "//h3[contains(@class, 'results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='search-result__wrapper']")
    private WebElement searchResultBlock;

    /**
     * Constructor of LinkedinPasswordResetSuccessPage class.
     * @param browser - WebDriver instance from test
     */
    public LinkedinSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchResultBlock, 10);
    }

    /**
     * Method for verification that page was loaded.
     * Used getCurrentPageTitle and getCurrentPageUrl methods from BasePage.
     */
    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
                && getCurrentPageTitle().contains("| Search | LinkedIn")
                && getCurrentPageUrl().contains("/search/results/");
    }

    /**
     * Method for counting a number of search results.
     */
    public int getSearchResultsCount() {
        return searchResults.size();
    }
}
