/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.http.HttpResponse;

public class TreatmentApiClient {

    private final ApiClient apiClient;

    public TreatmentApiClient() {
        apiClient = new ApiClient();
    }


    // GET ALL TREATMENTS

    public HttpResponse<String> getAllTreatments()
            throws IOException, InterruptedException {

        return apiClient.get(
                "treatments"
        );
    }


    // GET TREATMENT BY ID

    public HttpResponse<String> getTreatment(
            int treatmentId)
            throws IOException, InterruptedException {

        return apiClient.get(
                "treatments/" + treatmentId
        );
    }


    // GET ACTIVE TREATMENTS

    public HttpResponse<String> getActiveTreatments()
            throws IOException, InterruptedException {

        return apiClient.get(
                "treatments/active"
        );
    }


    // CREATE TREATMENT

    public HttpResponse<String> createTreatment(
            String json)
            throws IOException, InterruptedException {

        return apiClient.post(
                "treatments",
                json
        );
    }


    // UPDATE TREATMENT

    public HttpResponse<String> updateTreatment(
            int treatmentId,
            String json)
            throws IOException, InterruptedException {

        return apiClient.put(
                "treatments/" + treatmentId,
                json
        );
    }
}
