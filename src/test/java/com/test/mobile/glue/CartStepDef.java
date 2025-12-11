package com.test.mobile.glue; // <--- Paquete corregido a 'test'

import com.test.mobile.step.CartSteps; // Importamos la lógica
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartStepDef {

    private CartSteps cartSteps = new CartSteps();

    @Given("estoy en la aplicación de SauceLabs")
    public void estoy_en_la_aplicacion_de_saucelabs() {
        cartSteps.validarInicio();
    }

    @And("valido que carguen correctamente los productos en la galeria")
    public void valido_carga_productos() {
        cartSteps.validarCargaProductos();
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agrego_unidades_del_producto(int unidades, String producto) {
        cartSteps.agregarProducto(unidades, producto);
    }

    @Then("valido el carrito de compra actualice correctamente")
    public void valido_carrito_actualice() {
        cartSteps.validarCarrito();
    }
}