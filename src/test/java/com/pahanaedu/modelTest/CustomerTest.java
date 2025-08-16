package com.pahanaedu.modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.model.Customer;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testSettersAndGetters() {
        customer.setId(9);
        customer.setAccountNumber("PAH009");
        customer.setName("Alex Johnson");
        customer.setAddress("77 Maple Street, Colombo");
        customer.setTelephone("0759876543");
        customer.setEmail("alex.johnson@example.com");
        customer.setCreatedAt("2025-08-14 16:00:00");

        assertEquals("ID mismatch", 9, customer.getId());
        assertEquals("Account number mismatch", "PAH009", customer.getAccountNumber());
        assertEquals("Name mismatch", "Alex Johnson", customer.getName());
        assertEquals("Address mismatch", "77 Maple Street, Colombo", customer.getAddress());
        assertEquals("Telephone mismatch", "0759876543", customer.getTelephone());
        assertEquals("Email mismatch", "alex.johnson@example.com", customer.getEmail());
        assertEquals("CreatedAt mismatch", "2025-08-14 16:00:00", customer.getCreatedAt());

        System.out.println("Customer getters/setters test passed: " + customer.getName());
    }

    @Test
    public void testParameterizedConstructor() {
        Customer cust2 = new Customer(
                10,
                "PAH010",
                "Michael Lee",
                "88 Oak Avenue, Kandy",
                "0776543210",
                "michael.lee@example.com",
                "2025-08-14 16:15:00"
        );

        assertEquals("ID mismatch", 10, cust2.getId());
        assertEquals("Account number mismatch", "PAH010", cust2.getAccountNumber());
        assertEquals("Name mismatch", "Michael Lee", cust2.getName());
        assertEquals("Address mismatch", "88 Oak Avenue, Kandy", cust2.getAddress());
        assertEquals("Telephone mismatch", "0776543210", cust2.getTelephone());
        assertEquals("Email mismatch", "michael.lee@example.com", cust2.getEmail());
        assertEquals("CreatedAt mismatch", "2025-08-14 16:15:00", cust2.getCreatedAt());

        System.out.println("[PASS] Customer parameterized constructor test passed for name: " + cust2.getName());
    }
}
