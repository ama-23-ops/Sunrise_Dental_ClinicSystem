/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.http.HttpResponse;

public class DashboardApiClient {

    private final ApiClient apiClient;

    public DashboardApiClient() {
        apiClient = new ApiClient();
    }

    public HttpResponse<String> getStats()
            throws IOException, InterruptedException {

        return apiClient.get(
                "dashboard/stats"
        );
    }
}
