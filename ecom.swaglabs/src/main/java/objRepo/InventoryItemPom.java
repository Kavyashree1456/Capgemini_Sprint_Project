package objRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryItemPom {

    WebDriver driver;

    public InventoryItemPom(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private WebElement addToCartButton;

    public void clickAddToCart() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@id,'add-to-cart')]")
                )
        ).click();
    }


    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
    private WebElement addToCartBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartBtn;

    public WebElement getAddToCartBtn() {
        return addToCartBtn;
    }

    public void clickAddToCart1() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@id,'add-to-cart')]")
                )
        ).click();
    }

    public void clickCart() {
        cartBtn.click();
    }
}