package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    public CheckoutCompletePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    @FindBy(className = "pony_express")
    private WebElement confirmationImage;

    @FindBy(id = "back-to-products")
    private WebElement backHome;
    
    @FindBy(id="generate-pdf-order")
    private WebElement pdf;
    public void getpdf() {
    	pdf.click();
    }


    public boolean isOrderSuccessful() {

        return confirmationMessage.isDisplayed()
                && confirmationMessage.getText()
                        .equals("Thank you for your order!");
    }


    public boolean isConfirmationImageDisplayed() {

        return confirmationImage.isDisplayed();
    }


    public void clickBackHome() {

        backHome.click();
    }
}