/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.dao.DentistDAO;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.util.Validator;

import java.sql.SQLException;
import java.util.List;

public class DentistService {

    private final DentistDAO dao =
            new DentistDAO();


    // ==========================================
    // CREATE DENTIST
    // ==========================================

    public int save(Dentist dentist)
            throws SQLException {

        Validator.require(
                dentist.getDentistName(),
                "Dentist name"
        );

        Validator.require(
                dentist.getContactNumber(),
                "Contact number"
        );

        Validator.require(
                dentist.getSpecialization(),
                "Specialization"
        );

        if (!Validator.validPhone(
                dentist.getContactNumber())) {

            throw new IllegalArgumentException(
                    "Invalid contact number."
            );
        }

        return dao.create(dentist);
    }


    // ==========================================
    // UPDATE DENTIST
    // ==========================================

    public boolean update(Dentist dentist)
            throws SQLException {

        return dao.update(dentist);
    }


    // ==========================================
    // FIND DENTIST BY ID
    // ==========================================

    public Dentist findById(int id)
            throws SQLException {

        return dao.findById(id);
    }


    // ==========================================
    // FIND ALL DENTISTS
    // ==========================================

    public List<Dentist> findAll()
            throws SQLException {

        return dao.findAll();
    }


    // ==========================================
    // FIND ACTIVE DENTISTS
    // ==========================================

    public List<Dentist> findActive()
            throws SQLException {

        return dao.findActive();
    }
}
