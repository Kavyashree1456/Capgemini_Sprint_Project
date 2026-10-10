package testScripts;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelUtility;
import objRepo.CartPage;
import objRepo.InventoryItemPom;
import objRepo.InventoryPage;

public class CartTest extends BaseClass {


    @Test(groups = {"FT", "Pos"})
    public void verifyRemoveProductFromCart() {

        InventoryPage ip = new InventoryPage(driver);
        String productName = "Sauce Labs Backpack";
        ip.clickProduct(productName);

        InventoryItemPom item = new InventoryItemPom(driver);

        item.clickAddToCart();

        item.clickCart();

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
                cp.isProductAvailable(productName),
                "Product is not available before removal"
        );

        cp.getremove().click();

        Assert.assertFalse(
                cp.isProductAvailable(productName),
                "Product was not removed from Cart"
        );
    }
  
    
    @Test(groups = {"IT", "Pos"})
    public void verifyRemoveSingleProductFromMultipleProducts() {

        InventoryPage ip = new InventoryPage(driver);

        // Add multiple products
        ip.addMultipleProductsToCart(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Onesie"
        );

        // Open cart
        CartPage cp = new CartPage(driver);
        cp.getClickcart().click();

        // Verify all products are initially present
        Assert.assertTrue(
                cp.isProductAvailable("Sauce Labs Backpack"),
                "Backpack is missing before removal"
        );

        Assert.assertTrue(
                cp.isProductAvailable("Sauce Labs Bike Light"),
                "Bike Light is missing before removal"
        );

        Assert.assertTrue(
                cp.isProductAvailable("Sauce Labs Onesie"),
                "Onesie is missing before removal"
        );

        // Remove only one product
        cp.removeProduct("Sauce Labs Bike Light");

        // Verify removed product is absent
        Assert.assertFalse(
                cp.isProductAvailable("Sauce Labs Bike Light"),
                "Bike Light should not appear after removal"
        );

        // Verify remaining products are still present
        Assert.assertTrue(
                cp.isProductAvailable("Sauce Labs Backpack"),
                "Backpack should remain in the cart"
        );

        Assert.assertTrue(
                cp.isProductAvailable("Sauce Labs Onesie"),
                "Onesie should remain in the cart"
        );
    }
    @Test(groups = {"Smoke", "Pos"})
    public void verifyCheckoutButton() throws IOException, InterruptedException {

        InventoryPage ip = new InventoryPage(driver);

        ExcelUtility eu = new ExcelUtility();

        String productName = eu.getDataFromExcel("Sheet1", 1, 0);

        ip.clickProduct(productName);

        Thread.sleep(2000);

        InventoryItemPom item = new InventoryItemPom(driver);

        item.clickAddToCart();

        item.clickCart();

        CartPage cp = new CartPage(driver);

        Assert.assertTrue(
                cp.getCheckout().isDisplayed(),
                "Checkout button is not displayed"
        );

        cp.getCheckout().click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one"),
                "User is not navigated to Checkout page"
        );
    }
  
    @Test(groups = "Neg")
    public void verifyUnselectedProductNotInCart() throws InterruptedException, IOException {

        InventoryPage ip = new InventoryPage(driver);

        String selectedProduct = "Sauce Labs Backpack";
        String unselectedProduct = "Sauce Labs Bike Light";
        Thread.sleep(2000);
        // Add only the selected product
        ip.clickProduct(selectedProduct);
        Thread.sleep(2000);
        InventoryItemPom item = new InventoryItemPom(driver);
        item.clickAddToCart();
        item.clickCart();
        Thread.sleep(2000);
        CartPage cp = new CartPage(driver);
        wu.takeScreenshot(driver, "unselectedProduct");
        
        // Verify selected product is present
        Assert.assertTrue(
                cp.isProductAvailable(selectedProduct),
                "Selected product was not added to cart"
        );
        
        // Verify unselected product is absent
        Assert.assertFalse(
                cp.isProductAvailable(unselectedProduct),
                "Product that was never selected appeared in cart"
        );
    }
    
   
}