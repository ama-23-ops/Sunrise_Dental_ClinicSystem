package com.sunrisedental.dao;
import com.sunrisedental.model.User; import com.sunrisedental.util.DBConnection;
import java.sql.*;
public class UserDAO {
    public User findByUsername(String username) throws SQLException {
        String sql="SELECT * FROM users WHERE username=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,username); try(ResultSet rs=ps.executeQuery()){ return rs.next()?map(rs):null; }
        }
    }
    public int create(User u) throws SQLException {
        String sql="INSERT INTO users(username,password,full_name,role) VALUES(?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,u.getUsername()); ps.setString(2,u.getPassword()); ps.setString(3,u.getFullName()); ps.setString(4,u.getRole());
            ps.executeUpdate(); try(ResultSet k=ps.getGeneratedKeys()){ if(k.next()){u.setUserId(k.getInt(1)); return u.getUserId();} }
        } return 0;
    }
    private User map(ResultSet rs)throws SQLException{ User u=new User(); u.setUserId(rs.getInt("user_id"));u.setUsername(rs.getString("username"));u.setPassword(rs.getString("password"));u.setFullName(rs.getString("full_name"));u.setRole(rs.getString("role"));return u; }
}
