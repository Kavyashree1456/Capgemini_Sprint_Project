package objRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "product_sort_container")
    private WebElement ddFilter;
    public WebElement getDDFilter() {
        return ddFilter;
    }


    public void selectFilter(String filterVText) {
        Select sel = new Select(ddFilter);
        sel.selectByVisibleText(filterVText);
    }


    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;


    public void clickProduct(String productName) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        for (WebElement product : productNames) {

            if (product.getText().equalsIgnoreCase(productName)) {

                wait.until(
                        ExpectedConditions.elementToBeClickable(product)
                ).click();

                wait.until(
                        ExpectedConditions.urlContains("inventory-item.html")
                );

                return;
            }
        }

        throw new RuntimeException(
                "Product not found: " + productName
        );
    }


    public void clickAnyProduct() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        int randomIndex =
                (int) (Math.random() * productNames.size());

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        productNames.get(randomIndex)
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains("inventory-item.html")
        );
    }
    
    public void addMultipleProductsToCart(String... productNames) {

        for (String productName : productNames) {

            boolean productFound = false;

            for (WebElement product : driver.findElements(
                    By.className("inventory_item"))) {

                String actualProductName = product.findElement(
                        By.className("inventory_item_name")
                ).getText();

                if (actualProductName.equalsIgnoreCase(productName)) {

                    WebElement addToCartButton = product.findElement(
                            By.tagName("button")
                    );

                    addToCartButton.click();

                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                throw new RuntimeException(
                        "Product not found on Inventory page: " + productName
                );
            }
        }
    }
}