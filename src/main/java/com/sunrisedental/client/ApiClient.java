package com.sunrisedental.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {

    private static final String BASE_URL =
            "http://localhost:8080/api/";

    private final HttpClient client;

    public ApiClient() {

        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(
                        Duration.ofSeconds(10)
                )
                .build();
    }

    // ==========================================
    // GET
    // ==========================================

    public HttpResponse<String> get(
            String endpoint)
            throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL + endpoint
                                )
                        )
                        .timeout(
                                Duration.ofSeconds(15)
                        )
                        .header(
                                "Accept",
                                "application/json"
                        )
                        .GET()
                        .build();

        return client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }

    // ==========================================
    // POST
    // ==========================================

    public HttpResponse<String> post(
            String endpoint,
            String json)
            throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL + endpoint
                                )
                        )
                        .timeout(
                                Duration.ofSeconds(15)
                        )
                        .header(
                                "Content-Type",
                                "application/json; charset=UTF-8"
                        )
                        .header(
                                "Accept",
                                "application/json"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(
                                                json,
                                                java.nio.charset.StandardCharsets.UTF_8
                                        )
                        )
                        .build();

        return client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }

    // ==========================================
    // PUT
    // ==========================================

    public HttpResponse<String> put(
            String endpoint,
            String json)
            throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL + endpoint
                                )
                        )
                        .timeout(
                                Duration.ofSeconds(15)
                        )
                        .header(
                                "Content-Type",
                                "application/json; charset=UTF-8"
                        )
                        .header(
                                "Accept",
                                "application/json"
                        )
                        .PUT(
                                HttpRequest.BodyPublishers
                                        .ofString(
                                                json,
                                                java.nio.charset.StandardCharsets.UTF_8
                                        )
                        )
                        .build();

        return client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }
}