/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.net.http.HttpResponse;

public class AppointmentApiClientTest {

    public static void main(String[] args)
            throws Exception {

        AppointmentApiClient client =
                new AppointmentApiClient();

        System.out.println(
                "===================================="
        );

        System.out.println(
                " SUNRISE DENTAL APPOINTMENT API TEST"
        );

        System.out.println(
                "====================================");


        // GET ALL

        HttpResponse<String> response =
                client.getAllAppointments();

        System.out.println(
                "GET ALL APPOINTMENTS"
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


        // SEARCH

        // Replace this with an actual
        // appointment number from your database.

        String appointmentNo =
                "APT-0001";

        response =
                client.searchAppointment(
                        appointmentNo
                );

        System.out.println(
                "SEARCH APPOINTMENT"
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
