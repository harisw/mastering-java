package org.harisw.expensetracker.infrastructure.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.harisw.expensetracker.config.AppProperties;
import org.harisw.expensetracker.config.EnvConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    private static HikariDataSource dataSource;

    public static DataSource getDataSource() throws SQLException {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(EnvConfig.get("DB_URL", AppProperties.required("DB_URL")));
            config.setUsername(EnvConfig.get("DB_USER", AppProperties.required("DB_USER")));
            config.setPassword(EnvConfig.get("DB_PASSWORD", AppProperties.required("DB_PASSWORD")));

            config.setMaximumPoolSize(
                    Integer.parseInt(EnvConfig.get("DB_POOL_MAX", "10"))
            );
            config.setMinimumIdle(
                    Integer.parseInt(EnvConfig.get("DB_POOL_MIN_IDLE", "2"))
            );
            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}