package com.framework.base;

import com.framework.pages.*;

import org.openqa.selenium.WebDriver;

public class PageManager {
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CheckoutPage checkoutPage;

    public PageManager(WebDriver driver) {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }
}
