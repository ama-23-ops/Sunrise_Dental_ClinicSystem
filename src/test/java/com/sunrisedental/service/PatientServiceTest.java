/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.Patient;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    private final PatientService patientService =
            new PatientService();


    // ==========================================
    // TEST 1 - FIND EXISTING PATIENT
    // ==========================================

    @Test
    public void testFindExistingPatient() throws Exception {

        Patient result =
                patientService.findById(1);

        assertNotNull(
                result,
                "Existing patient should be returned"
        );

        assertEquals(
                1,
                result.getPatientId()
        );
    }


    // ==========================================
    // TEST 2 - FIND NON-EXISTING PATIENT
    // ==========================================

    @Test
    public void testFindNonExistingPatient() throws Exception {

        Patient result =
                patientService.findById(999999);

        assertNull(
                result,
                "Non-existing patient should return null"
        );
    }


    // ==========================================
    // TEST 3 - SEARCH PATIENT BY NAME
    // ==========================================

    @Test
    public void testSearchPatientByName() throws Exception {

        var results =
                patientService.searchByName("Sara");

        assertNotNull(
                results,
                "Search should return a list"
        );

        assertFalse(
                results.isEmpty(),
                "Search for an existing patient should return results"
        );
    }


    // ==========================================
    // TEST 4 - EMPTY SEARCH QUERY
    // ==========================================

    @Test
    public void testSearchWithEmptyQuery() throws Exception {

        var results =
                patientService.searchByName("");

        assertNotNull(
                results,
                "Empty search should return a list"
        );
    }


    // ==========================================
    // TEST 5 - FIRST NAME REQUIRED
    // ==========================================

    @Test
    public void testFirstNameRequired() {

        Patient patient = new Patient();

        patient.setFirstName("");
        patient.setAddress("Colombo");
        patient.setContactNumber("0711234567");
        patient.setEmail("test@gmail.com");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService.save(patient)
                );

        assertEquals(
                "First name is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - ADDRESS REQUIRED
    // ==========================================

    @Test
    public void testAddressRequired() {

        Patient patient = new Patient();

        patient.setFirstName("Test");
        patient.setAddress("");
        patient.setContactNumber("0711234567");
        patient.setEmail("test@gmail.com");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService.save(patient)
                );

        assertEquals(
                "Address is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 7 - CONTACT NUMBER REQUIRED
    // ==========================================

    @Test
    public void testContactNumberRequired() {

        Patient patient = new Patient();

        patient.setFirstName("Test");
        patient.setAddress("Colombo");
        patient.setContactNumber("");
        patient.setEmail("test@gmail.com");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService.save(patient)
                );

        assertEquals(
                "Contact number is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 8 - INVALID PHONE NUMBER
    // ==========================================

    @Test
    public void testInvalidPhoneNumber() {

        Patient patient = new Patient();

        patient.setFirstName("Test");
        patient.setAddress("Colombo");
        patient.setContactNumber("123");
        patient.setEmail("test@gmail.com");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService.save(patient)
                );

        assertEquals(
                "Invalid contact number.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 9 - INVALID EMAIL
    // ==========================================

    @Test
    public void testInvalidEmail() {

        Patient patient = new Patient();

        patient.setFirstName("Test");
        patient.setAddress("Colombo");
        patient.setContactNumber("0711234567");
        patient.setEmail("invalid-email");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService.save(patient)
                );

        assertEquals(
                "Invalid email address.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 10 - UPDATE NON-EXISTING PATIENT
    // ==========================================

    @Test
    public void testUpdateNonExistingPatient() throws Exception {

        Patient patient = new Patient();

        patient.setPatientId(999999);
        patient.setFirstName("Test");
        patient.setLastName("Patient");
        patient.setAddress("Colombo");
        patient.setContactNumber("0711234567");
        patient.setEmail("test@gmail.com");
        patient.setDateOfBirth(
                LocalDate.of(2000, 1, 1)
        );
        patient.setGender("Female");

        boolean result =
                patientService.update(patient);

        assertFalse(
                result,
                "Updating a non-existing patient should return false"
        );
    }
}