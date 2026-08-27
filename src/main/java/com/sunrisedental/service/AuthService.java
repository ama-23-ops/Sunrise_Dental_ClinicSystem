package com.sunrisedental.service;
import com.sunrisedental.dao.UserDAO;
import com.sunrisedental.model.User;
import com.sunrisedental.util.PasswordUtil;
import com.sunrisedental.util.SessionManager;
import java.sql.SQLException;

public class AuthService {
    private final UserDAO userDAO=new UserDAO();
    public User login(String username,String password)throws SQLException{
        if(username==null||username.isBlank()||password==null||password.isBlank()) throw new IllegalArgumentException("Username and password are required.");
        User u=userDAO.findByUsername(username.trim());
        if(u==null || !PasswordUtil.matches(password,u.getPassword())) return null;
        SessionManager.login(u);
        return u;
    }
}
