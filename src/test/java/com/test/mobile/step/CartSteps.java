package com.test.mobile.step;

import com.test.mobile.config.MobileDriverManager;
import com.test.mobile.view.CartPage;
import com.test.mobile.view.ProductDetailsPage;
import com.test.mobile.view.ProductListPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;

public class CartSteps {

    private ProductListPage productListPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    private int unitsToAdd;
    private String currentProduct;

    public CartSteps() {
        AndroidDriver driver = (AndroidDriver) MobileDriverManager.getDriver();
        productListPage = new ProductListPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    public void validarInicio() {
        boolean isLoaded = productListPage.isPageLoaded();
        Assert.assertTrue("ERROR FATAL: App no cargó", isLoaded);

        // FOTO 1: Al iniciar
        MobileDriverManager.screenShot("1_Inicio_App");
    }

    public void validarCargaProductos() {
        Assert.assertTrue(productListPage.isPageLoaded());
        // FOTO 2: Valdiar Productos
        MobileDriverManager.screenShot("2_Carga de productos");
    }

    public void agregarProducto(int unidades, String producto) {
        this.unitsToAdd = unidades;
        this.currentProduct = producto;

        // Seleccionamos el producto
        productListPage.selectProduct(producto);

        // Agregamos cantidad y al carrito
        productDetailsPage.addQuantity(unidades);
        productDetailsPage.addToCart();

        // FOTO 3: Evidencia de que se pulsó "Add to Cart"
        MobileDriverManager.screenShot("3_Agrego_Producto"); // <--- FOTO AQUI

        productDetailsPage.goBackToCatalog();
    }

    public void validarCarrito() {
        productListPage.goToCart();

        int actualQty = cartPage.getProductQuantity(currentProduct);

        // FOTO 4: Evidencia final dentro del carrito antes de validar
        MobileDriverManager.screenShot("4_Carrito_Final");

        Assert.assertEquals("La cantidad no coincide", unitsToAdd, actualQty);

        cartPage.goBack();
    }
}