package com.sunrisedental.model;

public class Dentist {
    private int dentistId; 
    private String dentistName,contactNumber,specialization; 
    private boolean active=true;
    public int getDentistId(){return dentistId;} 
    public void setDentistId(int v){dentistId=v;}
    public String getDentistName(){return dentistName;} 
    public void setDentistName(String v){dentistName=v;}
    public String getContactNumber(){return contactNumber;} 
    public void setContactNumber(String v){contactNumber=v;}
    public String getSpecialization(){return specialization;} 
    public void setSpecialization(String v){specialization=v;}
    public boolean isActive(){return active;} 
    public void setActive(boolean v){active=v;}
    public String toString(){return dentistName;}
}
