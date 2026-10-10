package testScripts;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericUtility.BaseClass;
import objRepo.CartPage;
import objRepo.InventoryItemPom;
import objRepo.InventoryPage;


public class InventoryTest extends BaseClass {

	@Test(groups = {"FT","Pos"})
	public void productdetails1() throws Exception {
		InventoryPage ip = new InventoryPage(driver);
		ip.selectFilter("Price (low to high)");
		Thread.sleep(2000);
		String productName="Sauce Labs Onesie";
		driver.findElement(By.xpath("//div[text()='"+productName+"']/ancestor::div[@class='inventory_item_description']/descendant::button")).click();
		
	}
	
	@Test(groups = {"FT", "Pos"})
	public void verifyNameZToAFilter() throws InterruptedException, IOException {

	    InventoryPage ip = new InventoryPage(driver);
	    ip.selectFilter("Name (Z to A)");
	    Select select = new Select(ip.getDDFilter());
	    String selectedOption = select.getFirstSelectedOption().getText();
	    Thread.sleep(2000);
	    ip.clickAnyProduct();
	    Thread.sleep(2000);
	    Assert.assertEquals(
	            selectedOption,
	            "Name (Z to A)",
	            "Name Z to A filter is not selected"
	    );
	}
	
	@Test(groups = {"IT", "Pos"})
	public void productdetails() throws Exception {

		InventoryPage ip = new InventoryPage(driver);
		ip.clickAnyProduct();
		Thread.sleep(2000);
		// Now we are on product details page
		InventoryItemPom item = new InventoryItemPom(driver);
		item.clickAddToCart();
		Thread.sleep(2000);
	}
	
	@Test(groups = {"IT", "Pos"})
	public void verifyInventoryToCart() {

	    InventoryPage ip = new InventoryPage(driver);

	    String productName = "Sauce Labs Backpack";

	    ip.clickProduct(productName);

	    InventoryItemPom item =
	            new InventoryItemPom(driver);

	    item.clickAddToCart();
	    item.clickCart();

	    CartPage cp =
	            new CartPage(driver);

	    Assert.assertTrue(
	            cp.isProductAvailable(productName),
	            "Product selected from Inventory is not available in Cart"
	    );
	}
	
	  @Test(groups = {"IT", "Pos"})
	    public void verifyMultipleProductsAddedToCart() {

	        InventoryPage ip = new InventoryPage(driver);

	        // Add multiple products from Inventory page
	        ip.addMultipleProductsToCart(
	                "Sauce Labs Backpack",
	                "Sauce Labs Bike Light",
	                "Sauce Labs Onesie"
	        );

	        // Open Cart page
	        CartPage cp = new CartPage(driver);
	        cp.getClickcart().click();

	        // Verify all three products are present in Cart
	        Assert.assertTrue(
	                cp.isProductAvailable("Sauce Labs Backpack"),
	                "Sauce Labs Backpack is not available in Cart"
	        );

	        Assert.assertTrue(
	                cp.isProductAvailable("Sauce Labs Bike Light"),
	                "Sauce Labs Bike Light is not available in Cart"
	        );

	        Assert.assertTrue(
	                cp.isProductAvailable("Sauce Labs Onesie"),
	                "Sauce Labs Onesie is not available in Cart"
	        );
	    }

	
	@Test(groups = {"Smoke", "Pos"})
	public void verifyAnyProductSelection() {

	    InventoryPage ip = new InventoryPage(driver);

	    ip.clickAnyProduct();

	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("inventory-item.html"),
	            "Product details page was opened"
	    );
	}
	
	@Test(groups = {"System", "Pos"})
	public void verifyInventoryToCartCompleteFlow() {

	    InventoryPage ip = new InventoryPage(driver);

	    ip.selectFilter("Price (low to high)");

	    String productName = "Sauce Labs Onesie";

	    ip.clickProduct(productName);

	    // Verify the actual product details page
	    System.out.println("Current URL: " + driver.getCurrentUrl());

	    System.out.println(
	            "Product displayed: "
	            + driver.findElement(
	                    By.className("inventory_details_name")
	            ).getText()
	    );

	    InventoryItemPom item = new InventoryItemPom(driver);

	    item.clickAddToCart();

	    item.clickCart();

	    System.out.println("Cart URL: " + driver.getCurrentUrl());

	    CartPage cp = new CartPage(driver);

	    // Print all product names currently in the cart
	    for (WebElement product : driver.findElements(
	            By.className("inventory_item_name"))) {

	        System.out.println("Cart product: " + product.getText());
	    }

	    Assert.assertTrue(
	            cp.isProductAvailable(productName),
	            productName + " is not available in Cart"
	    );
	}
	
	@Test(groups = {"Neg"})
	public void verifyInvalidProductSortOption() throws IOException {

	    InventoryPage inventoryPage = new InventoryPage(driver);
	    wu.takeScreenshot(driver, "Filter Not Found");
	    Assert.assertThrows(
	        org.openqa.selenium.NoSuchElementException.class,
	        () -> inventoryPage.selectFilter("bags"));
	    
	}
	
	
//	@Test(groups = "Neg")
//	public void verifyWrongProductName() {
//
//	    InventoryPage ip = new InventoryPage(driver);
//
//	    String productName = "jwellery";
//	    ip.clickProduct(productName);
//	    
//
//	    Assert.assertThrows(
//	            RuntimeException.class,
//	            () -> ip.clickProduct(productName)
//	    );
//	}
//	
//	@Test(groups = "Neg")
//	public void verifyBlankFilterValue() {
//
//	    InventoryPage ip = new InventoryPage(driver);
//
//	    Assert.assertThrows(
//	            org.openqa.selenium.NoSuchElementException.class,
//	            () -> ip.selectFilter("")
//	    );
//	}
//	
}
