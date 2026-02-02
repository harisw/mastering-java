package org.harisw.expensetracker.infrastructure.db;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayMigrator {

    public static void migrate(DataSource dataSource) {
        Flyway.configure()
                .dataSource(dataSource)
                .load()
                .migrate();
    }
}
