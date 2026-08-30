/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class AppointmentApiClient {

    private final ApiClient apiClient;

    public AppointmentApiClient() {
        apiClient = new ApiClient();
    }

    // ==========================================
    // GET ALL APPOINTMENTS
    // ==========================================

    public HttpResponse<String> getAllAppointments()
            throws IOException, InterruptedException {

        return apiClient.get(
                "appointments"
        );
    }


    // ==========================================
    // SEARCH BY APPOINTMENT NUMBER
    // ==========================================

    public HttpResponse<String> searchAppointment(
            String appointmentNo)
            throws IOException, InterruptedException {

        return apiClient.get(
                "appointments/search?no="
                + URLEncoder.encode(
                        appointmentNo,
                        StandardCharsets.UTF_8
                )
        );
    }


    // ==========================================
    // CREATE APPOINTMENT
    // ==========================================

    public HttpResponse<String> createAppointment(
            String json)
            throws IOException, InterruptedException {

        return apiClient.post(
                "appointments",
                json
        );
    }


    // ==========================================
    // UPDATE APPOINTMENT
    // ==========================================

    public HttpResponse<String> updateAppointment(
            int appointmentId,
            String json)
            throws IOException, InterruptedException {

        return apiClient.put(
                "appointments/" + appointmentId,
                json
        );
    }


    // ==========================================
    // CANCEL APPOINTMENT
    // ==========================================

    public HttpResponse<String> cancelAppointment(
            int appointmentId)
            throws IOException, InterruptedException {

        return apiClient.put(
                "appointments/"
                + appointmentId
                + "/cancel",
                ""
        );
    }
}
