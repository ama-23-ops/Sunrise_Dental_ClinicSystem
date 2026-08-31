/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {


    // ==========================================
    // TEST 1 - REQUIRED FIELD WITH VALID VALUE
    // ==========================================

    @Test
    public void testRequireWithValidValue() {

        assertDoesNotThrow(() ->
                Validator.require(
                        "John",
                        "Name"
                )
        );
    }


    // ==========================================
    // TEST 2 - REQUIRED FIELD WITH EMPTY VALUE
    // ==========================================

    @Test
    public void testRequireWithEmptyValue() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> Validator.require(
                                "",
                                "Name"
                        )
                );

        assertEquals(
                "Name is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 3 - REQUIRED FIELD WITH NULL
    // ==========================================

    @Test
    public void testRequireWithNullValue() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> Validator.require(
                                null,
                                "Name"
                        )
                );

        assertEquals(
                "Name is required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 4 - VALID PHONE NUMBER
    // ==========================================

    @Test
    public void testValidPhoneNumber() {

        assertTrue(
                Validator.validPhone(
                        "0711234567"
                )
        );
    }


    // ==========================================
    // TEST 5 - INVALID PHONE NUMBER
    // ==========================================

    @Test
    public void testInvalidPhoneNumber() {

        assertFalse(
                Validator.validPhone(
                        "123"
                )
        );
    }


    // ==========================================
    // TEST 6 - NULL PHONE NUMBER
    // ==========================================

    @Test
    public void testNullPhoneNumber() {

        assertFalse(
                Validator.validPhone(null)
        );
    }


    // ==========================================
    // TEST 7 - VALID EMAIL
    // ==========================================

    @Test
    public void testValidEmail() {

        assertTrue(
                Validator.validEmail(
                        "test@gmail.com"
                )
        );
    }


    // ==========================================
    // TEST 8 - INVALID EMAIL
    // ==========================================

    @Test
    public void testInvalidEmail() {

        assertFalse(
                Validator.validEmail(
                        "invalid-email"
                )
        );
    }


    // ==========================================
    // TEST 9 - EMPTY EMAIL ALLOWED
    // ==========================================

    @Test
    public void testEmptyEmailAllowed() {

        assertTrue(
                Validator.validEmail("")
        );
    }


    // ==========================================
    // TEST 10 - VALID FUTURE DATE
    // ==========================================

    @Test
    public void testValidFutureDate() {

        assertTrue(
                Validator.validFutureOrToday(
                        LocalDate.now().plusDays(1)
                )
        );
    }


    // ==========================================
    // TEST 11 - VALID TODAY DATE
    // ==========================================

    @Test
    public void testValidTodayDate() {

        assertTrue(
                Validator.validFutureOrToday(
                        LocalDate.now()
                )
        );
    }


    // ==========================================
    // TEST 12 - INVALID PAST DATE
    // ==========================================

    @Test
    public void testInvalidPastDate() {

        assertFalse(
                Validator.validFutureOrToday(
                        LocalDate.now().minusDays(1)
                )
        );
    }


    // ==========================================
    // TEST 13 - NULL DATE
    // ==========================================

    @Test
    public void testNullDate() {

        assertFalse(
                Validator.validFutureOrToday(null)
        );
    }
}