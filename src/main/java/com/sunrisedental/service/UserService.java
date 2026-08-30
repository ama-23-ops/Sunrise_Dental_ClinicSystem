/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.dao.UserDAO;
import com.sunrisedental.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDAO userDAO =
            new UserDAO();

    public void create(User user)
            throws SQLException {

        validateUser(user);

        User existingUser =
                userDAO.findByUsername(
                        user.getUsername()
                );

        if (existingUser != null) {

            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        userDAO.add(user);
    }

    public void update(User user,
                       boolean changePassword)
            throws SQLException {

        validateUser(user);

        User existingUser =
                userDAO.findByUsername(
                        user.getUsername()
                );

        if (existingUser != null
                && existingUser.getUserId()
                != user.getUserId()) {

            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        if (changePassword) {

            userDAO.updateWithPassword(user);

        } else {

            userDAO.updateWithoutPassword(user);
        }
    }

    public User findByUsername(
            String username)
            throws SQLException {

        return userDAO.findByUsername(username);
    }

    public List<User> findAll()
            throws SQLException {

        return userDAO.findAll();
    }

    public void delete(int userId)
            throws SQLException {

        userDAO.delete(userId);
    }

    private void validateUser(User user) {

        if (user.getUsername() == null
                || user.getUsername()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (user.getFullName() == null
                || user.getFullName()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Full name is required."
            );
        }

        if (user.getRole() == null
                || user.getRole()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Please select a role."
            );
        }
    }
}
