package com.pahanaedu.serviceTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BillServiceTest {

    private static BillService billService = BillService.getInstance();
    private static int addedBillId;
    private static final String TEST_ACCOUNT = "ACC_TEST_BILL";

    @Test
    public void test1_AddBill() {
        Bill bill = new Bill();
        bill.setAccountNumber(TEST_ACCOUNT);
        bill.setCustomerName("Test Customer");
        bill.setBooksPurchased("Book A, Book B");
        bill.setTotalAmount(123.45);
        bill.setBillingTime(new Timestamp(System.currentTimeMillis()));
        bill.setStaffUsername("staff_test");

        boolean added = billService.addBill(bill);
        assertTrue("Bill should be added", added);
        System.out.println("Bill added successfully");

        List<Bill> bills = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
        assertFalse("Bill should be found after add", bills.isEmpty());

        addedBillId = bills.get(0).getId();
        System.out.println("Added Bill ID: " + addedBillId);
    }

    @Test
    public void test2_GetBillById() {
        // Ensure addedBillId is initialized
        if (addedBillId == 0) {
            List<Bill> bills = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
            assertFalse("No bills found for TEST_ACCOUNT. Run test1_AddBill first or add a setup bill.", bills.isEmpty());
            addedBillId = bills.get(0).getId();
            System.out.println("Retrieved Added Bill ID: " + addedBillId);
        }

        Bill bill = billService.getBillById(addedBillId);
        assertNotNull("Bill should be retrieved by ID", bill);
        assertEquals(TEST_ACCOUNT, bill.getAccountNumber());
        System.out.println("Bill retrieved by ID successfully: " + bill.getId());
    }

    @Test
    public void test3_GetAllBills() {
        if (addedBillId == 0) {
            List<Bill> bills = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
            assertFalse("No bills found for TEST_ACCOUNT. Run test1_AddBill first or add a setup bill.", bills.isEmpty());
            addedBillId = bills.get(0).getId();
        }

        List<Bill> allBills = billService.getAllBills();
        assertNotNull("All bills list should not be null", allBills);
        boolean found = allBills.stream().anyMatch(b -> b.getId() == addedBillId);
        assertTrue("All bills should contain the added bill", found);
        System.out.println("All bills retrieved. Added bill found: " + addedBillId);
    }

    @Test
    public void test4_GetFilteredBills() {
        if (addedBillId == 0) {
            List<Bill> bills = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
            assertFalse("No bills found for TEST_ACCOUNT. Run test1_AddBill first or add a setup bill.", bills.isEmpty());
            addedBillId = bills.get(0).getId();
        }

        List<Bill> filtered = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
        assertFalse("Filtered bills should contain the added bill", filtered.isEmpty());
        assertEquals(TEST_ACCOUNT, filtered.get(0).getAccountNumber());
        System.out.println("Filtered bills retrieved. First bill account: " + filtered.get(0).getAccountNumber());
    }

    @Test
    public void test5_DeleteBill() {
        if (addedBillId == 0) {
            List<Bill> bills = billService.getFilteredBills(TEST_ACCOUNT, "", null, null);
            if (bills.isEmpty()) {
                System.out.println("No bill to delete. Skipping delete test.");
                return;
            }
            addedBillId = bills.get(0).getId();
        }

        boolean deleted = billService.deleteBill(addedBillId);
        assertTrue("Bill should be deleted", deleted);
        System.out.println("Bill deleted successfully: " + addedBillId);

        Bill bill = billService.getBillById(addedBillId);
        assertNull("Bill should not exist after deletion", bill);
        System.out.println("Confirmed bill deletion: " + addedBillId);

        // Reset addedBillId to avoid reuse
        addedBillId = 0;
    }
}
