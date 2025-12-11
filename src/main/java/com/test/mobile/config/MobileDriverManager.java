package com.test.mobile.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {
    public static AppiumDriver driver;
    private static Scenario scenario;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void startDriver(String plaform, String serverUrl) {
        if (driver != null) return;
        Config cfg = new Config(plaform);
        Capabilities caps = "ios".equalsIgnoreCase(plaform)
                ? DesiredCapsFactory.forIOS(cfg)
                : DesiredCapsFactory.forAndroid(cfg);
        try {
            URL url = new URL(serverUrl != null ? serverUrl : System.getProperty("appiumServerUrl", "http://127.0.0.1:4723/"));
            driver = "ios".equalsIgnoreCase(plaform)
                    ? new IOSDriver(url, caps)
                    : new AndroidDriver(url, caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

    public static void screenShot(){

        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        scenario.attach(evidencia, "image/png", "evidencias");

    }
}
