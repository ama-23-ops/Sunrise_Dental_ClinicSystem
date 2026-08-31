/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.net.http.HttpResponse;

public class AuthApiClientTest {

    public static void main(String[] args)
            throws Exception {

        AuthApiClient client =
                new AuthApiClient();

        System.out.println(
                "===================================="
        );

        System.out.println(
                " SUNRISE DENTAL LOGIN API TEST"
        );

        System.out.println(
                "===================================="
        );


        // ======================================
        // VALID LOGIN
        // ======================================

        HttpResponse<String> response =
                client.login(
                        "admin",
                        "admin123"
                );

        System.out.println(
                "VALID LOGIN"
        );

        System.out.println(
                "Status Code: "
                + response.statusCode()
        );

        System.out.println(
                "Response:"
        );

        System.out.println(
                response.body()
        );


        System.out.println(
                "===================================="
        );


        // ======================================
        // INVALID LOGIN
        // ======================================

        response =
                client.login(
                        "admin",
                        "wrongpassword"
                );

        System.out.println(
                "INVALID LOGIN"
        );

        System.out.println(
                "Status Code: "
                + response.statusCode()
        );

        System.out.println(
                "Response:"
        );

        System.out.println(
                response.body()
        );


        System.out.println(
                "===================================="
        );
    }
}
