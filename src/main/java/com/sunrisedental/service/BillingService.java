package com.sunrisedental.service;
import com.sunrisedental.dao.*;
import com.sunrisedental.model.*;
import com.sunrisedental.util.NumberGenerator;
import java.math.*;
import java.sql.*;

public class BillingService {
    private final AppointmentDAO appointmentDAO=new AppointmentDAO(); 
    private final TreatmentDAO treatmentDAO=new TreatmentDAO(); 
    private final BillDAO billDAO=new BillDAO();
    public Bill createBill(String appointmentNo,BigDecimal consultationFee,String paymentStatus,String paymentMethod)throws SQLException{
        Appointment a=appointmentDAO.findByNo(appointmentNo);
        if(a==null)throw new IllegalArgumentException("Appointment not found.");
        if(billDAO.findByAppointmentId(a.getAppointmentId())!=null)throw new IllegalArgumentException("A bill already exists for this appointment.");
        if(consultationFee==null||consultationFee.signum()<0)throw new IllegalArgumentException("Consultation fee cannot be negative.");
        Treatment t=treatmentDAO.findById(a.getTreatmentId());
        if(t==null)throw new IllegalArgumentException("Treatment not found.");
        Bill b=new Bill();
            b.setAppointmentId(a.getAppointmentId());
            b.setConsultationFee(consultationFee);
            b.setTreatmentFee(t.getTreatmentCost());
            b.setTotalAmount(calculateTotal(consultationFee,t.getTreatmentCost()));
            b.setPaymentStatus(paymentStatus==null?"UNPAID":paymentStatus);
            b.setPaymentMethod(paymentMethod);
            b.setBillNumber("TEMP-"+System.nanoTime());
            int id=billDAO.create(b);
            String no=NumberGenerator.billNo(id);
        try(Connection c=com.sunrisedental.util.DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement("UPDATE bills SET bill_number=? WHERE bill_id=?")){ps.setString(1,no);
                ps.setInt(2,id);
                ps.executeUpdate();
        }
        b.setBillId(id);b.setBillNumber(no);
        return b;
    }
    public BigDecimal calculateTotal(BigDecimal consultation,BigDecimal treatment){return consultation.add(treatment).setScale(2,RoundingMode.HALF_UP);
    }
}
