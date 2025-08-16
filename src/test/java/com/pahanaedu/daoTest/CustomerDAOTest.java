package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.*;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;

public class CustomerDAOTest {

    private static CustomerDAO customerDAO;
    private static Customer testCustomer;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        customerDAO = new CustomerDAO();

        // Generate the test account number once (consistent for re-runs)
        String testAccountNumber = "PAH007"; // fixed value for testing

        // Try fetching existing test customer
        Customer existing = customerDAO.getCustomerByAccount(testAccountNumber);

        if (existing == null) {
            // Create new test customer if not already in DB
            testCustomer = new Customer();
            testCustomer.setAccountNumber(testAccountNumber);
            testCustomer.setName("John Doe");
            testCustomer.setAddress("123 Main St, Kandy");
            testCustomer.setTelephone("0779557057");
            testCustomer.setEmail("johndoe@gmail.com");

            boolean added = customerDAO.addCustomer(testCustomer);
            assertTrue("Test customer should be added", added);

            // Fetch ID
            existing = customerDAO.getCustomerByAccount(testAccountNumber);
        }

        testCustomer = existing; // reuse existing if already there
        System.out.println("Using test customer for all tests: ID = " + testCustomer.getId());
    }

    //@AfterClass
    //public static void tearDownClass() throws SQLException {
    //    if (testCustomer != null && testCustomer.getId() > 0) {
    //        customerDAO.deleteCustomer(testCustomer.getId());
    //        System.out.println("Deleted test customer after all tests: ID = " + testCustomer.getId());
    //    }
    //}

    @Test
    public void testAddCustomer() throws SQLException {
        Customer newCustomer = new Customer();
        newCustomer.setAccountNumber(customerDAO.generateNextAccountNumber());
        newCustomer.setName("Test User");
        newCustomer.setAddress("99 Test Road, Colombo");
        newCustomer.setTelephone("0711111111");
        newCustomer.setEmail("testuser@example.com");

        boolean added = customerDAO.addCustomer(newCustomer);
        assertTrue("Customer should be added successfully", added);

        Customer fetched = customerDAO.getCustomerByAccount(newCustomer.getAccountNumber());
        assertNotNull("Fetched customer should not be null", fetched);
        assertEquals("Name should match", "Test User", fetched.getName());

        System.out.println("Added customer successfully: " + fetched.getAccountNumber());

        // Clean up
        //customerDAO.deleteCustomer(fetched.getId());
    }

    @Test
    public void testGenerateNextAccountNumber() {
        String nextAccount = customerDAO.generateNextAccountNumber();
        assertNotNull("Next account number should not be null", nextAccount);
        assertTrue("Next account number should start with 'PAH'", nextAccount.startsWith("PAH"));
        assertTrue("Next account number should have numeric suffix", nextAccount.length() == 6);

        System.out.println("Generated next account number: " + nextAccount);
    }

    @Test
    public void testGetCustomerById() throws SQLException {
        Customer fetched = customerDAO.getCustomerById(testCustomer.getId());
        assertNotNull("Customer should not be null", fetched);
        assertEquals(testCustomer.getName(), fetched.getName());
        System.out.println("Fetched customer by ID: " + fetched.getId() + ", Name: " + fetched.getName());
    }

    @Test
    public void testGetCustomerByAccount() throws SQLException {
        Customer fetched = customerDAO.getCustomerByAccount(testCustomer.getAccountNumber());
        assertNotNull(fetched);
        assertEquals(testCustomer.getName(), fetched.getName());
        System.out.println("Fetched customer by account: " + fetched.getAccountNumber());
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
        testCustomer.setName("Jane Smith");
        testCustomer.setAddress("456 Second St, Colombo");
        testCustomer.setTelephone("0762590668");
        testCustomer.setEmail("janesmith@email.com");

        boolean updated = customerDAO.updateCustomer(testCustomer);
        assertTrue(updated);

        Customer updatedCustomer = customerDAO.getCustomerById(testCustomer.getId());
        assertEquals("Jane Smith", updatedCustomer.getName());
        System.out.println("Updated customer: " + updatedCustomer.getName() + ", Email: " + updatedCustomer.getEmail());
    }

    @Test
    public void testIsEmailExists() {
        assertTrue(customerDAO.isEmailExists(testCustomer.getEmail()));
        assertFalse(customerDAO.isEmailExists("nonexistent@example.com"));
        System.out.println("Email existence check passed");
    }

    @Test
    public void testIsTelephoneExists() {
        assertTrue(customerDAO.isTelephoneExists(testCustomer.getTelephone()));
        assertFalse(customerDAO.isTelephoneExists("0000000000"));
        System.out.println("Telephone existence check passed");
    }

    @Test
    public void testSearchCustomer() {
        List<Customer> results = customerDAO.searchCustomer("Jane");
        assertNotNull(results);
        assertTrue(results.stream().anyMatch(c -> c.getId() == testCustomer.getId()));
        System.out.println("Search returned customer(s) matching keyword 'Jane': " + results.size());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        assertNotNull(allCustomers);
        assertTrue(allCustomers.stream().anyMatch(c -> c.getId() == testCustomer.getId()));
        System.out.println("Verified test customer exists in all customers list");
    }

    @Test
    public void testDeleteCustomerManually() throws SQLException {
        Customer temp = new Customer();
        temp.setAccountNumber(customerDAO.generateNextAccountNumber());
        temp.setName("Shehan Silva");
        temp.setAddress("12 Temple Road, Gampaha");
        temp.setTelephone("0712345678");
        temp.setEmail("shehan.silva@example.com");

        customerDAO.addCustomer(temp);
        Customer fetched = customerDAO.getCustomerByAccount(temp.getAccountNumber());
        assertNotNull(fetched);

        customerDAO.deleteCustomer(fetched.getId());
        Customer deleted = customerDAO.getCustomerById(fetched.getId());
        assertNull(deleted);
        System.out.println("Manually added temp customer deleted successfully: ID = " + fetched.getId());
    }
}
