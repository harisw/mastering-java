package org.harisw.expensetracker.infrastructure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = 
    "jdbc:postgresql://localhost:5432/expense_db";
    private static final String USER = "expense";
    private static final String PASSWORD = "expense";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}