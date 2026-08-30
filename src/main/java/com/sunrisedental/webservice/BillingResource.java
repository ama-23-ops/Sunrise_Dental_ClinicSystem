/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.model.Bill;
import com.sunrisedental.service.BillingService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.sql.SQLException;

@Path("bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {

    private final BillingService billingService =
            new BillingService();


    // ==========================================
    // GET BILL BY APPOINTMENT ID
    //
    // GET /api/bills/appointment/{appointmentId}
    // ==========================================

    @GET
    @Path("appointment/{appointmentId}")
    public Response getBillByAppointment(
            @PathParam("appointmentId")
            int appointmentId) {

        try {

            if (appointmentId <= 0) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Invalid appointment ID\"}"
                        )
                        .build();
            }

            /*
             * BillingService currently does not expose
             * findBillByAppointmentId().
             *
             * Therefore this endpoint will be added
             * through a small service method below.
             */

            Bill bill =
                    billingService.findByAppointmentId(
                            appointmentId
                    );

            if (bill == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Bill not found for this appointment\"}"
                        )
                        .build();
            }

            return Response
                    .ok(bill)
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
    // CREATE BILL
    //
    // POST /api/bills
    // ==========================================

    @POST
    public Response createBill(
            BillRequest request) {

        try {

            if (request == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Bill request is required\"}"
                        )
                        .build();
            }

            if (request.appointmentNo == null ||
                    request.appointmentNo.trim().isEmpty()) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Appointment number is required\"}"
                        )
                        .build();
            }

            if (request.consultationFee == null) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Consultation fee is required\"}"
                        )
                        .build();
            }

            Bill bill =
                    billingService.createBill(
                            request.appointmentNo.trim(),
                            request.consultationFee,
                            request.paymentStatus,
                            request.paymentMethod
                    );

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(bill)
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
    // REQUEST DTO
    // ==========================================

    public static class BillRequest {

        public String appointmentNo;

        public BigDecimal consultationFee;

        public String paymentStatus;

        public String paymentMethod;

        public BillRequest() {
        }
    }
}
