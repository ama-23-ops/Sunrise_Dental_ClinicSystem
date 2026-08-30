/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.service.AppointmentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {

    private final AppointmentService appointmentService =
            new AppointmentService();


    // ==========================================
    // GET ALL APPOINTMENTS
    //
    // GET /api/appointments
    // ==========================================

    @GET
    public Response getAllAppointments() {

        try {

            List<Appointment> appointments =
                    appointmentService.findAll();

            return Response
                    .ok(appointments)
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
    // SEARCH BY APPOINTMENT NUMBER
    //
    // GET /api/appointments/search?no=APT001
    // ==========================================

    @GET
    @Path("search")
    public Response searchAppointment(
            @QueryParam("no") String appointmentNo) {

        try {

            if (appointmentNo == null ||
                    appointmentNo.trim().isEmpty()) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Appointment number is required\"}"
                        )
                        .build();
            }

            Appointment appointment =
                    appointmentService.findByNo(
                            appointmentNo.trim()
                    );

            if (appointment == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Appointment not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok(appointment)
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
    // CREATE APPOINTMENT
    //
    // POST /api/appointments
    // ==========================================

    @POST
    public Response createAppointment(
            Appointment appointment) {

        try {

            int id =
                    appointmentService.create(
                            appointment
                    );

            if (id <= 0) {

                return Response
                        .serverError()
                        .entity(
                                "{\"error\":\"Appointment could not be created\"}"
                        )
                        .build();
            }

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(appointment)
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
    // UPDATE APPOINTMENT
    //
    // PUT /api/appointments/{id}
    // ==========================================

    @PUT
    @Path("{id}")
    public Response updateAppointment(
            @PathParam("id") int id,
            Appointment appointment) {

        try {

            appointment.setAppointmentId(id);

            boolean updated =
                    appointmentService.update(
                            appointment
                    );

            if (!updated) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Appointment not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok()
                    .entity(
                            "{\"message\":\"Appointment updated successfully\"}"
                    )
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
    // CANCEL APPOINTMENT
    //
    // PUT /api/appointments/{id}/cancel
    // ==========================================

    @PUT
    @Path("{id}/cancel")
    public Response cancelAppointment(
            @PathParam("id") int id) {

        try {

            boolean cancelled =
                    appointmentService.cancel(id);

            if (!cancelled) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Appointment not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok()
                    .entity(
                            "{\"message\":\"Appointment cancelled successfully\"}"
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
