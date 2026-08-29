package com.sunrisedental.report;
import com.sunrisedental.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ReportDAO {
    public List<AppointmentDetails> dailyAppointments(LocalDate date)throws SQLException{
        String s="SELECT a.appointment_no,CONCAT(p.first_name,' ',COALESCE(p.last_name,'')) patient_name,p.address,p.contact_number,d.dentist_name,t.treatment_name,a.appointment_date,a.appointment_time,a.status FROM appointments a JOIN patients p ON a.patient_id=p.patient_id JOIN dentists d ON a.dentist_id=d.dentist_id JOIN treatments t ON a.treatment_id=t.treatment_id WHERE a.appointment_date=? ORDER BY a.appointment_time";
        List<AppointmentDetails> out=new ArrayList<>();
          try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s)){ps.setDate(1,java.sql.Date.valueOf(date));
            try(ResultSet r=ps.executeQuery()){while(r.next()){AppointmentDetails x=new AppointmentDetails();
                x.appointmentNo=r.getString("appointment_no");
                x.patientName=r.getString("patient_name");
                x.address=r.getString("address");
                x.contactNumber=r.getString("contact_number");
                x.dentistName=r.getString("dentist_name");
                x.treatmentName=r.getString("treatment_name");
                x.appointmentDate=r.getDate("appointment_date").toLocalDate();
                x.appointmentTime=r.getTime("appointment_time").toLocalTime();
                x.status=r.getString("status");
                out.add(x);
            }
            }
          }return out;
    }
}
