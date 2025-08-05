package com.pahanaedu.dao;

import java.sql.Connection;

 // Abstraction + Factory Design Pattern + Layered Architecture

public class DBConnectionFactory {

    public static Connection getConnection() {
        return DBConnection.getInstance().getConnection(); // Singleton Instance
    }
}
