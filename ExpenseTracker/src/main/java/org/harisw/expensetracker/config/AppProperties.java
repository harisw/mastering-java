package org.harisw.expensetracker.config;

import java.io.InputStream;
import java.util.Properties;

public final class AppProperties {
    private static final Properties PROPS = new Properties();

    //Static initialization block
    //Flows
    //  Class loaded
    //→ static fields initialized
    //→ static blocks executed (top to bottom)
    //→ class ready
    //→ constructor runs (when you create an object)

    static {
        try (
                InputStream is = AppProperties.class.getClassLoader()
                        .getResourceAsStream("application.properties")) {
            if (is == null) {
                throw new IllegalStateException("application.properties not found on classpath");
            }

            PROPS.load(is);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private AppProperties() {
    } // prevent instantiation

    public static String required(String key) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing required property: " + key
            );
        }
        return value;
    }

    public static String get(String key) {
        return PROPS.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return PROPS.getProperty(key, defaultValue);
    }
}
