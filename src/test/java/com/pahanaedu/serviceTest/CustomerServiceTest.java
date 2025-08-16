package com.pahanaedu.serviceTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest {

    private static CustomerService customerService = CustomerService.getInstance();
    private static int testCustomerId;
    private static final String TEST_ACCOUNT = "ACC_TEST_CUST";
    private static final String TEST_EMAIL = "testcust@example.com";
    private static final String TEST_PHONE = "0771234567";

    @Test
    public void test1_AddCustomer() {
        Customer customer = new Customer();
        customer.setAccountNumber(TEST_ACCOUNT);
        customer.setName("Test Customer");
        customer.setEmail(TEST_EMAIL);
        customer.setTelephone(TEST_PHONE);
        customer.setAddress("123 Test Street");

        boolean added = customerService.addCustomer(customer);
        assertTrue("Customer should be added", added);
        System.out.println("Customer added successfully");

        Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
        assertNotNull("Customer should be retrievable by account", retrieved);
        testCustomerId = retrieved.getId();
        System.out.println("Added Customer ID: " + testCustomerId);
    }

    @Test
    public void test2_GetCustomerById() throws SQLException {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found. Run test1_AddCustomer first or add a setup customer.", retrieved);
            testCustomerId = retrieved.getId();
            System.out.println("Retrieved Customer ID: " + testCustomerId);
        }

        Customer customer = customerService.getCustomerById(testCustomerId);
        assertNotNull("Customer should exist by ID", customer);
        assertEquals(TEST_ACCOUNT, customer.getAccountNumber());
        System.out.println("Customer retrieved by ID successfully: " + testCustomerId);
    }

    @Test
    public void test3_GetAllCustomers() throws SQLException {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found.", retrieved);
            testCustomerId = retrieved.getId();
        }

        List<Customer> customers = customerService.getAllCustomers();
        assertNotNull("All customers list should not be null", customers);
        boolean found = customers.stream().anyMatch(c -> c.getId() == testCustomerId);
        assertTrue("All customers should contain the test customer", found);
        System.out.println("All customers retrieved. Test customer found: " + testCustomerId);
    }

    @Test
    public void test4_SearchCustomer() {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found.", retrieved);
            testCustomerId = retrieved.getId();
        }

        List<Customer> customers = customerService.searchCustomer("Test");
        assertFalse("Search should find the customer", customers.isEmpty());
        boolean found = customers.stream().anyMatch(c -> c.getId() == testCustomerId);
        assertTrue("Search results should contain the test customer", found);
        System.out.println("Search completed. Test customer found: " + testCustomerId);
    }

    @Test
    public void test5_UpdateCustomer() {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found.", retrieved);
            testCustomerId = retrieved.getId();
        }

        Customer customer = new Customer();
        customer.setId(testCustomerId);
        customer.setAccountNumber(TEST_ACCOUNT);
        customer.setName("Updated Customer");
        customer.setEmail(TEST_EMAIL);
        customer.setTelephone(TEST_PHONE);
        customer.setAddress("456 Updated Street");

        boolean updated = customerService.updateCustomer(customer);
        assertTrue("Customer should be updated", updated);

        Customer updatedCustomer = customerService.getCustomerByAccount(TEST_ACCOUNT);
        assertEquals("Updated Customer", updatedCustomer.getName());
        assertEquals("456 Updated Street", updatedCustomer.getAddress());
        System.out.println("Customer updated successfully: " + testCustomerId);
    }

    @Test
    public void test6_IsEmailAndPhoneExists() {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found.", retrieved);
            testCustomerId = retrieved.getId();
        }

        assertTrue("Email should exist", customerService.isEmailExists(TEST_EMAIL));
        assertTrue("Telephone should exist", customerService.isTelephoneExists(TEST_PHONE));
        assertFalse("Nonexistent email should not exist", customerService.isEmailExists("nonexist@example.com"));
        assertFalse("Nonexistent phone should not exist", customerService.isTelephoneExists("0710000000"));

        System.out.println("Email and phone existence check completed for test customer: " + testCustomerId);
    }

    @Test
    public void test7_GetCustomerByAccount() {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            assertNotNull("No test customer found.", retrieved);
            testCustomerId = retrieved.getId();
        }

        Customer customer = customerService.getCustomerByAccount(TEST_ACCOUNT);
        assertNotNull("Customer should be retrieved by account", customer);
        assertEquals(testCustomerId, customer.getId());
        System.out.println("Customer retrieved by account successfully: " + testCustomerId);
    }

    @Test
    public void test8_DeleteCustomer() throws SQLException {
        if (testCustomerId == 0) {
            Customer retrieved = customerService.getCustomerByAccount(TEST_ACCOUNT);
            if (retrieved == null) {
                System.out.println("No customer to delete. Skipping delete test.");
                return;
            }
            testCustomerId = retrieved.getId();
        }

        customerService.deleteCustomer(testCustomerId);
        Customer customer = customerService.getCustomerById(testCustomerId);
        assertNull("Customer should be deleted", customer);
        System.out.println("Customer deleted successfully: " + testCustomerId);

        testCustomerId = 0; // reset
    }
}
