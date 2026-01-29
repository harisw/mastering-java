package org.harisw.expensetracker.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv
            .configure()
            .ignoreIfMissing()
            .load();

    public static String get(String key, String defaultValue) {
        // First try actual OS environment vars (From JVM or machine)
        String val = System.getenv(key);

        if (val != null && !val.isEmpty()) {
            return val;
        }

        // Then try the .env loaded values
        val = dotenv.get(key);

        if (val != null && !val.isEmpty()) {
            return val;
        }

        return defaultValue;
    }
}
