package com.sunrisedental.dao;
import com.sunrisedental.model.Dentist; 
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.util.*;

public class DentistDAO {
    public List<Dentist> findActive()throws SQLException{List<Dentist> out=new ArrayList<>();
    try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement("SELECT * FROM dentists WHERE active=TRUE ORDER BY dentist_name");
            ResultSet rs=ps.executeQuery()){while(rs.next()){Dentist d=new Dentist();
            d.setDentistId(rs.getInt("dentist_id"));d.setDentistName(rs.getString("dentist_name"));
            d.setContactNumber(rs.getString("contact_number"));
            d.setSpecialization(rs.getString("specialization"));
            d.setActive(rs.getBoolean("active"));out.add(d);
            }
    }return out;
    }
    
    public Dentist findById(int id)throws SQLException{try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("SELECT * FROM dentists WHERE dentist_id=?")){ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){if(!rs.next())return null;
            Dentist d=new Dentist();d.setDentistId(id);
            d.setDentistName(rs.getString("dentist_name"));
            d.setContactNumber(rs.getString("contact_number"));
            d.setSpecialization(rs.getString("specialization"));
            d.setActive(rs.getBoolean("active"));
            return d;
            }
    }
    }
    public int create(Dentist dentist)
        throws SQLException {

    String sql =
            "INSERT INTO dentists "
            + "(dentist_name, contact_number, "
            + "specialization, active) "
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
                dentist.getDentistName()
        );

        statement.setString(
                2,
                dentist.getContactNumber()
        );

        statement.setString(
                3,
                dentist.getSpecialization()
        );

        statement.setBoolean(
                4,
                dentist.isActive()
        );

        statement.executeUpdate();

        try (ResultSet keys =
                statement.getGeneratedKeys()) {

            if (keys.next()) {

                dentist.setDentistId(
                        keys.getInt(1)
                );

                return dentist.getDentistId();
            }
        }
    }

    return 0;
}
    public boolean update(Dentist dentist)
        throws SQLException {

    String sql =
            "UPDATE dentists "
            + "SET dentist_name=?, "
            + "contact_number=?, "
            + "specialization=?, "
            + "active=? "
            + "WHERE dentist_id=?";

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql)) {

        statement.setString(
                1,
                dentist.getDentistName()
        );

        statement.setString(
                2,
                dentist.getContactNumber()
        );

        statement.setString(
                3,
                dentist.getSpecialization()
        );

        statement.setBoolean(
                4,
                dentist.isActive()
        );

        statement.setInt(
                5,
                dentist.getDentistId()
        );

        return statement.executeUpdate() > 0;
    }
}
    
   public List<Dentist> findAll()
        throws SQLException {

    List<Dentist> dentists =
            new ArrayList<>();

    String sql =
            "SELECT * FROM dentists "
            + "ORDER BY dentist_name";

    try (Connection connection =
            DBConnection.getConnection();

         PreparedStatement statement =
            connection.prepareStatement(sql);

         ResultSet resultSet =
            statement.executeQuery()) {

        while (resultSet.next()) {

            Dentist dentist =
                    new Dentist();

            dentist.setDentistId(
                    resultSet.getInt("dentist_id")
            );

            dentist.setDentistName(
                    resultSet.getString(
                            "dentist_name"
                    )
            );

            dentist.setContactNumber(
                    resultSet.getString(
                            "contact_number"
                    )
            );

            dentist.setSpecialization(
                    resultSet.getString(
                            "specialization"
                    )
            );

            dentist.setActive(
                    resultSet.getBoolean(
                            "active"
                    )
            );

            dentists.add(dentist);
        }
    }

    return dentists;
}
}


