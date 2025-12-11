package com.test.mobile.view;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DetailsAPKView {
    @AndroidFindBy(xpath = "com.wt.apkinfo:id/appName")
    private WebElement appName;

    public void showView() {
        this.appName.isDisplayed();
    }
}
