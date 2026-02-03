package org.harisw.expensetracker.infrastructure.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.harisw.expensetracker.config.DbConfig;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceFactory {

    public static DataSource create(DbConfig config) throws SQLException {
        HikariConfig hikari = new HikariConfig();

        hikari.setJdbcUrl(config.jdbcUrl());
        hikari.setUsername(config.user());
        hikari.setPassword(config.password());

        hikari.setMaximumPoolSize(config.maxPoolSize());
        hikari.setMinimumIdle(config.minPoolIdle());
        return new HikariDataSource(hikari);
    }

}