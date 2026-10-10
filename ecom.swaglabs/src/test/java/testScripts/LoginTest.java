
package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.WebDriverUtility;
import objRepo.LoginPage;
import genericUtility.FileUtility;

public class LoginTest {

    @Test(groups = "Neg")
    public void verifyInvalidPassword() throws Exception {

        WebDriverUtility wu = new WebDriverUtility();
        FileUtility fu = new FileUtility();

        WebDriver driver = wu.launchBrowser("chrome");

        try {
            driver.get(fu.getDataFromPropertyFile("url"));

            LoginPage lp = new LoginPage(driver);

            lp.login(
                fu.getDataFromPropertyFile("username"),
                "InvalidPassword123"
            );
            wu.takeScreenshot(driver, "InvalidPassword");
            Assert.assertTrue(
                lp.getErrorMessage().contains(
                    "Username and password do not match"
                ),
                "Error message was not displayed for invalid password"
            );

        } finally {
            driver.quit();
        }
    }

    @Test(groups = "Neg")
    public void verifyInvalidUsername() throws Exception {

        WebDriverUtility wu = new WebDriverUtility();
        FileUtility fu = new FileUtility();

        WebDriver driver = wu.launchBrowser("chrome");

        try {
            driver.get(fu.getDataFromPropertyFile("url"));

            LoginPage lp = new LoginPage(driver);

            lp.login(
                "invalid_user123",
                fu.getDataFromPropertyFile("password")
            );
            wu.takeScreenshot(driver, "InvalidUsername");
            Assert.assertTrue(
                lp.getErrorMessage().contains(
                    "Username and password do not match"
                ),
                "Error message was not displayed for invalid username"
            );
        } finally {
            driver.quit();
        }
    }
}
