package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgermenuPage {

	WebDriver driver;
	public HamburgermenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	public void clickMenu() {
		menuButton.click();
	}


	// Logout

	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutButton;

	public void clickLogout() {
		logoutButton.click();
	}
	public void logout() {
		clickMenu();
		clickLogout();
	}
}

