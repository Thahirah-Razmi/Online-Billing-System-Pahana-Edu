package com.pahanaedu.service;

import java.sql.SQLException;
import java.util.List;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;

public class CustomerService {

    //Encapsulation
    private static CustomerService instance;
    private CustomerDAO customerDAO = new CustomerDAO();

    // Singleton Pattern
    private CustomerService() {
        customerDAO = new CustomerDAO();
    }

    // Singleton Pattern
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public boolean isEmailExists(String email) {
        return customerDAO.isEmailExists(email);
    }

    public boolean isTelephoneExists(String telephone) {
        return customerDAO.isTelephoneExists(telephone);
    }

    //Abstraction
    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    //Abstraction
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    //Abstraction
    public List<Customer> searchCustomer(String keyword) {
        return customerDAO.searchCustomer(keyword);
    }

    //Abstraction
    public Customer getCustomerById(int id) throws SQLException {
        return customerDAO.getCustomerById(id);
    }

    //Abstraction
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    //Abstraction
    public void deleteCustomer(int id) throws SQLException {
        customerDAO.deleteCustomer(id);

    }

    //Abstraction
    public Customer getCustomerByAccount(String accountNumber) {
        return customerDAO.getCustomerByAccount(accountNumber);
    }
}
