package com.sunrisedental.client;

import java.io.IOException;
import java.net.http.HttpResponse;

public class DentistApiClient {

    private final ApiClient apiClient;

    public DentistApiClient() {
        apiClient = new ApiClient();
    }

    // GET ALL DENTISTS
    public HttpResponse<String> getAllDentists()
            throws IOException, InterruptedException {

        return apiClient.get("dentists");
    }

    // GET DENTIST BY ID
    public HttpResponse<String> getDentist(int dentistId)
            throws IOException, InterruptedException {

        return apiClient.get("dentists/" + dentistId);
    }

    // GET ACTIVE DENTISTS
    public HttpResponse<String> getActiveDentists()
            throws IOException, InterruptedException {

        return apiClient.get("dentists/active");
    }

    // CREATE DENTIST
    public HttpResponse<String> createDentist(String json)
            throws IOException, InterruptedException {

        return apiClient.post("dentists", json);
    }

    // UPDATE DENTIST
    public HttpResponse<String> updateDentist(
            int dentistId,
            String json)
            throws IOException, InterruptedException {

        return apiClient.put(
                "dentists/" + dentistId,
                json
        );
    }
}