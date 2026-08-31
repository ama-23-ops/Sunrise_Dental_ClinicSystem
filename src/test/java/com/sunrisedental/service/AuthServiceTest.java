/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sunrisedental.service;

import com.sunrisedental.model.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private final AuthService authService =
            new AuthService();


    // ==========================================
    // TEST 1 - VALID LOGIN
    // ==========================================

    @Test
    public void testValidLogin() throws Exception {

        User result =
                authService.login(
                        "admin",
                        "admin123"
                );

        assertNotNull(
                result,
                "Valid login should return a User"
        );

        assertEquals(
                "admin",
                result.getUsername()
        );
    }


    // ==========================================
    // TEST 2 - INVALID PASSWORD
    // ==========================================

    @Test
    public void testInvalidPassword() throws Exception {

        User result =
                authService.login(
                        "admin",
                        "wrongPassword123"
                );

        assertNull(
                result,
                "Invalid password should return null"
        );
    }


    // ==========================================
    // TEST 3 - NON-EXISTING USER
    // ==========================================

    @Test
    public void testNonExistingUser() throws Exception {

        User result =
                authService.login(
                        "user_that_does_not_exist",
                        "password123"
                );

        assertNull(
                result,
                "Non-existing user should return null"
        );
    }


    // ==========================================
    // TEST 4 - EMPTY USERNAME
    // ==========================================

    @Test
    public void testEmptyUsername() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> authService.login(
                                "",
                                "password123"
                        )
                );

        assertEquals(
                "Username and password are required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 5 - EMPTY PASSWORD
    // ==========================================

    @Test
    public void testEmptyPassword() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> authService.login(
                                "admin",
                                ""
                        )
                );

        assertEquals(
                "Username and password are required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 6 - NULL USERNAME
    // ==========================================

    @Test
    public void testNullUsername() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> authService.login(
                                null,
                                "password123"
                        )
                );

        assertEquals(
                "Username and password are required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 7 - NULL PASSWORD
    // ==========================================

    @Test
    public void testNullPassword() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> authService.login(
                                "admin",
                                null
                        )
                );

        assertEquals(
                "Username and password are required.",
                exception.getMessage()
        );
    }


    // ==========================================
    // TEST 8 - USERNAME WITH SPACES
    // ==========================================

    @Test
    public void testUsernameWithSpaces() throws Exception {

        User result =
                authService.login(
                        "  admin  ",
                        "admin123"
                );

        assertNotNull(
                result,
                "Username should be trimmed before lookup"
        );

        assertEquals(
                "admin",
                result.getUsername()
        );
    }
}