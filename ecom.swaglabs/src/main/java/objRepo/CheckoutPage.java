package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(id="first-name")
	private WebElement firstname;
	public WebElement getfirstname() {
		return firstname;
	}
	
	@FindBy(id="last-name")
	private WebElement lastname;
	public WebElement getlastname() {
		return lastname;
    }
	
	@FindBy(id="postal-code")
	private WebElement zipcode;
	public WebElement getzipcode() {
		return zipcode;
	}
	
	@FindBy(id ="continue")
	private WebElement continuebtn;
	public WebElement continuebtn() {
		return continuebtn;
	}
	
	public void info(String Firstname,String Lastname,String Zipcode) {
	firstname.sendKeys(Firstname);
	lastname.sendKeys(Lastname);
	zipcode.sendKeys(Zipcode);
	continuebtn.click();
	}
}