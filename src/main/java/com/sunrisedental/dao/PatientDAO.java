package com.sunrisedental.dao;
import com.sunrisedental.model.Patient;
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PatientDAO {
    public int create(Patient p)throws SQLException{
        String s="INSERT INTO patients(first_name,last_name,address,contact_number,email,date_of_birth,gender) VALUES(?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(s,Statement.RETURN_GENERATED_KEYS)){
            set(ps,p); ps.executeUpdate(); 
            try(ResultSet k=ps.getGeneratedKeys()){if(k.next()){p.setPatientId(k.getInt(1));
            return p.getPatientId();
            }
            }
        } return 0;
    }
    public boolean update(Patient p)throws SQLException{
        String s="UPDATE patients SET first_name=?,last_name=?,address=?,contact_number=?,email=?,date_of_birth=?,gender=? WHERE patient_id=?";
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s)){set(ps,p);
                ps.setInt(8,p.getPatientId());
                return ps.executeUpdate()>0;
        }
    }
    public Patient findById(int id)throws SQLException{
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement("SELECT * FROM patients WHERE patient_id=?")){ps.setInt(1,id);
                try(ResultSet rs=ps.executeQuery()){return rs.next()?map(rs):null;
                }
        }
    }
    public List<Patient> searchByName(String q)throws SQLException{
        List<Patient> list=new ArrayList<>();String s="SELECT * FROM patients WHERE first_name LIKE ? OR last_name LIKE ? ORDER BY first_name,last_name";
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s)){String x="%"+q+"%";ps.setString(1,x);ps.setString(2,x);
                try(ResultSet rs=ps.executeQuery()){while(rs.next())list.add(map(rs));
                }
        }return list;
    }
    private void set(PreparedStatement ps,Patient p)throws SQLException{
        ps.setString(1,p.getFirstName());
        ps.setString(2,p.getLastName());
        ps.setString(3,p.getAddress());
        ps.setString(4,p.getContactNumber());
        ps.setString(5,p.getEmail());
        if(p.getDateOfBirth()==null)ps.setNull(6,Types.DATE);
        else ps.setDate(6,java.sql.Date.valueOf(p.getDateOfBirth()));
        ps.setString(7,p.getGender());
    }
    private Patient map(ResultSet r)throws SQLException{Patient p=new Patient();
        p.setPatientId(r.getInt("patient_id"));
        p.setFirstName(r.getString("first_name"));
        p.setLastName(r.getString("last_name"));
        p.setAddress(r.getString("address"));
        p.setContactNumber(r.getString("contact_number"));
        p.setEmail(r.getString("email"));
        Date d=r.getDate("date_of_birth");
      if(d!=null)p.setDateOfBirth(d.toLocalDate());
        p.setGender(r.getString("gender"));
      return p;
    }
}
