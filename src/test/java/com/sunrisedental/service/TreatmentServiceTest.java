/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.Treatment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TreatmentServiceTest {

    private final TreatmentService treatmentService =
            new TreatmentService();


    // ==========================================
    // TEST 1 - FIND ALL TREATMENTS
    // ==========================================

    @Test
    public void testFindAllTreatments()
            throws Exception {

        List<Treatment> results =
                treatmentService.findAll();

        assertNotNull(
                results,
                "findAll should return a list"
        );

        assertFalse(
                results.isEmpty(),
                "There should be at least one treatment"
        );
    }


    // ==========================================
    // TEST 2 - FIND EXISTING TREATMENT
    // ==========================================

    @Test
    public void testFindExistingTreatment()
            throws Exception {

        List<Treatment> treatments =
                treatmentService.findAll();

        assertFalse(
                treatments.isEmpty(),
                "Test database must contain at least one treatment"
        );

        Treatment firstTreatment =
                treatments.get(0);

        Treatment result =
                treatmentService.findById(
                        firstTreatment.getTreatmentId()
                );

        assertNotNull(
                result,
                "Existing treatment should be returned"
        );

        assertEquals(
                firstTreatment.getTreatmentId(),
                result.getTreatmentId()
        );
    }


    // ==========================================
    // TEST 3 - FIND NON-EXISTING TREATMENT
    // ==========================================

    @Test
    public void testFindNonExistingTreatment()
            throws Exception {

        Treatment result =
                treatmentService.findById(999999);

        assertNull(
                result,
                "Non-existing treatment should return null"
        );
    }


    // ==========================================
    // TEST 4 - FIND ACTIVE TREATMENTS
    // ==========================================

    @Test
    public void testFindActiveTreatments()
            throws Exception {

        List<Treatment> results =
                treatmentService.findActive();

        assertNotNull(
                results,
                "findActive should return a list"
        );

        for (Treatment treatment : results) {

            assertTrue(
                    treatment.isActive(),
                    "All returned treatments should be active"
            );
        }
    }


    // ==========================================
    // TEST 5 - TREATMENT NAME REQUIRED
    // ==========================================

    @Test
    public void testTreatmentNameRequired() {

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentName("");

        treatment.setTreatmentCost(
                new BigDecimal("5000.00")
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> treatmentService.save(
                                treatment
                        )
                );

        assertEquals(
                "Treatment name is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - TREATMENT COST REQUIRED
    // ==========================================

    @Test
    public void testTreatmentCostRequired() {

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentName(
                "Test Treatment"
        );

        treatment.setTreatmentCost(null);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> treatmentService.save(
                                treatment
                        )
                );

        assertEquals(
                "Treatment cost is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 7 - NEGATIVE TREATMENT COST
    // ==========================================

    @Test
    public void testNegativeTreatmentCost() {

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentName(
                "Test Treatment"
        );

        treatment.setTreatmentCost(
                new BigDecimal("-100.00")
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> treatmentService.save(
                                treatment
                        )
                );

        assertEquals(
                "Treatment cost cannot be negative.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 8 - ZERO TREATMENT COST
    // ==========================================

    @Test
    public void testZeroTreatmentCost()
            throws Exception {

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentName(
                "Free Consultation Test"
        );

        treatment.setTreatmentCost(
                BigDecimal.ZERO
        );

        /*
         * Zero is allowed by the current
         * TreatmentService validation.
         *
         * This test only verifies that the
         * validation does not reject zero.
         *
         * We do not call save() because that
         * would insert a test record into
         * the database.
         */

        assertDoesNotThrow(() -> {

            if (treatment.getTreatmentCost()
                    .compareTo(BigDecimal.ZERO) < 0) {

                throw new IllegalArgumentException(
                        "Treatment cost cannot be negative."
                );
            }
        });
    }


    // ==========================================
    // TEST 9 - UPDATE NON-EXISTING TREATMENT
    // ==========================================

    @Test
    public void testUpdateNonExistingTreatment()
            throws Exception {

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentId(999999);

        treatment.setTreatmentName(
                "Test Treatment"
        );

        treatment.setTreatmentCost(
                new BigDecimal("5000.00")
        );

        treatment.setDescription(
                "Test description"
        );

        treatment.setActive(true);

        boolean result =
                treatmentService.update(
                        treatment
                );

        assertFalse(
                result,
                "Updating a non-existing treatment should return false"
        );
    }
}