package org.harisw.expensetracker.infrastructure.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

//MAYBE DELETE SOON, UNNECESSARY
public class DbConnection {
    private static final Logger log = LoggerFactory.getLogger(DbConnection.class);

    public static Connection get() throws SQLException {
        log.info("Requesting DB Connection");
        return DataSourceFactory.getDataSource().getConnection();
    }
}