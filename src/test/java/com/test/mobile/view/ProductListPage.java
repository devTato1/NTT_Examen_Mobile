package com.test.mobile.view;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductListPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public ProductListPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectProduct(String productName) {
        System.out.println(">>> Buscando producto: " + productName);
        try {
            WebElement product = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().description(\"" + productName + "\"));"));
            product.click();
        } catch (Exception e) {
            driver.findElement(AppiumBy.accessibilityId(productName)).click();
        }
    }

    public void goToCart() {
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartRL")
        ));
        cartIcon.click();
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("//android.widget.TextView[@text='Products']")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}