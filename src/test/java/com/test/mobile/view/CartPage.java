package com.test.mobile.view;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getProductQuantity(String productName) {
        // 1. Espera de seguridad
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@text='My Cart']")
        ));

        // 2. SCROLL ESPECÍFICO (SOLUCIÓN AL NO-SCROLL)
        try {
            // AQUI ESTA EL CAMBIO CLAVE:
            // Usamos .resourceId("...productRV") para obligar a que scrollee LA LISTA DE PRODUCTOS
            // y no toda la pantalla o el contenedor equivocado.
            String listID = "com.saucelabs.mydemoapp.android:id/productRV";

            String scrollCommand = "new UiScrollable(new UiSelector().resourceId(\"" + listID + "\"))" +
                    ".scrollIntoView(new UiSelector().textContains(\"" + productName + "\"));";

            driver.findElement(AppiumBy.androidUIAutomator(scrollCommand));
            System.out.println(">>> Scroll realizado buscando en la lista RV: " + productName);

        } catch (Exception e) {
            // Si falla el scroll por ID, intentamos un pequeño swipe manual o asumimos que ya se ve
            System.out.println(">>> Aviso: El scroll automático reportó una excepción o el elemento ya estaba ahí.");
        }

        // 3. LEER LA CANTIDAD
        // Buscamos el texto que acabamos de traer a la vista
        String xpathCantidad = "//android.widget.TextView[contains(@text, '" + productName + "')]/../..//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/noTV']";

        try {
            WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath(xpathCantidad)
            ));

            String texto = quantityElement.getText().trim().replaceAll("[^0-9]", "");
            return Integer.parseInt(texto);

        } catch (Exception e) {
            System.out.println("ERROR CRÍTICO: Producto '" + productName + "' no encontrado visualmente tras el scroll.");
            return 0;
        }
    }

    public void goBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
        }
    }
}