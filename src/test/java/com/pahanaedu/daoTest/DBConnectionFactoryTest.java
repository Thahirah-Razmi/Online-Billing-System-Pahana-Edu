package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.pahanaedu.dao.DBConnectionFactory;

public class DBConnectionFactoryTest {

    @Test
    public void testGetConnectionNotNull() {
        Connection connection = DBConnectionFactory.getConnection();
        assertNotNull("Connection should not be null", connection);
        System.out.println("Connection obtained successfully: " + connection);
    }

    @Test
    public void testConnectionIsOpen() throws SQLException {
        Connection connection = DBConnectionFactory.getConnection();
        assertFalse("Connection should not be closed", connection.isClosed());
        System.out.println("Connection is open: " + !connection.isClosed());
    }

    @Test
    public void testConnectionIsValid() throws SQLException {
        Connection connection = DBConnectionFactory.getConnection();
        assertTrue("Connection should be valid", connection.isValid(2)); // 2 seconds timeout
        System.out.println("Connection is valid: " + connection.isValid(2));
    }

    @Test
    public void testMultipleCallsReturnValidConnections() throws SQLException {
        Connection conn1 = DBConnectionFactory.getConnection();
        Connection conn2 = DBConnectionFactory.getConnection();

        assertNotNull("First connection should not be null", conn1);
        assertNotNull("Second connection should not be null", conn2);

        assertFalse("First connection should not be closed", conn1.isClosed());
        assertFalse("Second connection should not be closed", conn2.isClosed());

        System.out.println("First connection: " + conn1 + " (open: " + !conn1.isClosed() + ")");
        System.out.println("Second connection: " + conn2 + " (open: " + !conn2.isClosed() + ")");
    }
}
