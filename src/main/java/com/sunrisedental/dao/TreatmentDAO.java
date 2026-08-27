package com.sunrisedental.dao;
import com.sunrisedental.model.Treatment; 
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.util.*;

public class TreatmentDAO {
    public List<Treatment> findActive()throws SQLException{List<Treatment> out=new ArrayList<>();
    try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT * FROM treatments WHERE active=TRUE ORDER BY treatment_name");
            ResultSet rs=ps.executeQuery()){while(rs.next())out.add(map(rs));
    }
    return out;
    }
    public Treatment findById(int id)throws SQLException{try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT * FROM treatments WHERE treatment_id=?")){ps.setInt(1,id);
        try(ResultSet rs=ps.executeQuery()){return rs.next()?map(rs):null;
        }
    }
    }
    private Treatment map(ResultSet r)throws SQLException{Treatment t=new Treatment();
        t.setTreatmentId(r.getInt("treatment_id"));
        t.setTreatmentName(r.getString("treatment_name"));
        t.setTreatmentCost(r.getBigDecimal("treatment_cost"));
        t.setDescription(r.getString("description"));
        t.setActive(r.getBoolean("active"));
        return t;
    }
    
    public int create(Treatment treatment)
        throws SQLException {

    String sql =
            "INSERT INTO treatments "
            + "(treatment_name, treatment_cost, "
            + "description, active) "
            + "VALUES (?, ?, ?, ?)";

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            )) {

        statement.setString(
                1,
                treatment.getTreatmentName()
        );

        statement.setBigDecimal(
                2,
                treatment.getTreatmentCost()
        );

        statement.setString(
                3,
                treatment.getDescription()
        );

        statement.setBoolean(
                4,
                treatment.isActive()
        );

        statement.executeUpdate();

        try (ResultSet keys =
                statement.getGeneratedKeys()) {

            if (keys.next()) {

                treatment.setTreatmentId(
                        keys.getInt(1)
                );

                return treatment.getTreatmentId();
            }
        }
    }

    return 0;
}
    
    public boolean update(Treatment treatment)
        throws SQLException {

    String sql =
            "UPDATE treatments "
            + "SET treatment_name=?, "
            + "treatment_cost=?, "
            + "description=?, "
            + "active=? "
            + "WHERE treatment_id=?";

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(
                1,
                treatment.getTreatmentName()
        );

        statement.setBigDecimal(
                2,
                treatment.getTreatmentCost()
        );

        statement.setString(
                3,
                treatment.getDescription()
        );

        statement.setBoolean(
                4,
                treatment.isActive()
        );

        statement.setInt(
                5,
                treatment.getTreatmentId()
        );

        return statement.executeUpdate() > 0;
    }
}
    
    public List<Treatment> findAll()
        throws SQLException {

    List<Treatment> treatments =
            new ArrayList<>();

    String sql =
            "SELECT * FROM treatments "
            + "ORDER BY treatment_name";

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql);

         ResultSet resultSet =
            statement.executeQuery()) {

        while (resultSet.next()) {

            treatments.add(
                    map(resultSet)
            );
        }
    }

    return treatments;
}
}
