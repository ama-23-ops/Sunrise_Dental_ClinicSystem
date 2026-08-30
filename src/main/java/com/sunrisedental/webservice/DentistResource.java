/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.model.Dentist;
import com.sunrisedental.service.DentistService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("dentists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DentistResource {

    private final DentistService dentistService =
            new DentistService();


    // ==========================================
    // GET DENTIST BY ID
    //
    // GET /api/dentists/{id}
    // ==========================================

    @GET
    @Path("{id}")
    public Response getDentistById(
            @PathParam("id") int id) {

        try {

            Dentist dentist =
                    dentistService.findById(id);

            if (dentist == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Dentist not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok(dentist)
                    .build();

        } catch (SQLException ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"error\":\"Database error\"}"
                    )
                    .build();
        }
    }


    // ==========================================
    // GET ALL DENTISTS
    //
    // GET /api/dentists
    // ==========================================

    @GET
    public Response getAllDentists() {

        try {

            List<Dentist> dentists =
                    dentistService.findAll();

            return Response
                    .ok(dentists)
                    .build();

        } catch (SQLException ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"error\":\"Database error\"}"
                    )
                    .build();
        }
    }


    // ==========================================
    // GET ACTIVE DENTISTS
    //
    // GET /api/dentists/active
    // ==========================================

    @GET
    @Path("active")
    public Response getActiveDentists() {

        try {

            List<Dentist> dentists =
                    dentistService.findActive();

            return Response
                    .ok(dentists)
                    .build();

        } catch (SQLException ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"error\":\"Database error\"}"
                    )
                    .build();
        }
    }


    // ==========================================
    // CREATE DENTIST
    //
    // POST /api/dentists
    // ==========================================

    @POST
    public Response createDentist(
            Dentist dentist) {

        try {

            int id =
                    dentistService.save(dentist);

            if (id <= 0) {

                return Response
                        .serverError()
                        .entity(
                                "{\"error\":\"Dentist could not be created\"}"
                        )
                        .build();
            }

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(dentist)
                    .build();

        } catch (IllegalArgumentException ex) {

            return Response
                    .status(
                            Response.Status.BAD_REQUEST
                    )
                    .entity(
                            "{\"error\":\""
                            + ex.getMessage()
                            + "\"}"
                    )
                    .build();

        } catch (SQLException ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"error\":\"Database error\"}"
                    )
                    .build();
        }
    }


    // ==========================================
    // UPDATE DENTIST
    //
    // PUT /api/dentists/{id}
    // ==========================================

    @PUT
    @Path("{id}")
    public Response updateDentist(
            @PathParam("id") int id,
            Dentist dentist) {

        try {

            dentist.setDentistId(id);

            boolean updated =
                    dentistService.update(dentist);

            if (!updated) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Dentist not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok()
                    .entity(
                            "{\"message\":\"Dentist updated successfully\"}"
                    )
                    .build();

        } catch (SQLException ex) {

            ex.printStackTrace();

            return Response
                    .serverError()
                    .entity(
                            "{\"error\":\"Database error\"}"
                    )
                    .build();
        }
    }
}
