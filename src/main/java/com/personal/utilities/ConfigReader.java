package com.personal.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read configuration properties from config.properties.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Could not load config.properties from " + CONFIG_FILE_PATH + ": " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getBrowser() {
        String browser = getProperty("browser");
        return (browser != null && !browser.trim().isEmpty()) ? browser : "chrome";
    }

    public static boolean isHeadless() {
        String headless = getProperty("headless");
        return Boolean.parseBoolean(headless);
    }

    public static int getImplicitWait() {
        String wait = getProperty("implicit.wait");
        return (wait != null) ? Integer.parseInt(wait) : 10;
    }

    public static int getExplicitWait() {
        String wait = getProperty("explicit.wait");
        return (wait != null) ? Integer.parseInt(wait) : 15;
    }

    public static String getStandardUsername() {
        return getProperty("standard.username");
    }

    public static String getStandardPassword() {
        return getProperty("standard.password");
    }

    public static String getLockedUsername() {
        return getProperty("locked.username");
    }

    public static String getLockedPassword() {
        return getProperty("locked.password");
    }

    public static String getFrozenUsername() {
        return getProperty("frozen.username");
    }

    public static String getFrozenPassword() {
        return getProperty("frozen.password");
    }
}
