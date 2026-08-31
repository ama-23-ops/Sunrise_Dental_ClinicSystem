/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.Dentist;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DentistServiceTest {

    private final DentistService dentistService =
            new DentistService();


    // ==========================================
    // TEST 1 - FIND ALL DENTISTS
    // ==========================================

    @Test
    public void testFindAllDentists() throws Exception {

        List<Dentist> results =
                dentistService.findAll();

        assertNotNull(
                results,
                "findAll should return a list"
        );

        assertFalse(
                results.isEmpty(),
                "There should be at least one dentist"
        );
    }


    // ==========================================
    // TEST 2 - FIND EXISTING DENTIST
    // ==========================================

    @Test
    public void testFindExistingDentist() throws Exception {

        List<Dentist> dentists =
                dentistService.findAll();

        assertFalse(
                dentists.isEmpty(),
                "Test database must contain at least one dentist"
        );

        Dentist firstDentist =
                dentists.get(0);

        Dentist result =
                dentistService.findById(
                        firstDentist.getDentistId()
                );

        assertNotNull(
                result,
                "Existing dentist should be returned"
        );

        assertEquals(
                firstDentist.getDentistId(),
                result.getDentistId()
        );
    }


    // ==========================================
    // TEST 3 - FIND NON-EXISTING DENTIST
    // ==========================================

    @Test
    public void testFindNonExistingDentist()
            throws Exception {

        Dentist result =
                dentistService.findById(999999);

        assertNull(
                result,
                "Non-existing dentist should return null"
        );
    }


    // ==========================================
    // TEST 4 - FIND ACTIVE DENTISTS
    // ==========================================

    @Test
    public void testFindActiveDentists()
            throws Exception {

        List<Dentist> results =
                dentistService.findActive();

        assertNotNull(
                results,
                "findActive should return a list"
        );

        for (Dentist dentist : results) {

            assertTrue(
                    dentist.isActive(),
                    "All returned dentists should be active"
            );
        }
    }


    // ==========================================
    // TEST 5 - DENTIST NAME REQUIRED
    // ==========================================

    @Test
    public void testDentistNameRequired() {

        Dentist dentist = new Dentist();

        dentist.setDentistName("");
        dentist.setContactNumber("0711234567");
        dentist.setSpecialization("General Dentistry");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> dentistService.save(dentist)
                );

        assertEquals(
                "Dentist name is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - CONTACT NUMBER REQUIRED
    // ==========================================

    @Test
    public void testContactNumberRequired() {

        Dentist dentist = new Dentist();

        dentist.setDentistName("Test Dentist");
        dentist.setContactNumber("");
        dentist.setSpecialization("General Dentistry");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> dentistService.save(dentist)
                );

        assertEquals(
                "Contact number is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 7 - SPECIALIZATION REQUIRED
    // ==========================================

    @Test
    public void testSpecializationRequired() {

        Dentist dentist = new Dentist();

        dentist.setDentistName("Test Dentist");
        dentist.setContactNumber("0711234567");
        dentist.setSpecialization("");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> dentistService.save(dentist)
                );

        assertEquals(
                "Specialization is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 8 - INVALID CONTACT NUMBER
    // ==========================================

    @Test
    public void testInvalidContactNumber() {

        Dentist dentist = new Dentist();

        dentist.setDentistName("Test Dentist");
        dentist.setContactNumber("123");
        dentist.setSpecialization("General Dentistry");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> dentistService.save(dentist)
                );

        assertEquals(
                "Invalid contact number.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 9 - UPDATE NON-EXISTING DENTIST
    // ==========================================

    @Test
    public void testUpdateNonExistingDentist()
            throws Exception {

        Dentist dentist = new Dentist();

        dentist.setDentistId(999999);
        dentist.setDentistName("Test Dentist");
        dentist.setContactNumber("0711234567");
        dentist.setSpecialization("General Dentistry");
        dentist.setActive(true);

        boolean result =
                dentistService.update(dentist);

        assertFalse(
                result,
                "Updating a non-existing dentist should return false"
        );
    }
}