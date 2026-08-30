/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental;

import com.sunrisedental.webservice.RestServer;
import org.glassfish.grizzly.http.server.HttpServer;

public class RestApiApp {

    public static void main(String[] args)
            throws Exception {

        HttpServer server =
                RestServer.start();

        System.out.println();
        System.out.println(
                "Press Enter to stop the REST API..."
        );

        System.in.read();

        server.shutdownNow();

        System.out.println(
                "REST API stopped."
        );
    }
}