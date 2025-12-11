package com.test.mobile.config;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;

public class DesiredCapsFactory {

    public static Capabilities forAndroid(Config cfg) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(cfg.get("platformName"));
        options.setAutomationName(cfg.get("automationName"));

        if (cfg.get("app") != null) options.setApp(cfg.get("app"));
        if (cfg.get("udid") != null) options.setUdid(cfg.get("udid"));
        if (cfg.get("deviceName") != null) options.setDeviceName(cfg.get("deviceName"));
        if (cfg.get("appPackage") != null) options.setAppPackage(cfg.get("appPackage"));
        if (cfg.get("appActivity") != null) options.setAppActivity(cfg.get("appActivity"));
        if (cfg.get("autoGrantPermissions") != null)
            options.setCapability("autoGrantPermissions", Boolean.parseBoolean(cfg.get("autoGrantPermissions")));

        options.setCapability("appium:fullReset", false);
        options.setCapability("appium:noReset", false);
        options.setCapability("appium:appWaitActivity", "*");
        options.setCapability("appium:appWaitDuration", 15000);
        options.setCapability("appium:androidInstallTimeout", 12000);
        options.setCapability("appium:forceAppLaunch", true);

        return options;
    }

    public static Capabilities forIOS(Config cfg) {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName(cfg.get("platformName"));
        return options;
    }
}