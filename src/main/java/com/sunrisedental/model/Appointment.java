package com.sunrisedental.model;
import java.time.LocalDate; 
import java.time.LocalTime;

public class Appointment {
    private int appointmentId,patientId,dentistId,treatmentId;
    private String appointmentNo,status,notes;
    private LocalDate appointmentDate; private LocalTime appointmentTime;
    public int getAppointmentId(){return appointmentId;} public void setAppointmentId(int v){appointmentId=v;}
    public int getPatientId(){return patientId;} public void setPatientId(int v){patientId=v;}
    public int getDentistId(){return dentistId;} public void setDentistId(int v){dentistId=v;}
    public int getTreatmentId(){return treatmentId;} public void setTreatmentId(int v){treatmentId=v;}
    public String getAppointmentNo(){return appointmentNo;} public void setAppointmentNo(String v){appointmentNo=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public LocalDate getAppointmentDate(){return appointmentDate;} public void setAppointmentDate(LocalDate v){appointmentDate=v;}
    public LocalTime getAppointmentTime(){return appointmentTime;} public void setAppointmentTime(LocalTime v){appointmentTime=v;}
}
