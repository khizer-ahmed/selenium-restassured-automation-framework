package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    private By addToCartButtons = By.xpath("//button[contains(text(),'Add to cart')]");
    private By productsTitle = By.className("title");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(productsTitle).getText();
    }

    public void addFirstItemToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public void addMultipleItemsToCart(int count) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (int i = 0; i < count && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();
    }
}
