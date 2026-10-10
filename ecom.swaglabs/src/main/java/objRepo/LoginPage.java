package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// Username
	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement username;
	public WebElement getUsername() {
		return username ;
	}


	// Password
	@FindBy(id = "password")
	private WebElement password;
	public WebElement getPassword( ) {
		return password;
	}


	// Login button
	@FindBy(xpath = "//input[@class='submit-button btn_action']")
	private WebElement loginbtn;
	public WebElement getLoginbtn() {
		return loginbtn;
	}


	// Error message
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorMessage;
	public String getErrorMessage() {
		return errorMessage.getText();
	}


	// Complete login
	public void login(String un, String pwd) throws Exception {
		Thread.sleep(2000);
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbtn.click();
	}
}