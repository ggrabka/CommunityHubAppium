package com.communityhub.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class DriverFactory {
    public static AndroidDriver createDriver() throws Exception {

        String apkPath = System.getProperty(
                "app.path",
                ConfigReader.get("app.path")
        );

        Path apk = Path.of(apkPath);

        if (!Files.isRegularFile(apk)) {
            throw new IllegalStateException(
                    "APK not found: " + apk
            );
        }

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(ConfigReader.get("device.name"))
                .setApp(apk.toString())
                .setNoReset(Boolean.parseBoolean(ConfigReader.get("no.reset")));

        String udid = System.getProperty("udid");
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
        }

        options.setCapability("appium:autoWebview", false);
        options.setCapability(
                "appium:ensureWebviewsHavePages", true);
        options.setCapability(
                "appium:chromedriverAutodownload",
                true
        );

        String url = ConfigReader.get("appium.url");
        System.out.println("Resolved Appium URL: " + url);
      return new AndroidDriver(
              URI.create(url).toURL(), options);
    }
}
