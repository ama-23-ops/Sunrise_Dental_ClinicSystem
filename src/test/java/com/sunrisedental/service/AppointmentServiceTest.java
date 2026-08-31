/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.Appointment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private final AppointmentService appointmentService =
            new AppointmentService();


    // ==========================================
    // TEST 1 - FIND ALL APPOINTMENTS
    // ==========================================

    @Test
    public void testFindAllAppointments()
            throws Exception {

        List<Appointment> results =
                appointmentService.findAll();

        assertNotNull(
                results,
                "findAll should return a list"
        );
    }


    // ==========================================
    // TEST 2 - FIND EXISTING APPOINTMENT
    // ==========================================

    @Test
    public void testFindExistingAppointment()
            throws Exception {

        List<Appointment> appointments =
                appointmentService.findAll();

        if (appointments.isEmpty()) {
            return;
        }

        Appointment existing =
                appointments.get(0);

        Appointment result =
                appointmentService.findByNo(
                        existing.getAppointmentNo()
                );

        assertNotNull(
                result,
                "Existing appointment should be returned"
        );

        assertEquals(
                existing.getAppointmentNo(),
                result.getAppointmentNo()
        );
    }


    // ==========================================
    // TEST 3 - FIND NON-EXISTING APPOINTMENT
    // ==========================================

    @Test
    public void testFindNonExistingAppointment()
            throws Exception {

        Appointment result =
                appointmentService.findByNo(
                        "APT-NONEXISTENT-999999"
                );

        assertNull(
                result,
                "Non-existing appointment should return null"
        );
    }


    // ==========================================
    // TEST 4 - EMPTY APPOINTMENT NUMBER
    // ==========================================

    @Test
    public void testEmptyAppointmentNumber() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService.findByNo("")
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 5 - NULL APPOINTMENT NUMBER
    // ==========================================

    @Test
    public void testNullAppointmentNumber() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService.findByNo(null)
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - REQUIRED PATIENT, DENTIST
    //            AND TREATMENT
    // ==========================================

    @Test
    public void testRequiredPatientDentistTreatment() {

        Appointment appointment =
                new Appointment();

        appointment.setPatientId(0);
        appointment.setDentistId(0);
        appointment.setTreatmentId(0);

        appointment.setAppointmentDate(
                LocalDate.now()
        );

        appointment.setAppointmentTime(
                LocalTime.of(10, 0)
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService.create(
                                appointment
                        )
                );

        assertEquals(
                "Patient, dentist and treatment are required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 7 - PAST APPOINTMENT DATE
    // ==========================================

    @Test
    public void testPastAppointmentDate() {

        Appointment appointment =
                new Appointment();

        appointment.setPatientId(1);
        appointment.setDentistId(1);
        appointment.setTreatmentId(1);

        appointment.setAppointmentDate(
                LocalDate.now().minusDays(1)
        );

        appointment.setAppointmentTime(
                LocalTime.of(10, 0)
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService.create(
                                appointment
                        )
                );

        assertEquals(
                "Appointment date cannot be in the past.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 8 - APPOINTMENT TIME REQUIRED
    // ==========================================

    @Test
    public void testAppointmentTimeRequired() {

        Appointment appointment =
                new Appointment();

        appointment.setPatientId(1);
        appointment.setDentistId(1);
        appointment.setTreatmentId(1);

        appointment.setAppointmentDate(
                LocalDate.now()
        );

        appointment.setAppointmentTime(null);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService.create(
                                appointment
                        )
                );

        assertEquals(
                "Appointment time is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 9 - CANCEL NON-EXISTING APPOINTMENT
    // ==========================================

    @Test
    public void testCancelNonExistingAppointment()
            throws Exception {

        boolean result =
                appointmentService.cancel(999999);

        assertFalse(
                result,
                "Cancelling a non-existing appointment should return false"
        );
    }


    // ==========================================
    // TEST 10 - UPDATE NON-EXISTING APPOINTMENT
    // ==========================================

    @Test
    public void testUpdateNonExistingAppointment()
            throws Exception {

        Appointment appointment =
                new Appointment();

        appointment.setAppointmentId(999999);
        appointment.setPatientId(1);
        appointment.setDentistId(1);
        appointment.setTreatmentId(1);

        appointment.setAppointmentDate(
                LocalDate.now()
        );

        appointment.setAppointmentTime(
                LocalTime.of(23, 59)
        );

        appointment.setStatus("SCHEDULED");
        appointment.setNotes("Test update");

        boolean result =
                appointmentService.update(
                        appointment
                );

        assertFalse(
                result,
                "Updating a non-existing appointment should return false"
        );
    }
}

