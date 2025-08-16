package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;

public class BillDAOTest {

    private static BillDAO billDAO;
    private static int setupBillId;
    private static final String SETUP_ACCOUNT = "PAH001";

    @BeforeClass
    public static void setUpClass() {
        billDAO = new BillDAO();

        // Check if a setup bill for the same account already exists
        List<Bill> bills = billDAO.getFilteredBills(SETUP_ACCOUNT, null, null, null);
        if (bills.isEmpty()) {
            // Insert setup bill only if it doesn't exist
            Bill setupBill = new Bill();
            setupBill.setAccountNumber(SETUP_ACCOUNT);
            setupBill.setCustomerName("Ayesha Fernando");
            setupBill.setBooksPurchased("Clean Code(qty-1)");
            setupBill.setTotalAmount(13800.00);
            setupBill.setStaffUsername("Admin");

            boolean added = billDAO.addBill(setupBill);
            assertTrue("Setup bill should be added", added);

            // Fetch its ID
            bills = billDAO.getFilteredBills(SETUP_ACCOUNT, null, null, null);
        }

        setupBillId = bills.get(0).getId();
        System.out.println("Setup bill ID used: " + setupBillId);
    }

    //@AfterClass
    //public static void tearDownClass() {
        // Clean up the setup bill
    //    if (setupBillId > 0) {
    //        boolean deleted = billDAO.deleteBill(setupBillId);
    //        System.out.println("Deleted setup bill ID " + setupBillId + ": " + deleted);
    //    }
    //}

    @Test
    public void testAddBill() {
        Bill newBill = new Bill();
        newBill.setAccountNumber("PAH007");
        newBill.setCustomerName("Rashmi Silva");
        newBill.setBooksPurchased("Effective Java(qty-2)");
        newBill.setTotalAmount(25000.00);
        newBill.setStaffUsername("Admin");

        boolean added = billDAO.addBill(newBill);
        assertTrue("New bill should be added successfully", added);

        // Verify the bill was persisted
        List<Bill> retrieved = billDAO.getFilteredBills("PAH007", null, null, null);
        assertFalse("Retrieved bills should not be empty", retrieved.isEmpty());

        Bill fetched = retrieved.get(0);
        assertEquals("Rashmi Silva", fetched.getCustomerName());
        assertEquals("PAH007", fetched.getAccountNumber());
        System.out.println("Added bill verified: " + fetched.getId() + ", " + fetched.getCustomerName());

        // Cleanup after test
        //boolean deleted = billDAO.deleteBill(fetched.getId());
        //assertTrue("Cleanup: test bill should be deleted", deleted);
    }

    @Test
    public void testGetBillById() {
        Bill bill = billDAO.getBillById(setupBillId);
        assertNotNull("Bill should not be null", bill);
        assertEquals(SETUP_ACCOUNT, bill.getAccountNumber());
        System.out.println("GetBillById retrieved: " + bill.getId() + ", " + bill.getCustomerName());
    }

    @Test
    public void testGetAllBillsContainsSetupBill() {
        List<Bill> bills = billDAO.getAllBills();
        boolean found = bills.stream().anyMatch(b -> b.getId() == setupBillId);
        assertTrue("All bills should contain the setup bill", found);
        System.out.println("Verified setup bill exists in all bills.");
    }

    @Test
    public void testGetFilteredBills() {
        List<Bill> filtered = billDAO.getFilteredBills(SETUP_ACCOUNT, null, null, null);
        assertFalse("Filtered bills should not be empty", filtered.isEmpty());
        assertEquals(SETUP_ACCOUNT, filtered.get(0).getAccountNumber());
        System.out.println("Filtered bills retrieved: " + filtered.size() + " bill(s)");
    }

    @Test
    public void testDeleteBill() {
        // First add a temporary bill to delete
        Bill tempBill = new Bill();
        tempBill.setAccountNumber("PAH002");
        tempBill.setCustomerName("Nimal Perera");
        tempBill.setBooksPurchased("Java Concurrency(qty-1)");
        tempBill.setTotalAmount(15000.00);
        tempBill.setStaffUsername("Admin");

        boolean added = billDAO.addBill(tempBill);
        assertTrue("Temporary bill should be added", added);

        List<Bill> retrieved = billDAO.getFilteredBills("PAH002", null, null, null);
        int tempBillId = retrieved.get(0).getId();

        // Delete the temporary bill
        boolean deleted = billDAO.deleteBill(tempBillId);
        assertTrue("Temporary bill should be deleted", deleted);

        Bill fetched = billDAO.getBillById(tempBillId);
        assertNull("Bill should be null after deletion", fetched);
        System.out.println("Temporary bill deleted successfully, ID: " + tempBillId);
    }
}
