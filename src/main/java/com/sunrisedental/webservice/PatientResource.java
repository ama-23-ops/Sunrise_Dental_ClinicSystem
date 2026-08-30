package com.sunrisedental.webservice;

import com.sunrisedental.model.Patient;
import com.sunrisedental.service.PatientService;

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

@Path("patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    private final PatientService patientService =
            new PatientService();


    // ==========================================
    // GET PATIENT BY ID
    // GET /api/patients/{id}
    // ==========================================

    @GET
    @Path("{id}")
    public Response getPatientById(
            @PathParam("id") int id) {

        try {

            Patient patient =
                    patientService.findById(id);

            if (patient == null) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Patient not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok(patient)
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
    // SEARCH PATIENTS
    //
    // GET /api/patients/search?q=John
    // GET /api/patients/search?q=
    //
    // Empty query = return all patients
    // ==========================================

    @GET
    @Path("search")
    public Response searchPatients(
            @QueryParam("q") String query) {

        try {

            if (query == null) {
                query = "";
            }

            List<Patient> patients =
                    patientService.searchByName(
                            query.trim()
                    );

            return Response
                    .ok(patients)
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
    // CREATE PATIENT
    //
    // POST /api/patients
    // ==========================================

    @POST
    public Response createPatient(
            Patient patient) {

        try {

            int id =
                    patientService.save(patient);

            if (id <= 0) {

                return Response
                        .serverError()
                        .entity(
                                "{\"error\":\"Patient could not be created\"}"
                        )
                        .build();
            }

            return Response
                    .status(
                            Response.Status.CREATED
                    )
                    .entity(patient)
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
    // UPDATE PATIENT
    //
    // PUT /api/patients/{id}
    // ==========================================

    @PUT
    @Path("{id}")
    public Response updatePatient(
            @PathParam("id") int id,
            Patient patient) {

        try {

            patient.setPatientId(id);

            boolean updated =
                    patientService.update(patient);

            if (!updated) {

                return Response
                        .status(
                                Response.Status.NOT_FOUND
                        )
                        .entity(
                                "{\"error\":\"Patient not found\"}"
                        )
                        .build();
            }

            return Response
                    .ok(patient)
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