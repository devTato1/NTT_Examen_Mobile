package com.test.mobile.step; // <--- Paquete corregido a 'test'

import com.test.mobile.config.MobileDriverManager;
import com.test.mobile.view.CartPage;
import com.test.mobile.view.ProductDetailsPage;
import com.test.mobile.view.ProductListPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;

public class CartSteps {

    // Declaramos las Pages (Views)
    private ProductListPage productListPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;

    // Variables de estado
    private int unitsToAdd;
    private String currentProduct;

    public CartSteps() {
        // Inicializamos driver y pages
        AndroidDriver driver = (AndroidDriver) MobileDriverManager.getDriver();
        productListPage = new ProductListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    public void validarInicio() {
        // Lógica inicial si fuera necesaria
    }

    public void validarCargaProductos() {
        Assert.assertTrue("ERROR: No cargaron los productos", productListPage.isPageLoaded());
    }

    public void agregarProducto(int unidades, String producto) {
        this.unitsToAdd = unidades;
        this.currentProduct = producto;

        productListPage.selectProduct(producto);
        productDetailsPage.addQuantity(unidades);
        productDetailsPage.addToCart();
        productDetailsPage.goBackToCatalog();
    }

    public void validarCarrito() {
        productListPage.goToCart();

        int actualQty = cartPage.getProductQuantity(currentProduct);

        System.out.println(">>> Validando: Esperado=" + unitsToAdd + " | Actual=" + actualQty);

        Assert.assertEquals(
                "BUG: La cantidad en el carrito es incorrecta.",
                unitsToAdd,
                actualQty
        );

        cartPage.goBack();
    }
}