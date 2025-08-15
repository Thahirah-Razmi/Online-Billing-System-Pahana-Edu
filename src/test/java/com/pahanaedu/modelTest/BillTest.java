package com.pahanaedu.modelTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.model.Bill;

public class BillTest {

    private Bill bill;
    private Timestamp now;

    @Before
    public void setUp() throws ParseException {
        bill = new Bill();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        now = new Timestamp(dateFormat.parse("2025-08-12 12:30:00").getTime());
    }

    @Test
    public void testSettersAndGetters() {
        bill.setId(6);
        bill.setAccountNumber("PAH008");
        bill.setCustomerName("Test User");
        bill.setBooksPurchased("Think and Grow Rich(qty-1), වෙරළෙක පැතුම්(qty-1)");
        bill.setTotalAmount(12225.00);
        bill.setBillingTime(now);
        bill.setStaffUsername("staff1");

        assertEquals("ID mismatch", 6, bill.getId());
        assertEquals("Account number mismatch", "PAH008", bill.getAccountNumber());
        assertEquals("Customer name mismatch", "Test User", bill.getCustomerName());
        assertEquals("Books purchased mismatch",
                "Think and Grow Rich(qty-1), වෙරළෙක පැතුම්(qty-1)",
                bill.getBooksPurchased());
        assertEquals("Total amount mismatch", 12225.00, bill.getTotalAmount(), 0.001);
        assertEquals("Billing time mismatch", now, bill.getBillingTime());
        assertEquals("Staff username mismatch", "staff1", bill.getStaffUsername());

        System.out.println("[PASS] Bill getters/setters test passed for customer: " + bill.getCustomerName());
    }
}
