package com.pahanaedu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/online_billing_system_pahana_edu";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static DBConnection instance; // Singleton Instance
    private Connection connection; // Encapsulation

    private DBConnection() {
        createConnection();
    }

    // Singleton Pattern
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    // Abstraction + Encapsulation
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                createConnection(); // Reopen if closed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Abstraction + Encapsulation
    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
