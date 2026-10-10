package objRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	 WebDriver driver;

	public CartPage(WebDriver driver) {
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement clickcart;

    @FindBy(id = "continue-shopping")
    private WebElement continueS;

    @FindBy(id = "checkout")
    private WebElement checkout;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartProducts;
    
    @FindBy(xpath = "//button[contains(@id,'remove')]")
    private WebElement remove;
    
    public WebElement getremove() {
    	return remove;
    }

    public WebElement getContinueS() {
        return continueS;
    }

    public WebElement getCheckout() {
        return checkout;
    }

    public WebElement getClickcart() {
        return clickcart;
    }


    public boolean isProductAvailable(String productName) {

        for (WebElement product : cartProducts) {

            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }

        return false;
    }
    
    public void removeProduct(String productName) {

        boolean productFound = false;

        for (WebElement product : driver.findElements(
                By.className("cart_item"))) {

            String actualProductName = product.findElement(
                    By.className("inventory_item_name")
            ).getText();

            if (actualProductName.equalsIgnoreCase(productName)) {

                WebElement removeButton = product.findElement(
                        By.tagName("button")
                );

                removeButton.click();

                productFound = true;
                break;
            }
        }

        if (!productFound) {
            throw new RuntimeException(
                    "Product not found in Cart: " + productName
            );
        }
    }
}