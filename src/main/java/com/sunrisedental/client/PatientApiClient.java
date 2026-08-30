/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.http.HttpResponse;

public class PatientApiClient {

    private final ApiClient apiClient;

    public PatientApiClient() {
        apiClient = new ApiClient();
    }

    public HttpResponse<String> getPatient(
            int patientId)
            throws IOException, InterruptedException {

        return apiClient.get(
                "patients/" + patientId
        );
    }

    public HttpResponse<String> searchPatients(
            String query)
            throws IOException, InterruptedException {

        return apiClient.get(
                "patients/search?q="
                + java.net.URLEncoder.encode(
                        query,
                        java.nio.charset.StandardCharsets.UTF_8
                )
        );
    }

    public HttpResponse<String> createPatient(
            String json)
            throws IOException, InterruptedException {

        return apiClient.post(
                "patients",
                json
        );
    }

    public HttpResponse<String> updatePatient(
            int patientId,
            String json)
            throws IOException, InterruptedException {

        return apiClient.put(
                "patients/" + patientId,
                json
        );
    }
}