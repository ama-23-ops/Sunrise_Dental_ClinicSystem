package com.sunrisedental.model;
import java.math.BigDecimal; 
import java.time.LocalDateTime;

public class Bill {
    private int billId,appointmentId; private String billNumber,paymentStatus,paymentMethod;
    private BigDecimal consultationFee,treatmentFee,totalAmount; 
    private LocalDateTime billDate;
    public int getBillId(){return billId;} 
    public void setBillId(int v){billId=v;}
    public int getAppointmentId(){return appointmentId;} 
    public void setAppointmentId(int v){appointmentId=v;}
    public String getBillNumber(){return billNumber;} 
    public void setBillNumber(String v){billNumber=v;}
    public String getPaymentStatus(){return paymentStatus;} 
    public void setPaymentStatus(String v){paymentStatus=v;}
    public String getPaymentMethod(){return paymentMethod;} 
    public void setPaymentMethod(String v){paymentMethod=v;}
    public BigDecimal getConsultationFee(){return consultationFee;} 
    public void setConsultationFee(BigDecimal v){consultationFee=v;}
    public BigDecimal getTreatmentFee(){return treatmentFee;} 
    public void setTreatmentFee(BigDecimal v){treatmentFee=v;}
    public BigDecimal getTotalAmount(){return totalAmount;} 
    public void setTotalAmount(BigDecimal v){totalAmount=v;}
    public LocalDateTime getBillDate(){return billDate;} 
    public void setBillDate(LocalDateTime v){billDate=v;}
}
