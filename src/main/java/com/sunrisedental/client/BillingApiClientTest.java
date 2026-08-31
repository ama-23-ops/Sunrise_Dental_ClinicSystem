/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.net.http.HttpResponse;

public class BillingApiClientTest {

    public static void main(String[] args)
            throws Exception {

        BillingApiClient client =
                new BillingApiClient();

        System.out.println(
                "===================================="
        );

        System.out.println(
                " SUNRISE DENTAL BILLING API TEST"
        );

        System.out.println(
                "===================================="
        );


        // ======================================
        // TEST GET BILL
        // ======================================

        int appointmentId = 1;

        HttpResponse<String> response =
                client.getBillByAppointment(
                        appointmentId
                );

        System.out.println(
                "GET BILL BY APPOINTMENT"
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
        // CREATE BILL
        // ======================================

        /*
         * Use an appointment number that
         * does NOT already have a bill.
         *
         * Example:
         *
         * String appointmentNo = "APT-0002";
         *
         * Uncomment only when you have an
         * appointment without an existing bill.
         */

        /*
        response =
                client.createBill(
                        "APT-0002",
                        "1000.00",
                        "PAID",
                        "CASH"
                );

        System.out.println(
                "CREATE BILL"
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
        */


        System.out.println(
                "===================================="
        );
    }
}
