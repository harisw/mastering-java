package org.harisw.expensetracker.infrastructure.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    public static Connection get() throws SQLException {
        return DataSourceFactory.getDataSource().getConnection();
    }
}