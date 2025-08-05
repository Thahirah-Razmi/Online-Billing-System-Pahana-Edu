package com.pahanaedu.service;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    //Encapsulation
    private static UserService instance;
    private UserDAO userDAO;

    // Singleton Pattern
    private UserService() {
        userDAO = new UserDAO();
    }

    // Singleton Pattern
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    //Abstraction
    public void addUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    //Abstraction
    public boolean isUsernameOrEmailExists(String username, String email) throws SQLException {
        return userDAO.isUsernameOrEmailExists(username, email);
    }

    //Abstraction
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    //Abstraction
    public void deleteUser(int id) throws SQLException {
        userDAO.deleteUser(id);
    }

    //Abstraction
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    //Abstraction
    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }

    //Abstraction
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

}
