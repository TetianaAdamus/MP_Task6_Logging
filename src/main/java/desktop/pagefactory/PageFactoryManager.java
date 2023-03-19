package desktop.pagefactory;

import desktop.BasePage;
import desktop.HomePage;
import desktop.ProductPage;
import desktop.SearchPage;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage getBasePage(){
        return new BasePage(driver);
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

    public SearchPage getSearchPage(){
        return new SearchPage(driver);
    }

    public ProductPage getProductPage(){
        return new ProductPage(driver);
    }

}
