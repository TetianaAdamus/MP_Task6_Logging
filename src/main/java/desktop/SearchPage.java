package desktop;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SearchPage extends BasePage {

    @FindBy(css = ".book-item")
    List<WebElement> searchResultBooksList;

    @FindBy(css = "div.form-group select[name='price']")
    WebElement priceRange;

    @FindBy(css = "div.form-group select[name='availability']")
    WebElement availability;

    @FindBy(css = "div.form-group select[name='searchLang']")
    WebElement language;

    @FindBy(css = "div.form-group select[name='format']")
    WebElement format;

    @FindBy(css = "[class=' js'] div.page-slide .filter-menu button")
    WebElement filterMenuButton;


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<String> searchResultBooksListText() {
        List<String> searchResultTextList = new ArrayList<>();
        searchResultBooksList.forEach(webElement -> searchResultTextList.add(webElement.findElement(By.cssSelector("h3"
                + ".title")).getText().trim()));
        return searchResultTextList;
    }

    public boolean isProductsOnSearchPage(List<String> products) {
        return new HashSet<>(searchResultBooksListText()).containsAll(products);
    }

    private Map<String, WebElement> facetMap() {
        Map<String, WebElement> facetMap = new HashMap<>();
        facetMap.put("Price range", priceRange);
        facetMap.put("Availability", availability);
        facetMap.put("Language", language);
        facetMap.put("Format", format);
        return facetMap;
    }

    public void selectFilter(Map<String, String> filter) {
        for (Map.Entry<String, String> entry : filter.entrySet()) {
            Select selectFilter = new Select(facetMap().get(entry.getKey()));
            selectFilter.selectByVisibleText(entry.getValue());
        }
        filterMenuButton.click();
    }

    public void getAddProductToBasketButton(String buttonName, String productName) {
        searchResultBooksList.stream()
                .filter(product -> product.findElement(By.cssSelector("h3.title")).getText().trim().equals(productName))
                .collect(toList()).get(INTEGER_ZERO)
                .findElement(By.cssSelector(format("a[class*='%s']", buttonName.toLowerCase().replace(" ", "-"))))
                .click();
    }


}
