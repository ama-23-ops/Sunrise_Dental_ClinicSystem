/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.client;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AuthApiClient {

    private final ApiClient apiClient;

    public AuthApiClient() {
        apiClient = new ApiClient();
    }

    // ==========================================
    // LOGIN
    // ==========================================

    public HttpResponse<String> login(
            String username,
            String password)
            throws IOException, InterruptedException {

        String json =
                "{"
                + "\"username\":\""
                + escapeJson(username)
                + "\","
                + "\"password\":\""
                + escapeJson(password)
                + "\""
                + "}";

        return apiClient.post(
                "auth/login",
                json
        );
    }


    // ==========================================
    // JSON ESCAPE
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
