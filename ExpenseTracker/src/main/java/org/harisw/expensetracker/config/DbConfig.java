package org.harisw.expensetracker.config;

public final class DbConfig {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final Integer maxPool;
    private final Integer minPool;

    public DbConfig(String dbUrl, String dbUser, String dbPassword, Integer maxPool, Integer minPool) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.maxPool = maxPool;
        this.minPool = minPool;
    }

    public DbConfig() {
        this.dbUrl = EnvConfig.get("DB_URL", AppProperties.required("DB_URL"));
        this.dbUser = EnvConfig.get("DB_USER", AppProperties.required("DB_USER"));
        this.dbPassword = EnvConfig.get("DB_PASSWORD", AppProperties.required("DB_PASSWORD"));
        this.maxPool = Integer.parseInt(EnvConfig.get("DB_POOL_MAX", "10"));
        this.minPool = Integer.parseInt(EnvConfig.get("DB_POOL_MIN_IDLE", "2"));
    }

    public String jdbcUrl() {
        return dbUrl;
    }

    public String user() {
        return dbUser;
    }

    public String password() {
        return dbPassword;
    }

    public Integer maxPoolSize() {
        return maxPool;
    }

    public Integer minPoolIdle() {
        return minPool;
    }
}
