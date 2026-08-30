/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.report.AppointmentDetails;
import com.sunrisedental.report.ReportDAO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Path("reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

    private final ReportDAO reportDAO =
            new ReportDAO();

    // ==========================================
    // DAILY APPOINTMENT REPORT
    //
    // GET /api/reports/daily?date=2026-08-30
    // ==========================================

    @GET
    @Path("daily")
    public Response dailyAppointments(
            @QueryParam("date") String date) {

        try {

            if (date == null ||
                    date.trim().isEmpty()) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Report date is required\"}"
                        )
                        .build();
            }

            LocalDate reportDate;

            try {

                reportDate =
                        LocalDate.parse(
                                date.trim()
                        );

            } catch (
                    java.time.format.DateTimeParseException ex) {

                return Response
                        .status(
                                Response.Status.BAD_REQUEST
                        )
                        .entity(
                                "{\"error\":\"Date must be in YYYY-MM-DD format\"}"
                        )
                        .build();
            }

            List<AppointmentDetails> appointments =
                    reportDAO.dailyAppointments(
                            reportDate
                    );

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
}
