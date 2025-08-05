package com.pahanaedu.service;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;

import java.util.List;

public class BillService {

    //Encapsulation
    private static BillService instance;
    // Service Layer Pattern
    private final BillDAO billDAO;

    private BillService() {
        billDAO = new BillDAO();
    }

     // Singleton Pattern
    public static BillService getInstance() {
        if (instance == null) {
            instance = new BillService();
        }
        return instance;
    }

    //Abstraction
    public boolean addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    public Bill getBillById(int id) {
        return billDAO.getBillById(id);
    }

    public boolean deleteBill(int id) {
        return billDAO.deleteBill(id);
    }

    public List<Bill> getFilteredBills(String account, String invoiceNum, String fromDate, String toDate) {
        return billDAO.getFilteredBills(account, invoiceNum, fromDate, toDate);
    }

}
