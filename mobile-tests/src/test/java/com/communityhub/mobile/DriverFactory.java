package com.communityhub.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class DriverFactory {
    public static AndroidDriver createDriver() throws Exception {

        Path apk = Path.of(ConfigReader.get("app.path"));
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

        String url = ConfigReader.get("appium.url");
      return new AndroidDriver(
              URI.create(url).toURL(), options);
    }
}
