package com.sunrisedental.dao;
import com.sunrisedental.model.User; 
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public int create(User u) throws SQLException {
        String sql="INSERT INTO users(username,password,full_name,role) VALUES(?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); 
                PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,u.getUsername()); 
            ps.setString(2,u.getPassword()); 
            ps.setString(3,u.getFullName()); 
            ps.setString(4,u.getRole());
            ps.executeUpdate(); 
            try(ResultSet k=ps.getGeneratedKeys()){ if(k.next()){u.setUserId(k.getInt(1)); 
            return u.getUserId();
            }
            }
        } return 0;
    }
    private User map(ResultSet rs)throws SQLException{ User u=new User(); 
        u.setUserId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setFullName(rs.getString("full_name"));
        u.setRole(rs.getString("role"));
        return u; 
    }
    
    public void add(User user) throws SQLException {

    String sql = """
        INSERT INTO users
        (username, password, full_name, role)
        VALUES (?, ?, ?, ?)
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(
                1,
                user.getUsername()
        );

        statement.setString(
                2,
                user.getPassword()
        );

        statement.setString(
                3,
                user.getFullName()
        );

        statement.setString(
                4,
                user.getRole()
        );

        statement.executeUpdate();
    }
}
    
    public User findByUsername(String username)
        throws SQLException {

    String sql = """
        SELECT *
        FROM users
        WHERE username = ?
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(1, username);

        try (ResultSet resultSet =
                statement.executeQuery()) {

            if (resultSet.next()) {

                return mapUser(resultSet);
            }
        }
    }

    return null;
}
    
    public List<User> findAll()
        throws SQLException {

    List<User> users = new ArrayList<>();

    String sql = """
        SELECT *
        FROM users
        ORDER BY user_id DESC
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql);

         ResultSet resultSet =
            statement.executeQuery()) {

        while (resultSet.next()) {

            users.add(
                    mapUser(resultSet)
            );
        }
    }

    return users;
}
    
    public void updateWithoutPassword(User user)
        throws SQLException {

    String sql = """
        UPDATE users
        SET username = ?,
            full_name = ?,
            role = ?
        WHERE user_id = ?
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(
                1,
                user.getUsername()
        );

        statement.setString(
                2,
                user.getFullName()
        );

        statement.setString(
                3,
                user.getRole()
        );

        statement.setInt(
                4,
                user.getUserId()
        );

        statement.executeUpdate();
    }
}
    
    public void updateWithPassword(User user)
        throws SQLException {

    String sql = """
        UPDATE users
        SET username = ?,
            password = ?,
            full_name = ?,
            role = ?
        WHERE user_id = ?
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(
                1,
                user.getUsername()
        );

        statement.setString(
                2,
                user.getPassword()
        );

        statement.setString(
                3,
                user.getFullName()
        );

        statement.setString(
                4,
                user.getRole()
        );

        statement.setInt(
                5,
                user.getUserId()
        );

        statement.executeUpdate();
    }
}
    
    public void delete(int userId)
        throws SQLException {

    String sql = """
        DELETE FROM users
        WHERE user_id = ?
        """;

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setInt(1, userId);

        statement.executeUpdate();
    }
}
    
    //HELPER METHOD
    private User mapUser(ResultSet resultSet)
        throws SQLException {

    User user = new User();

    user.setUserId(
            resultSet.getInt("user_id")
    );

    user.setUsername(
            resultSet.getString("username")
    );

    user.setPassword(
            resultSet.getString("password")
    );

    user.setFullName(
            resultSet.getString("full_name")
    );

    user.setRole(
            resultSet.getString("role")
    );

    return user;
}
}
