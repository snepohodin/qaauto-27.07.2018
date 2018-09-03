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
     * Method for taking current page title.
     */
    public String getCurrentPageTitle() {
        return browser.getTitle();
    }

    /**
     * Method for taking current page URL.
     */
    public String getCurrentPageUrl() {
        return browser.getCurrentUrl();
    }

    /**
     * Abstract method for verification that page was loaded.
     */
    public abstract boolean isLoaded();
}
