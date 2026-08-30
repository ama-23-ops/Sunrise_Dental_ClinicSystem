/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.webservice;

import com.sunrisedental.dao.AppointmentDAO;
import com.sunrisedental.dao.BillDAO;
import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.model.DashboardStats;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    private final PatientDAO patientDAO =
            new PatientDAO();

    private final AppointmentDAO appointmentDAO =
            new AppointmentDAO();

    private final BillDAO billDAO =
            new BillDAO();


    // ==========================================
    // GET DASHBOARD STATISTICS
    //
    // GET /api/dashboard/stats
    // ==========================================

    @GET
    @Path("stats")
    public Response getDashboardStats() {

        try {

            DashboardStats stats =
                    new DashboardStats();

            // Total patients

            stats.setTotalPatients(
                    patientDAO.countPatients()
            );


            // Today's appointments

            stats.setTodayAppointments(
                    appointmentDAO
                            .countTodayAppointments()
            );


            // Today's revenue

            stats.setTodayRevenue(
                    billDAO.getTodayRevenue()
            );


            return Response
                    .ok(stats)
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
