package com.framework.tests.ui;

import com.framework.base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    @Test(description = "Verify successful login")
    public void testValidLogin() {
        pages.loginPage.loginAs("standard_user", "secret_sauce"); 
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User should be redirected to inventory page");
        Assert.assertEquals(pages.inventoryPage.getTitleText(), "Products");
    }

    @Test(description = "Verify adding multiple items to cart")
    public void testAddMultipleItemsToCart() {
        pages.loginPage.loginAs("standard_user", "secret_sauce");
        pages.inventoryPage.addMultipleItemsToCart(2);
        Assert.assertEquals(pages.inventoryPage.getCartCount(), "2");
    }

    @Test(description = "Verify checkout process end-to-end")
    public void testCheckoutFlow() {
        pages.loginPage.loginAs("standard_user", "secret_sauce");
        pages.inventoryPage.addFirstItemToCart();
        pages.inventoryPage.openCart();
        pages.checkoutPage.clickCheckout();
        pages.checkoutPage.enterUserDetails("John", "Doe", "12345");
        pages.checkoutPage.continueCheckout();

        pages.checkoutPage.finishOrder();
        Assert.assertTrue(pages.checkoutPage.isOrderSuccess(), "Order should be successful");
        Assert.assertEquals(pages.checkoutPage.successHeaderText(), "Thank you for your order!");
    }

    @Test(description = "Verify logout functionality")
    public void testLogout() {
        pages.loginPage.loginAs("standard_user", "secret_sauce");
        pages.inventoryPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/"), "User should be logged out");
    }

    @Test(description = "Verify Login with locked user")
    public void testLockedUserLogin() {
        pages.loginPage.loginAs("locked_out_user", "secret_sauce");
        Assert.assertTrue(pages.loginPage.isErrorDisplayed(), "Error message should be displayed for locked user");
        Assert.assertEquals(pages.loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(description = "Verify Login with incorrect password")
    public void testIncorrectPasswordUserLogin() {
        pages.loginPage.loginAs("standard_user", "abcd");
        Assert.assertTrue(pages.loginPage.isErrorDisplayed(), "Error message should be displayed for incorrect password user");
        Assert.assertEquals(pages.loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
    
}
