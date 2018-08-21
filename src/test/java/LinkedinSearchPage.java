import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchPage extends BasePage {

    @FindBy(xpath = "//div[@class='blended-srp-results-js ph0 pv4 container-with-shadow']")
    private WebElement searchTable;

    @FindBy(xpath = "//div[@class='search-result__wrapper']")
    private WebElement searchResultBlock;

    @FindBy(xpath = "//div[@class='search-result__wrapper']")
    private List<WebElement> searchResults;

    public LinkedinSearchPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean searchResultsCount() {
        if (searchResults.size() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean searchTermVerification() {
        for(WebElement searchResult : searchResults){
                if(searchResult.getText().toLowerCase().contains("hr"))
                    return true;
        }
        return false;
    }

    public boolean isLoaded() {
        return searchTable.isDisplayed();
    }

    public void scrollDown() {
        ((JavascriptExecutor)browser).executeScript("scroll(0,1200)");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
