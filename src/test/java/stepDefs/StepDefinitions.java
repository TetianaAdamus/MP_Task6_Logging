package stepDefs;

import static constants.Constants.BOOK_PRODUCT_URL;
import static org.assertj.core.api.Assertions.assertThat;

import desktop.pagefactory.PageFactoryManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class StepDefinitions {

    WebDriver driver;
    PageFactoryManager pageFactoryManager=new PageFactoryManager(driver);

    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    @Before
    public void driverSetUp() {
        logger.warn("Driver is not set up");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.warn("Page factory is not created");
        pageFactoryManager = new PageFactoryManager(driver);

    }

    @Given("I open Book Depository")
    public void openHomePage() {
        pageFactoryManager.getHomePage().openHomePage();
    }

    @Given("I open Thinking in Java product")
    public void openProduct() {
        driver.get(BOOK_PRODUCT_URL);
    }

    @Given("I manually accept cookie")
    public void acceptCookie() {
        pageFactoryManager.getBasePage().acceptCookie();
    }

    @When("I apply the following search filters")
    public void chooseSearchFilters(Map<String, String> filter) {
        pageFactoryManager.getSearchPage().selectFilter(filter);
    }

    @When("I search for {string}")
    public void search(String keyword) {
        pageFactoryManager.getHomePage().search(keyword);
    }

    @Then("Search results contain only the following products")
    public void searchResultsContainOnlyProducts(List<String> bookList) {
        assertThat(bookList).isEqualTo(pageFactoryManager.getSearchPage().searchResultBooksListText());
    }


    @Then("I am on the {string} product page")
    public void verifyProductPage(String productName) {
        assertThat(pageFactoryManager.getProductPage().productName()).isEqualTo(productName);

    }

    @Then("I open category {string}")
    public void iOpenCategory(String categoryName) {
        pageFactoryManager.getHomePage().openCategory(categoryName);
    }

    @Then("I am on the {string}")
    public void verifyPage(String page) {
        assertThat(pageFactoryManager.getHomePage().checkUrl(page)).isTrue();
    }

    @Then("Search results contain the following products")
    public void searchResultsContainProducts(List<String> productList) {
        assertThat(pageFactoryManager.getSearchPage().isProductsOnSearchPage(productList)).isTrue();
    }

    @After(order = 100)
    public void clearCoolies() {
        driver.manage().deleteAllCookies();
    }

    @After(order = 99)
    public void createScreenshot(Scenario scenario) {
        if(scenario.isFailed()){
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                File screenshot = new File("build/screenshots/" + scenario + ".png");
                FileUtils.copyFile(tempFile, screenshot);
                System.err.println("Screenshot saved to: " + screenshot.getCanonicalPath());
                if(screenshot.exists()){
                    logger.info("screenshot exist");
                    String screenshotName = scenario.getName().replaceAll(" ", "_");
                    byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(sourcePath, "image/png", screenshotName);
                }
                else {
                    logger.error("screenshot doesn't exist");
                }
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

}
