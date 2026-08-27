package com.sunrisedental.util;
import java.sql.*;

public final class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sunrise_dental_db?useSSL=false&serverTimezone=Asia/Colombo";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    private DBConnection(){}
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
