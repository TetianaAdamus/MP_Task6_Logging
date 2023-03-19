package desktop;

import static constants.Constants.BOOK_DEPOSITORY;
import static constants.Constants.TIME_TO_WAIT;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomePage extends BasePage{
    private @FindBy(css = ".text-input")
    WebElement searchInputField;

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    private @FindBy(css = ".secondary-header-wrap li[class='mob-nav-shop'] a")
    List<WebElement> categories;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(){
        driver.get(BOOK_DEPOSITORY);
        driver.manage().timeouts().implicitlyWait(TIME_TO_WAIT, TimeUnit.SECONDS);
        logger.info("The page is not opened");
    }

    public void search(String keyword) {
        searchInputField.sendKeys(keyword, Keys.ENTER);
    }

    public void openCategory(String name){
        categories.stream().filter(category ->category.getText().equals(name)).collect(Collectors.toList()).get(0).click();
    }

}
