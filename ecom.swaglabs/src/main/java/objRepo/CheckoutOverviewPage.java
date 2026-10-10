package objRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
	public CheckoutOverviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	@FindBy(id="finish")
	private WebElement finish;
	public void getfinish() {
		finish.click();
	}
	
	@FindBy(id="cancel")
	private WebElement cancel;
	public void getcancel() {
		cancel.click();
	}
	@FindBy(className = "inventory_item_name")
    private List<WebElement> products;


    public boolean isProductAvailable(String productName) {

        for (WebElement product : products) {

            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }

        return false;
    }
    @FindBy(className = "complete-header")
    private WebElement orderConfirmation;

    public boolean isOrderSuccessful() {
        return orderConfirmation.isDisplayed()
                && orderConfirmation.getText().equals("Thank you for your order!");
    }

}
