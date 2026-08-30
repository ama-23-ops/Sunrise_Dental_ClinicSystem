/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.model.Treatment;
import com.sunrisedental.service.TreatmentService;

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

@Path("treatments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreatmentResource {

    private final TreatmentService treatmentService =
            new TreatmentService();


    // ==========================================
    // GET ALL TREATMENTS
    //
    // GET /api/treatments
    // ==========================================

    @GET
    public Response getAllTreatments() {

        try {

            List<Treatment> treatments =
                    treatmentService.findAll();

            return Response
                    .ok(treatments)
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
    // GET ACTIVE TREATMENTS
    //
    // GET /api/treatments/active
    // ==========================================

    @GET
    @Path("active")
    public Response getActiveTreatments() {

        try {

            List<Treatment> treatments =
                    treatmentService.findActive();

            return Response
                    .ok(treatments)
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
    // GET TREATMENT BY ID
    //
    // GET /api/treatments/{id}
    // ==========================================

    @GET
    @Path("{id}")
    public Response getTreatmentById(
            @PathParam("id") int id) {

        try {

            Treatment treatment =
                    treatmentService.findById(id);

            if (treatment == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Treatment not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok(treatment)
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
    // CREATE TREATMENT
    //
    // POST /api/treatments
    // ==========================================

    @POST
    public Response createTreatment(
            Treatment treatment) {

        try {

            int id =
                    treatmentService.save(
                            treatment
                    );

            if (id <= 0) {

                return Response
                        .serverError()
                        .entity(
                                "{\"error\":\"Treatment could not be created\"}"
                        )
                        .build();
            }

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(treatment)
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
    // UPDATE TREATMENT
    //
    // PUT /api/treatments/{id}
    // ==========================================

    @PUT
    @Path("{id}")
    public Response updateTreatment(
            @PathParam("id") int id,
            Treatment treatment) {

        try {

            treatment.setTreatmentId(id);

            boolean updated =
                    treatmentService.update(
                            treatment
                    );

            if (!updated) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Treatment not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok()
                    .entity(
                            "{\"message\":\"Treatment updated successfully\"}"
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