import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage {

    WebDriver browser;
    WebElement profileNavigationItem;

    public LinkedinHomePage(WebDriver browser){
        this.browser = browser;
        initElements();
    }

    public void initElements() {
        profileNavigationItem = browser.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    public boolean isProfileNavigationItemDisplayed() {
        if (profileNavigationItem.isDisplayed()) {
            return true;
        }
        return false;
    }
}
