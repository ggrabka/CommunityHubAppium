package com.communityhub.mobile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        loadConfig();
    }

    private static void loadConfig() {

        try (InputStream configFile = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")){
            if (configFile == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            properties.load(configFile);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load properties", ex);
        }
    }

    public static String get(String key) {
       String override = System.getProperty(key);
       if (override != null && !override.isBlank()) {
           return override;
       }

        System.out.println(
                "System property appium.url: " +
                        System.getProperty("appium.url")
        );

        System.out.println(
                "Resolved Appium URL: " +
                        ConfigReader.get("appium.url")
        );

        String value = properties.getProperty(key);

        if (value == null ||value.isBlank()) {
            throw new IllegalStateException(
                    "Missing required configuration: " + key
            );
        }
        return value;
    }
}
