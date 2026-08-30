/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ReportApiClient {

    private final ApiClient apiClient;

    public ReportApiClient() {
        apiClient = new ApiClient();
    }

    // ==========================================
    // DAILY APPOINTMENT REPORT
    // ==========================================

    public HttpResponse<String> getDailyAppointments(
            String date)
            throws IOException, InterruptedException {

        return apiClient.get(
                "reports/daily?date="
                + URLEncoder.encode(
                        date,
                        StandardCharsets.UTF_8
                )
        );
    }
}
