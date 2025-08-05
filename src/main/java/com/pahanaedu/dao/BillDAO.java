package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BillDAO {

    // Encapsulation
    public boolean addBill(Bill bill) {
        
        // Layered Architecture
        String sql = "INSERT INTO billing "
                + "(customer_account_number, customer_name, books_purchased, "
                + " total_amount, generated_by) "
                + "VALUES (?,?,?,?,?)";
        
        try (Connection conn = DBConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) { // Layered Architecture

            // Encapsulation
            ps.setString(1, bill.getAccountNumber());
            ps.setString(2, bill.getCustomerName());
            ps.setString(3, bill.getBooksPurchased());
            ps.setDouble(4, bill.getTotalAmount());
            ps.setString(5, bill.getStaffUsername());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                return false;
            }

            // Workaround: fetch last inserted ID manually
            String latestSql = "SELECT id FROM billing WHERE customer_account_number = ? ORDER BY id DESC LIMIT 1";
            try (PreparedStatement latestPs = conn.prepareStatement(latestSql)) {
                latestPs.setString(1, bill.getAccountNumber());
                try (ResultSet rs = latestPs.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        bill.setId(id); // Encapsulation
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Abstraction
    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM billing ORDER BY bill_date DESC";
        try (Connection conn = DBConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bill b = new Bill();//Abstraction
                b.setId(rs.getInt("id"));
                b.setAccountNumber(rs.getString("customer_account_number"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setBooksPurchased(rs.getString("books_purchased"));
                b.setTotalAmount(rs.getDouble("total_amount"));
                b.setBillingTime(rs.getTimestamp("bill_date"));
                b.setStaffUsername(rs.getString("generated_by"));
                list.add(b);//Encapsulation
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Abstraction
    public Bill getBillById(int id) {
        String sql = "SELECT * FROM billing WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bill b = new Bill(); //Abstraction
                    b.setId(rs.getInt("id"));
                    b.setAccountNumber(rs.getString("customer_account_number"));
                    b.setCustomerName(rs.getString("customer_name"));
                    b.setBooksPurchased(rs.getString("books_purchased"));
                    b.setTotalAmount(rs.getDouble("total_amount"));
                    b.setBillingTime(rs.getTimestamp("billing_date"));
                    b.setStaffUsername(rs.getString("generated_by"));
                    return b; //Abstraction + Encapsulation
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Abstraction
    public boolean deleteBill(int id) {
        String sql = "DELETE FROM billing WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Abstraction
    public List<Bill> getFilteredBills(String account, String invoiceNum, String fromDate, String toDate) {
        List<Bill> list = new ArrayList<>();

        //Abstraction
        StringBuilder sql = new StringBuilder("SELECT * FROM billing WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (account != null && !account.trim().isEmpty()) {
            sql.append(" AND customer_account_number LIKE ?");
            params.add("%" + account.trim() + "%");

        }

        if (invoiceNum != null && !invoiceNum.trim().isEmpty()) {
            sql.append(" AND id LIKE ?");
            params.add("%" + invoiceNum.trim() + "%");

        }
        if (fromDate != null && !fromDate.trim().isEmpty()) {
            sql.append(" AND bill_date >= ?");
            params.add(Date.valueOf(fromDate));
        }

        if (toDate != null && !toDate.trim().isEmpty()) {
            sql.append(" AND bill_date <= ?");
            params.add(Date.valueOf(toDate));
        }

        sql.append(" ORDER BY bill_date DESC");

        try (Connection conn = DBConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            // Abstraction
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill b = new Bill();//Abstraction
                b.setId(rs.getInt("id"));
                b.setAccountNumber(rs.getString("customer_account_number"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setBooksPurchased(rs.getString("books_purchased"));
                b.setTotalAmount(rs.getDouble("total_amount"));
                b.setBillingTime(rs.getTimestamp("bill_date"));
                b.setStaffUsername(rs.getString("generated_by"));
                list.add(b); // Encapsulation
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
