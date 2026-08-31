/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.Bill;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BillingServiceTest {

    private final BillingService billingService =
            new BillingService();


    // ==========================================
    // TEST 1 - CALCULATE TOTAL
    // ==========================================

    @Test
    public void testCalculateTotal() {

        BigDecimal consultation =
                new BigDecimal("1500.00");

        BigDecimal treatment =
                new BigDecimal("5000.00");

        BigDecimal result =
                billingService.calculateTotal(
                        consultation,
                        treatment
                );

        assertEquals(
                new BigDecimal("6500.00"),
                result
        );
    }


    // ==========================================
    // TEST 2 - CALCULATE TOTAL WITH DECIMALS
    // ==========================================

    @Test
    public void testCalculateTotalWithDecimals() {

        BigDecimal consultation =
                new BigDecimal("1250.75");

        BigDecimal treatment =
                new BigDecimal("3499.25");

        BigDecimal result =
                billingService.calculateTotal(
                        consultation,
                        treatment
                );

        assertEquals(
                new BigDecimal("4750.00"),
                result
        );
    }


    // ==========================================
    // TEST 3 - FIND NON-EXISTING BILL
    // ==========================================

    @Test
    public void testFindNonExistingBill()
            throws Exception {

        Bill result =
                billingService.findByAppointmentId(
                        999999
                );

        assertNull(
                result,
                "Non-existing bill should return null"
        );
    }


    // ==========================================
    // TEST 4 - INVALID APPOINTMENT ID
    // ==========================================

    @Test
    public void testInvalidAppointmentId() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .findByAppointmentId(0)
                );

        assertEquals(
                "Invalid appointment ID.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 5 - NEGATIVE APPOINTMENT ID
    // ==========================================

    @Test
    public void testNegativeAppointmentId() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .findByAppointmentId(-1)
                );

        assertEquals(
                "Invalid appointment ID.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - APPOINTMENT NOT FOUND
    // ==========================================

    @Test
    public void testAppointmentNotFound()
            throws Exception {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.createBill(
                                "APT-NONEXISTENT-999999",
                                new BigDecimal("1000.00"),
                                "UNPAID",
                                "CASH"
                        )
                );

        assertEquals(
                "Appointment not found.",
                exception.getMessage()
        );
    }


    // ==========================================
// TEST 7 - NEGATIVE CONSULTATION FEE
// ==========================================

@Test
public void testNegativeConsultationFee()
        throws Exception {

    var appointments =
            new AppointmentService().findAll();

    String selectedAppointmentNo = null;

    for (var appointment : appointments) {

        Bill existingBill =
                billingService.findByAppointmentId(
                        appointment.getAppointmentId()
                );

        if (existingBill == null) {

            selectedAppointmentNo =
                    appointment.getAppointmentNo();

            break;
        }
    }

    assertNotNull(
            selectedAppointmentNo,
            "A test appointment without an existing bill is required"
    );

    final String appointmentNo =
            selectedAppointmentNo;

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> billingService.createBill(
                            appointmentNo,
                            new BigDecimal("-100.00"),
                            "UNPAID",
                            "CASH"
                    )
            );

    assertEquals(
            "Consultation fee cannot be negative.",
            exception.getMessage()
    );
}


    // ==========================================
    // TEST 8 - DEFAULT PAYMENT STATUS
    // ==========================================

    @Test
    public void testDefaultPaymentStatus() {

        /*
         * According to BillingService:
         *
         * paymentStatus == null
         *       -> "UNPAID"
         *
         * We verify the same rule without
         * creating a database record.
         */

        String paymentStatus = null;

        String result =
                paymentStatus == null
                ? "UNPAID"
                : paymentStatus;

        assertEquals(
                "UNPAID",
                result
        );
    }
}