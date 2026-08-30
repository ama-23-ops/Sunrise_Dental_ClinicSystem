package com.sunrisedental.model;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {
    private int patientId; 
    private String firstName,lastName,address,contactNumber,email,gender; 
    private LocalDate dateOfBirth;
    public int getPatientId(){return patientId;} 
    public void setPatientId(int v){patientId=v;}
    public String getFirstName(){return firstName;} 
    public void setFirstName(String v){firstName=v;}
    public String getLastName(){return lastName;} 
    public void setLastName(String v){lastName=v;}
    public String getAddress(){return address;} 
    public void setAddress(String v){address=v;}
    public String getContactNumber(){return contactNumber;} 
    public void setContactNumber(String v){contactNumber=v;}
    public String getEmail(){return email;} 
    public void setEmail(String v){email=v;}
    public LocalDate getDateOfBirth(){return dateOfBirth;} 
    public void setDateOfBirth(LocalDate v){dateOfBirth=v;}
    public String getGender(){return gender;} 
    public void setGender(String v){gender=v;}
@JsonIgnore
public String getFullName(){
    return firstName
            + (lastName == null || lastName.isBlank()
                    ? ""
                    : " " + lastName);
}}
