package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private WebDriver driver;

    private By checkoutButton = By.xpath("//button[contains(text(),'Checkout')]");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By finishButton = By.xpath("//button[contains(text(),'Finish')]");
    private By successMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void enterUserDetails(String fName, String lName, String zip) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(zip);
    }

    public void continueCheckout() {
        driver.findElement(continueButton).click();
    }

    public void finishOrder() {
        driver.findElement(finishButton).click();
    }

    public boolean isOrderSuccess() {
        WebElement msg = driver.findElement(successMessage);
        return msg.isDisplayed();
    }

    public String successHeaderText() {
        return driver.findElement(successMessage).getText();
    }
}
