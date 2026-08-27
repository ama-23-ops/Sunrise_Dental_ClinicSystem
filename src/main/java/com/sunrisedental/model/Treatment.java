package com.sunrisedental.model;
import java.math.BigDecimal;

public class Treatment {
    private int treatmentId; 
    private String treatmentName,description; 
    private BigDecimal treatmentCost; 
    private boolean active=true;
    public int getTreatmentId(){return treatmentId;} 
    public void setTreatmentId(int v){treatmentId=v;}
    public String getTreatmentName(){return treatmentName;} 
    public void setTreatmentName(String v){treatmentName=v;}
    public String getDescription(){return description;} 
    public void setDescription(String v){description=v;}
    public BigDecimal getTreatmentCost(){return treatmentCost;} 
    public void setTreatmentCost(BigDecimal v){treatmentCost=v;}
    public boolean isActive(){return active;} 
    public void setActive(boolean v){active=v;}
    public String toString(){return treatmentName;}
}
