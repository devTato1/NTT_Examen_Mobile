package com.test.mobile.view;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomeView {
    @AndroidFindBy(id = "com.wt.apkinfo:id/recycler")
    private WebElement mainScreen;
    @AndroidFindBy(id = "//android.widget.TextView[@text=\"AFP Integra\"]")
    private WebElement btn_afp_integra;

    public static By selectApp(String apk_name) {
        return By.xpath("//android.widget.TextView[@text=\"" + apk_name + "\"]");
    }

    public void showView() {
        this.mainScreen.isDisplayed();
    }
}
