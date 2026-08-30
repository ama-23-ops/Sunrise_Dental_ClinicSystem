/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class BillingApiClient {

    private final ApiClient apiClient;

    public BillingApiClient() {
        apiClient = new ApiClient();
    }

    // ==========================================
    // GET BILL BY APPOINTMENT ID
    // ==========================================

    public HttpResponse<String> getBillByAppointment(
            int appointmentId)
            throws IOException, InterruptedException {

        return apiClient.get(
                "bills/appointment/" + appointmentId
        );
    }


    // ==========================================
    // CREATE BILL
    // ==========================================

    public HttpResponse<String> createBill(
            String appointmentNo,
            String consultationFee,
            String paymentStatus,
            String paymentMethod)
            throws IOException, InterruptedException {

        String json =
                "{"
                + "\"appointmentNo\":\""
                + escapeJson(appointmentNo)
                + "\","
                + "\"consultationFee\":"
                + consultationFee
                + ","
                + "\"paymentStatus\":\""
                + escapeJson(paymentStatus)
                + "\","
                + "\"paymentMethod\":\""
                + escapeJson(paymentMethod)
                + "\""
                + "}";

        return apiClient.post(
                "bills",
                json
        );
    }


    // ==========================================
    // JSON ESCAPE HELPER
    // ==========================================

    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}