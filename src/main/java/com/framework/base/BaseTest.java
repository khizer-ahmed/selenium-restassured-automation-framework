package com.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties config;
    protected PageManager pages;

    @BeforeMethod
    public void setUp() throws IOException {
        config = new Properties();
        config.load(new FileInputStream("src/test/resources/config.properties"));
        String browser = config.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("profile.credentials_enable_service", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(config.getProperty("baseUrl"));
        pages = new PageManager(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
