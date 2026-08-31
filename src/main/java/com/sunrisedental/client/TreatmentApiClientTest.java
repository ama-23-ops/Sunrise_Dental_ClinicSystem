/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.net.http.HttpResponse;

public class TreatmentApiClientTest {

    public static void main(String[] args)
            throws Exception {

        TreatmentApiClient client =
                new TreatmentApiClient();

        System.out.println(
                "===================================="
        );

        System.out.println(
                " SUNRISE DENTAL TREATMENT API TEST"
        );

        System.out.println(
                "===================================="
        );

        HttpResponse<String> response =
                client.getAllTreatments();

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
