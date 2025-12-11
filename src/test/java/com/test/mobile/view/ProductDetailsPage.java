package com.test.mobile.view;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public ProductDetailsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addQuantity(int quantity) {
        if (quantity <= 1) return;

        // ESTRATEGIA NUEVA: Scroll hasta el botón "Add to cart" (que siempre está al final)
        // Al bajar hasta el fondo, el botón "+" quedará visible obligatoriamente.
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().description(\"Tap to add product to cart\"));"));
        } catch (Exception e) {
            System.out.println("No fue necesario hacer scroll o falló.");
        }

        // Ahora sí, buscamos el "+"
        WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Increase item quantity")
        ));

        for (int i = 1; i < quantity; i++) {
            plusButton.click();
        }
    }

    public void addToCart() {
        // Scroll preventivo también para el botón de "Add to Cart"
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().description(\"Tap to add product to cart\"));"));
        } catch (Exception e) {
        }

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Tap to add product to cart")
        ));
        addButton.click();
    }

    public void goBackToCatalog() {
        // Pausa breve para estabilidad antes de volver
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
        driver.navigate().back();
    }
}