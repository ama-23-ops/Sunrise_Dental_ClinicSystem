/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.net.http.HttpResponse;

public class ReportApiClientTest {

    public static void main(String[] args)
            throws Exception {

        ReportApiClient client =
                new ReportApiClient();

        System.out.println(
                "===================================="
        );

        System.out.println(
                " SUNRISE DENTAL REPORT API TEST"
        );

        System.out.println(
                "===================================="
        );

        String date =
                "2026-08-30";

        HttpResponse<String> response =
                client.getDailyAppointments(
                        date
                );

        System.out.println(
                "DAILY APPOINTMENT REPORT"
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
