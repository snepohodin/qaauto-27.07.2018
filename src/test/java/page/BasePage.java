package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Parent class for Linkedin page objects.
 */
public abstract class BasePage {
    protected WebDriver browser;
    protected static GMailService gMailService = new GMailService();

    /**
     * @param webElement - web element which should be found on the page.
     * @param timeOutInSeconds - timeout for webElement waiting.
     */
    public WebElement waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    /**
     * Taking current page title.
     */
    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    /**
     * Taking current page URL.
     */
    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }

    /**
     * Verification that current page was loaded.
     */
    public abstract boolean isLoaded();
}
