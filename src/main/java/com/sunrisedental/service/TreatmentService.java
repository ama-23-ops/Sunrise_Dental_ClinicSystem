/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.dao.TreatmentDAO;
import com.sunrisedental.model.Treatment;
import com.sunrisedental.util.Validator;

import java.sql.SQLException;
import java.util.List;

public class TreatmentService {

    private final TreatmentDAO dao =
            new TreatmentDAO();


    // ==========================================
    // CREATE TREATMENT
    // ==========================================

    public int save(Treatment treatment)
            throws SQLException {

        Validator.require(
                treatment.getTreatmentName(),
                "Treatment name"
        );

        if (treatment.getTreatmentCost() == null) {

            throw new IllegalArgumentException(
                    "Treatment cost is required."
            );
        }

        if (treatment.getTreatmentCost()
                .compareTo(java.math.BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Treatment cost cannot be negative."
            );
        }

        return dao.create(treatment);
    }


    // ==========================================
    // UPDATE TREATMENT
    // ==========================================

    public boolean update(Treatment treatment)
            throws SQLException {

        return dao.update(treatment);
    }


    // ==========================================
    // FIND TREATMENT BY ID
    // ==========================================

    public Treatment findById(int id)
            throws SQLException {

        return dao.findById(id);
    }


    // ==========================================
    // FIND ALL TREATMENTS
    // ==========================================

    public List<Treatment> findAll()
            throws SQLException {

        return dao.findAll();
    }


    // ==========================================
    // FIND ACTIVE TREATMENTS
    // ==========================================

    public List<Treatment> findActive()
            throws SQLException {

        return dao.findActive();
    }
}
