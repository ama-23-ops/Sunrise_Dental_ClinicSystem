package com.sunrisedental.service;
import com.sunrisedental.dao.AppointmentDAO;
import com.sunrisedental.model.Appointment;
import com.sunrisedental.util.NumberGenerator;
import com.sunrisedental.util.Validator;
import java.sql.*;

public class AppointmentService {
    private final AppointmentDAO dao=new AppointmentDAO();
    public int create(Appointment a)throws SQLException{
        if(a.getPatientId()<=0||a.getDentistId()<=0||a.getTreatmentId()<=0)throw new IllegalArgumentException("Patient, dentist and treatment are required.");
        if(!Validator.validFutureOrToday(a.getAppointmentDate()))throw new IllegalArgumentException("Appointment date cannot be in the past.");
        if(a.getAppointmentTime()==null)throw new IllegalArgumentException("Appointment time is required.");
        if(dao.dentistBusy(a.getDentistId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),null))throw new IllegalArgumentException("Selected dentist already has an appointment at this date and time.");
                a.setStatus(a.getStatus()==null?"SCHEDULED":
                a.getStatus());
                a.setAppointmentNo("TEMP-"+System.nanoTime());
        int id=dao.create(a);
        String finalNo=NumberGenerator.appointmentNo(id);
        updateAppointmentNo(id,finalNo);a.setAppointmentNo(finalNo);
        return id;
    }
    private void updateAppointmentNo(int id,String no)throws SQLException{
        try(Connection c=com.sunrisedental.util.DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement("UPDATE appointments SET appointment_no=? WHERE appointment_id=?")){ps.setString(1,no);
                ps.setInt(2,id);
                ps.executeUpdate();}
    }
    public boolean update(Appointment a)throws SQLException{
        if(dao.dentistBusy(a.getDentistId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getAppointmentId()))throw new IllegalArgumentException("Selected dentist already has an appointment at this date and time.");
        return dao.update(a);
    }
    public boolean cancel(int id)throws SQLException{return dao.setStatus(id,"CANCELLED");
    }
    
    public Appointment findByNo(
        String appointmentNo)
        throws SQLException {

    if (appointmentNo == null ||
            appointmentNo.trim().isEmpty()) {

        throw new IllegalArgumentException(
                "Appointment number is required."
        );
    }

    return dao.findByNo(
            appointmentNo.trim()
    );
}
    
    public java.util.List<Appointment> findAll()
        throws SQLException {

    return dao.findAll();
}
}
