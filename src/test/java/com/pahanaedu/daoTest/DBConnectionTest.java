package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.pahanaedu.dao.DBConnection;

public class DBConnectionTest {

    @Test
    public void testGetInstanceNotNull() {
        DBConnection instance = DBConnection.getInstance();
        assertNotNull("DBConnection instance should not be null", instance);
        System.out.println("DBConnection instance obtained: " + instance);
    }

    @Test
    public void testSingletonBehavior() {
        DBConnection instance1 = DBConnection.getInstance();
        DBConnection instance2 = DBConnection.getInstance();
        assertSame("Both instances should be the same (singleton)", instance1, instance2);
        System.out.println("Singleton verified: instance1 == instance2");
    }

    @Test
    public void testGetConnectionNotNull() {
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        assertNotNull("Connection should not be null", connection);
        System.out.println("Connection obtained: " + connection);
    }

    @Test
    public void testConnectionIsOpen() throws SQLException {
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        assertFalse("Connection should not be closed", connection.isClosed());
        System.out.println("Connection is open: " + !connection.isClosed());
    }

    @Test
    public void testReconnectIfClosed() throws SQLException {
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        connection.close();  // Close the connection manually
        System.out.println("Closed original connection: " + connection);

        Connection newConnection = instance.getConnection();  // Should reopen
        assertNotNull("New connection after close should not be null", newConnection);
        assertFalse("New connection should be open", newConnection.isClosed());
        System.out.println("Reopened connection: " + newConnection + ", open: " + !newConnection.isClosed());
    }

    @Test
    public void testExecuteSimpleQuery() throws SQLException {
        DBConnection instance = DBConnection.getInstance();
        Connection connection = instance.getConnection();
        try (Statement stmt = connection.createStatement()) {
            boolean result = stmt.execute("SELECT 1");
            assertTrue("Query should run without returning a result set", result);
            System.out.println("Executed simple query 'SELECT 1', result: " + result);
        }
    }
}
