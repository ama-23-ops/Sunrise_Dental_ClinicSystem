/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public final class RestServer {

    private static final String BASE_URI =
            "http://localhost:8080/api/";

    private RestServer() {
    }

    public static HttpServer start() {

        ResourceConfig config =
                ResourceConfig.forApplication(
                        new ApiApplication()
                );

        HttpServer server =
                GrizzlyHttpServerFactory.createHttpServer(
                        URI.create(BASE_URI),
                        config,
                        false
                );

        try {

            server.start();

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    " SUNRISE DENTAL REST API STARTED"
            );

            System.out.println(
                    " URL: " + BASE_URI
            );

            System.out.println(
                    "======================================"
            );

            return server;

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to start REST API on "
                    + BASE_URI,
                    ex
            );
        }
    }
}
