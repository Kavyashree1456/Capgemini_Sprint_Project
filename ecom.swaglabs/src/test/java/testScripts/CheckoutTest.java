package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objRepo.CartPage;
import objRepo.CheckoutPage;
import objRepo.InventoryItemPom;
import objRepo.InventoryPage;

public class CheckoutTest extends BaseClass {
	@Test(groups = {"IT", "Pos"})
	public void checkout() throws IOException, InterruptedException {
		InventoryPage ip = new InventoryPage(driver);
	    String productName = "Sauce Labs Onesie";
	    ip.clickProduct(productName);
	    Thread.sleep(2000);

	    InventoryItemPom item = new InventoryItemPom(driver);
	    item.clickAddToCart();
	    Thread.sleep(2000);
	    item.clickCart();
	    Thread.sleep(2000);
	    
		CartPage cp = new CartPage(driver);
		cp.getCheckout().click();
		Thread.sleep(2000);
		
		CheckoutPage chk = new CheckoutPage(driver);
		String firstname = fu.getDataFromPropertyFile("firstname");
	    String lastname = fu.getDataFromPropertyFile("lastname");
	    String zipcode = fu.getDataFromPropertyFile("zipcode");
	    chk.info(firstname, lastname, zipcode);
	    Thread.sleep(2000);
//		chk.continuebtn();
	//	Thread.sleep(2000);
	}
	@Test(groups =  "Neg")
	public void verifyCheckoutWithEmptyFirstName() throws InterruptedException, Exception {

	    InventoryPage ip = new InventoryPage(driver);

	    String productName = "Sauce Labs Onesie";

	    ip.clickProduct(productName);
	    Thread.sleep(2000);

	    InventoryItemPom item = new InventoryItemPom(driver);
	    item.clickAddToCart();
	    Thread.sleep(2000);

	    item.clickCart();
	    Thread.sleep(2000);

	    CartPage cp = new CartPage(driver);
	    cp.getCheckout().click();
	    Thread.sleep(2000);

	    CheckoutPage chk = new CheckoutPage(driver);

	    String lastname = fu.getDataFromPropertyFile("lastname");
	    String zipcode = fu.getDataFromPropertyFile("zipcode");

	    // First name intentionally left empty
	    chk.getlastname().sendKeys(lastname);
	    chk.getzipcode().sendKeys(zipcode);

	    chk.continuebtn().click();
	    Thread.sleep(2000);
	    wu.takeScreenshot(driver, "CheckoutWithEmptyFirstName");
	    Assert.assertTrue(
	        driver.getCurrentUrl().contains("checkout-step-one"),
	        "User should not proceed without First Name"
	    );
	}
	
	

}
