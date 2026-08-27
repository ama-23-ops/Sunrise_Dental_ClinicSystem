package com.sunrisedental.service;
import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.model.Patient;
import com.sunrisedental.util.Validator;
import java.sql.SQLException;

public class PatientService {
    private final PatientDAO dao=new PatientDAO();
    public int save(Patient p)throws SQLException{
        Validator.require(p.getFirstName(),"First name");
        Validator.require(p.getAddress(),"Address");
        Validator.require(p.getContactNumber(),"Contact number");
        if(!Validator.validPhone(p.getContactNumber()))throw new IllegalArgumentException("Invalid contact number.");
        if(!Validator.validEmail(p.getEmail()))throw new IllegalArgumentException("Invalid email address.");
        return dao.create(p);
    }
    public boolean update(Patient p)throws SQLException{return dao.update(p);
    }
}
