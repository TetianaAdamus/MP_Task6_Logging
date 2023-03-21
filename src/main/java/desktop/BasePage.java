package desktop;

import static constants.Constants.BOOK_DEPOSITORY;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class BasePage {
    @FindBy(css = "button[class='btn btn-sm btn-yes']")
    WebElement acceptCookie;

    WebDriver driver;

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public Map<String, String> pagesNamesMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Home page", BOOK_DEPOSITORY);
        map.put("Basket page", BOOK_DEPOSITORY + "basket");
        map.put("Search page", BOOK_DEPOSITORY + "search");
        map.put("Bestsellers Page", BOOK_DEPOSITORY + "bestsellers");
        return map;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pageUrl(String page) {
        return pagesNamesMap().get(page);
    }

    public boolean checkUrl(String pageName) {
        return driver.getCurrentUrl().contains(pageUrl(pageName));
    }

    public void acceptCookie(){
        logger.info("There is no popup to accept cookies");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String cookie = javascriptExecutor.executeScript("return document.cookie.includes('ACCEPT_ALL')").toString();
        if(cookie.equals("false")) {
            acceptCookie.click();
        }
    }

}
