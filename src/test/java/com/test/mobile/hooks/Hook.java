package com.test.mobile.hooks;

import com.test.mobile.config.MobileDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    // ID de la app para poder reiniciarla
    private static final String APP_PACKAGE = "com.saucelabs.mydemoapp.android";

    @BeforeAll()
    public static void beforeAll() {
        // Código para inicializar
        String platform = System.getProperty("platform", "android");
        String serverUrl = System.getProperty("appiumServerUrl", "http://127.0.0.1:4723/");
        if (MobileDriverManager.getDriver() == null) {
            MobileDriverManager.startDriver(platform, serverUrl);
        }
    }

    @Before
    public void setUp(Scenario scenario) {
        // Pasamos el escenario al Manager para que pueda adjuntar fotos durante el test
        MobileDriverManager.setScenario(scenario);
    }

    @After
    public void tearDown(Scenario scenario) {
        // Obtenemos el driver actual
        AndroidDriver driver = (AndroidDriver) MobileDriverManager.getDriver();

        if (driver != null) {
            // 1. CAPTURA DE PANTALLA EN CASO DE ERROR
            if (scenario.isFailed()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Captura de Error");
                    System.out.println(">>> FOTO TOMADA: El escenario falló. Revisa el reporte.");
                } catch (Exception e) {
                    System.out.println(">>> Error tomando captura: " + e.getMessage());
                }
            }

            // 2. REINICIAR LA APP
            try {
                driver.terminateApp(APP_PACKAGE);
                driver.activateApp(APP_PACKAGE);
                System.out.println(">>> App reiniciada para el siguiente escenario.");
            } catch (Exception e) {
                System.out.println(">>> No se pudo reiniciar la app: " + e.getMessage());
            }
        }
    }

    @AfterAll()
    public static void afterAll() {
        MobileDriverManager.stopDriver();
    }
}