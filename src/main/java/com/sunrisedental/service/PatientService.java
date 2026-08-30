package com.sunrisedental.service;

import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.model.Patient;
import com.sunrisedental.util.Validator;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    private final PatientDAO dao =
            new PatientDAO();

    // CREATE

    public int save(Patient p)
            throws SQLException {

        Validator.require(
                p.getFirstName(),
                "First name"
        );

        Validator.require(
                p.getAddress(),
                "Address"
        );

        Validator.require(
                p.getContactNumber(),
                "Contact number"
        );

        if (!Validator.validPhone(
                p.getContactNumber())) {

            throw new IllegalArgumentException(
                    "Invalid contact number."
            );
        }

        if (!Validator.validEmail(
                p.getEmail())) {

            throw new IllegalArgumentException(
                    "Invalid email address."
            );
        }

        return dao.create(p);
    }

    // UPDATE
    public boolean update(Patient p)
            throws SQLException {

        return dao.update(p);
    }

    // FIND BY ID

    public Patient findById(int id)
            throws SQLException {

        return dao.findById(id);
    }

    // SEARCH BY NAME

    public List<Patient> searchByName(String query)
        throws SQLException {

    if (query == null) {
        query = "";
    }

    return dao.searchByName(query.trim());
}
}