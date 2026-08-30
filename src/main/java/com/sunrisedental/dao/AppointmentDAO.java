package com.sunrisedental.dao;
import com.sunrisedental.model.Appointment;
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public int create(Appointment a)throws SQLException{
        String s="INSERT INTO appointments(appointment_no,patient_id,dentist_id,treatment_id,appointment_date,appointment_time,status,notes) VALUES(?,?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s,Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,a.getAppointmentNo());
            ps.setInt(2,a.getPatientId());
            ps.setInt(3,a.getDentistId());
            ps.setInt(4,a.getTreatmentId());ps.setDate(5,java.sql.Date.valueOf(a.getAppointmentDate()));ps.setTime(6,Time.valueOf(a.getAppointmentTime()));ps.setString(7,a.getStatus());ps.setString(8,a.getNotes());ps.executeUpdate();
            
            try(ResultSet k=ps.getGeneratedKeys()){if(k.next()){a.setAppointmentId(k.getInt(1));
            return a.getAppointmentId();
            }
            }
        }return 0;
    }
    public boolean update(Appointment a)throws SQLException{
        String s="UPDATE appointments SET patient_id=?,dentist_id=?,treatment_id=?,appointment_date=?,appointment_time=?,status=?,notes=? WHERE appointment_id=?";
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s)){ps.setInt(1,a.getPatientId());
                ps.setInt(2,a.getDentistId());
                ps.setInt(3,a.getTreatmentId());ps.setDate(4,java.sql.Date.valueOf(a.getAppointmentDate()));ps.setTime(5,Time.valueOf(a.getAppointmentTime()));ps.setString(6,a.getStatus());ps.setString(7,a.getNotes());ps.setInt(8,a.getAppointmentId());
                return ps.executeUpdate()>0;
        }
    }
    public Appointment findByNo(String no)throws SQLException{String s="SELECT * FROM appointments WHERE appointment_no=?";
    try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(s)){ps.setString(1,no);
            try(ResultSet rs=ps.executeQuery()){return rs.next()?map(rs):null;}
    }
    }
    public boolean dentistBusy(int dentistId,LocalDate date,LocalTime time,Integer excludeId)throws SQLException{
        String s="SELECT COUNT(*) FROM appointments WHERE dentist_id=? AND appointment_date=? AND appointment_time=? AND status<>'CANCELLED'"+(excludeId!=null?" AND appointment_id<>?":"");
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s)){ps.setInt(1,dentistId);
                ps.setDate(2,java.sql.Date.valueOf(date));
                ps.setTime(3,Time.valueOf(time));
                if(excludeId!=null)
                    ps.setInt(4,excludeId);
                try(ResultSet rs=ps.executeQuery()){rs.next();
                return rs.getInt(1)>0;
                }
        }
    }
    public boolean setStatus(int id,String status)throws SQLException{try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement("UPDATE appointments SET status=? WHERE appointment_id=?")){ps.setString(1,status);
            ps.setInt(2,id);
            return ps.executeUpdate()>0;
    }
    }
    private Appointment map(ResultSet r)throws SQLException{Appointment a=new Appointment();
      a.setAppointmentId(r.getInt("appointment_id"));
      a.setAppointmentNo(r.getString("appointment_no"));
      a.setPatientId(r.getInt("patient_id"));a.setDentistId(r.getInt("dentist_id"));
      a.setTreatmentId(r.getInt("treatment_id"));
      a.setAppointmentDate(r.getDate("appointment_date").toLocalDate());
      a.setAppointmentTime(r.getTime("appointment_time").toLocalTime());
      a.setStatus(r.getString("status"));
      a.setNotes(r.getString("notes"));
      return a;
    }

public List<Appointment> findAll() throws SQLException {

    List<Appointment> appointments = new ArrayList<>();

    String sql = """
        SELECT *
        FROM appointments
        ORDER BY appointment_date DESC, appointment_time DESC
        """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Appointment appointment = new Appointment();

            appointment.setAppointmentId(
                    resultSet.getInt("appointment_id")
            );

            appointment.setAppointmentNo(
                    resultSet.getString("appointment_no")
            );

            appointment.setPatientId(
                    resultSet.getInt("patient_id")
            );

            appointment.setDentistId(
                    resultSet.getInt("dentist_id")
            );

            appointment.setTreatmentId(
                    resultSet.getInt("treatment_id")
            );

            appointment.setAppointmentDate(
                    resultSet.getDate("appointment_date")
                            .toLocalDate()
            );

            appointment.setAppointmentTime(
                    resultSet.getTime("appointment_time")
                            .toLocalTime()
            );

            appointment.setStatus(
                    resultSet.getString("status")
            );

            appointment.setNotes(
                    resultSet.getString("notes")
            );

            appointments.add(appointment);
        }
    }

    return appointments;
}

public int countTodayAppointments()
        throws SQLException {

    String sql =
            "SELECT COUNT(*) "
            + "FROM appointments "
            + "WHERE appointment_date = CURRENT_DATE "
            + "AND status <> 'CANCELLED'";

    try (Connection connection =
            DBConnection.getConnection();
         PreparedStatement statement =
            connection.prepareStatement(sql);
         ResultSet resultSet =
            statement.executeQuery()) {

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    }

    return 0;
}
}



