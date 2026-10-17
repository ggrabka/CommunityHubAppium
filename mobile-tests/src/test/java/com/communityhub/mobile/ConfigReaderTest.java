package com.communityhub.mobile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigReaderTest {
    @Test
    void shouldThrowExceptionWhenPropertyMissing() {

        assertThrows(
                IllegalStateException.class,
                () -> ConfigReader.get(
                        "communityhub.nonexistent.property"
                )
        );
    }

    @Test
    void shouldPreferSystemPropertyOverConfigFile() {
        String originalUrl = System.getProperty("appium.url");

        try {
            System.setProperty(
                    "appium.url",
                    "http://localhost:9999"
            );

            assertEquals(
                    "http://localhost:9999",
                    ConfigReader.get("appium.url")
            );
        } finally {
            if (originalUrl == null) {
                System.clearProperty("appium.url");
            } else {
                System.setProperty("appium.url", originalUrl);
            }
        }
    }
}
