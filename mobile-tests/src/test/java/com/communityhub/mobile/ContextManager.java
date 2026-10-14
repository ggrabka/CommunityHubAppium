package com.communityhub.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContextManager {

    private final AndroidDriver driver;

    public ContextManager(AndroidDriver driver) {
        this.driver = driver;
    }

    public void switchToWebView() throws Exception {

        String webview = new WebDriverWait(
                driver, Duration.ofSeconds(30))
                .until(d -> driver.getContextHandles()
                        .stream()
                        .filter(c -> c.startsWith("WEBVIEW"))
                        .findFirst()
                        .orElse(null));
        driver.context(webview);
    }

    public void switchToNative() {
        driver.context("NATIVE_APP");
    }
}
