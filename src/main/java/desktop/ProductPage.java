package desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(css = "h1[itemprop='name']")
    WebElement productTitle;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String productName(){
        return productTitle.getText().trim();
    }

}
