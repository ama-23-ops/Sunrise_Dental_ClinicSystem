/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.model.User;
import com.sunrisedental.service.AuthService;
import com.sunrisedental.webservice.dto.LoginRequest;
import com.sunrisedental.webservice.dto.LoginResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    private final AuthService authService =
            new AuthService();

    @POST
    @Path("login")
    public Response login(LoginRequest request) {

        try {

            User user =
                    authService.login(
                            request.getUsername(),
                            request.getPassword()
                    );

            if (user == null) {

                return Response
                        .status(
                                Response.Status.UNAUTHORIZED
                        )
                        .entity(
                                new LoginResponse(
                                        false,
                                        "Invalid username or password."
                                )
                        )
                        .build();
            }

            LoginResponse response =
                    new LoginResponse(
                            true,
                            "Login successful."
                    );

            response.setUserId(
                    user.getUserId()
            );

            response.setFullName(
                    user.getFullName()
            );

            response.setRole(
                    user.getRole()
            );

            return Response
                    .ok(response)
                    .build();

        } catch (IllegalArgumentException ex) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            new LoginResponse(
                                    false,
                                    ex.getMessage()
                            )
                    )
                    .build();

        } catch (Exception ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            new LoginResponse(
                                    false,
                                    "Unable to process login."
                            )
                    )
                    .build();
        }
    }
}
