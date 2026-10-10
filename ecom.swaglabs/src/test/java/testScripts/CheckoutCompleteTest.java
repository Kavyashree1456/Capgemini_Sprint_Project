package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import objRepo.CartPage;
import objRepo.CheckoutCompletePage;
import objRepo.CheckoutOverviewPage;
import objRepo.CheckoutPage;
import objRepo.InventoryItemPom;
import objRepo.InventoryPage;

public class CheckoutCompleteTest extends BaseClass {

    @Test(groups = {"System", "Pos"})
    public void verifyOrderConfirmation() throws IOException, InterruptedException {

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

        CheckoutOverviewPage overview =
                new CheckoutOverviewPage(driver);

        overview.getfinish();
        Thread.sleep(2000);

        // Final confirmation page
        CheckoutCompletePage complete =
                new CheckoutCompletePage(driver);

        complete.getpdf();
        Thread.sleep(2000);

        Assert.assertTrue(
                complete.isOrderSuccessful(),
                "Order confirmation message is not displayed"
        );
    }


    @Test(groups = {"IT", "Pos"})
    public void verifyBackHomeAfterOrder() throws IOException, InterruptedException {

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

        CheckoutOverviewPage overview =
                new CheckoutOverviewPage(driver);

        overview.getfinish();
        Thread.sleep(2000);

        CheckoutCompletePage complete =
                new CheckoutCompletePage(driver);

        Assert.assertTrue(
                complete.isOrderSuccessful(),
                "Order was not completed successfully"
        );

        complete.clickBackHome();
        Thread.sleep(2000);

        InventoryPage inventory =
                new InventoryPage(driver);

        Assert.assertTrue(
                inventory.getDDFilter().isDisplayed(),
                "User is not returned to Inventory page"
        );
    }


    
}