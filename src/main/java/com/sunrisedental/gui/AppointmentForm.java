/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.client.AppointmentApiClient;
import com.sunrisedental.client.DentistApiClient;
import com.sunrisedental.client.PatientApiClient;
import com.sunrisedental.client.TreatmentApiClient;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.Patient;
import com.sunrisedental.model.Treatment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrisedental.model.User;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iffah
 */
public class AppointmentForm extends javax.swing.JFrame {
    private User currentUser;
    /**
     * Creates new form AppointmentForm
     */
    public AppointmentForm() {
        initComponents();
}
    
    public AppointmentForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;

    setLocationRelativeTo(null);

    txtAppointmentNo.setEditable(false);

    loadPatients();
    loadDentists();
    loadTreatments();
    loadAppointments();

    cmbStatus.removeAllItems();

    cmbStatus.addItem("SCHEDULED");
    cmbStatus.addItem("COMPLETED");
    cmbStatus.addItem("CANCELLED");
}
    
    private final AppointmentApiClient appointmentApiClient =
        new AppointmentApiClient();

private final PatientApiClient patientApiClient =
        new PatientApiClient();

private final DentistApiClient dentistApiClient =
        new DentistApiClient();

private final TreatmentApiClient treatmentApiClient =
        new TreatmentApiClient();

private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AppointmentForm.class.getName());
    
    private void loadPatients() {

    try {

        HttpResponse<String> response =
                patientApiClient.searchPatients("");

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Load Patients Error"
            );

            return;
        }

        List<Patient> patients =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<Patient>>() {}
                );

        cmbPatient.removeAllItems();

        for (Patient patient : patients) {

            cmbPatient.addItem(
                    patient.getPatientId()
                    + " - "
                    + patient.getFirstName()
                    + " "
                    + patient.getLastName()
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Load Patients Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
   private void loadDentists() {

    try {

        HttpResponse<String> response =
                dentistApiClient.getActiveDentists();

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Load Dentists Error"
            );

            return;
        }

        List<Dentist> dentists =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<Dentist>>() {}
                );

        cmbDentist.removeAllItems();

        for (Dentist dentist : dentists) {

            cmbDentist.addItem(
                    dentist.getDentistId()
                    + " - "
                    + dentist.getDentistName()
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Load Dentists Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   
   private void loadTreatments() {

    try {

        HttpResponse<String> response =
                treatmentApiClient.getActiveTreatments();

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Load Treatments Error"
            );

            return;
        }

        List<Treatment> treatments =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<Treatment>>() {}
                );

        cmbTreatment.removeAllItems();

        for (Treatment treatment : treatments) {

            cmbTreatment.addItem(
                    treatment.getTreatmentId()
                    + " - "
                    + treatment.getTreatmentName()
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Load Treatments Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   
   private void loadAppointments() {

    try {

        HttpResponse<String> response =
                appointmentApiClient
                        .getAllAppointments();

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Load Appointments Error"
            );

            return;
        }

        List<Appointment> appointments =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<Appointment>>() {}
                );

        DefaultTableModel model =
                (DefaultTableModel)
                tblAppointments.getModel();

        model.setRowCount(0);

        for (Appointment appointment :
                appointments) {

            model.addRow(new Object[]{

                appointment.getAppointmentId(),
                appointment.getAppointmentNo(),
                appointment.getPatientId(),
                appointment.getDentistId(),
                appointment.getTreatmentId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                appointment.getNotes()

            });
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Load Appointments Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
   
   //Helper methods for search
   private void selectPatient(int patientId) {

    for (int i = 0; i < cmbPatient.getItemCount(); i++) {

        String item = cmbPatient.getItemAt(i);

        if (item.startsWith(patientId + " - ")) {

            cmbPatient.setSelectedIndex(i);

            break;
        }
    }
}
   
   private void selectDentist(int dentistId) {

    for (int i = 0; i < cmbDentist.getItemCount(); i++) {

        String item = cmbDentist.getItemAt(i);

        if (item.startsWith(dentistId + " - ")) {

            cmbDentist.setSelectedIndex(i);

            break;
        }
    }
}
   
   private void selectTreatment(int treatmentId) {

    for (int i = 0; i < cmbTreatment.getItemCount(); i++) {

        String item = cmbTreatment.getItemAt(i);

        if (item.startsWith(treatmentId + " - ")) {

            cmbTreatment.setSelectedIndex(i);

            break;
        }
    }
}
   
   private int getSelectedId(javax.swing.JComboBox<String> comboBox) {

    String selectedItem =
            comboBox.getSelectedItem().toString();

    String idText =
            selectedItem.split(" - ")[0];

    return Integer.parseInt(idText);
}
   
   private void selectAppointmentRow(
        String appointmentNo) {

    DefaultTableModel model =
            (DefaultTableModel) tblAppointments.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {

        String tableAppointmentNo =
                model.getValueAt(i, 1).toString();

        if (tableAppointmentNo.equals(
                appointmentNo)) {

            tblAppointments.setRowSelectionInterval(
                    i,
                    i
            );

            break;
        }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAppointmentNo = new javax.swing.JTextField();
        txtAppointmentDate = new javax.swing.JTextField();
        cmbPatient = new javax.swing.JComboBox<>();
        cmbDentist = new javax.swing.JComboBox<>();
        cmbTreatment = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        txtAppointmentTime = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        btnSaveAppointment = new javax.swing.JButton();
        btnUpdateAppointment = new javax.swing.JButton();
        btnCancelAppointment = new javax.swing.JButton();
        btnClearAppointment = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        btnSearchAppointment = new javax.swing.JButton();
        txtSearchAppointmentNo = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("APPOINTMENT MANAGEMENT");

        jLabel2.setText("Appointment No:");

        jLabel3.setText("Patient:");

        jLabel4.setText("Dentist: ");

        jLabel5.setText("Treatment:");

        jLabel6.setText("Date:           ");

        jLabel7.setText("Time:");

        jLabel8.setText("Status: ");

        txtAppointmentDate.setText("YYYY-MM-DD");

        cmbPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Patient" }));
        cmbPatient.addActionListener(this::cmbPatientActionPerformed);

        cmbDentist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Dentist" }));

        cmbTreatment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Treatment" }));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SCHEDULED" }));

        txtAppointmentTime.setText("HH:MM");

        jLabel9.setText("Notes: ");

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane1.setViewportView(txtNotes);

        btnSaveAppointment.setText("SAVE");
        btnSaveAppointment.addActionListener(this::btnSaveAppointmentActionPerformed);

        btnUpdateAppointment.setText("UPDATE");
        btnUpdateAppointment.addActionListener(this::btnUpdateAppointmentActionPerformed);

        btnCancelAppointment.setText("CANCEL APPOINTMENT");
        btnCancelAppointment.addActionListener(this::btnCancelAppointmentActionPerformed);

        btnClearAppointment.setText("CLEAR");
        btnClearAppointment.addActionListener(this::btnClearAppointmentActionPerformed);

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment Id", "Appointment No", "Patient", "Dentist", "treatment", "Date", "Time", "Status", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAppointments);

        btnSearchAppointment.setText("SEARCH");
        btnSearchAppointment.addActionListener(this::btnSearchAppointmentActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSaveAppointment)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelAppointment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearAppointment))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAppointmentDate)
                            .addComponent(cmbTreatment, 0, 177, Short.MAX_VALUE)
                            .addComponent(cmbDentist, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPatient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAppointmentNo)
                            .addComponent(cmbStatus, 0, 177, Short.MAX_VALUE)
                            .addComponent(txtAppointmentTime))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchAppointmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchAppointment)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(597, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnBack)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtAppointmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchAppointment)
                            .addComponent(txtSearchAppointmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbDentist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbTreatment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtAppointmentTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveAppointment)
                            .addComponent(btnUpdateAppointment)
                            .addComponent(btnCancelAppointment)
                            .addComponent(btnClearAppointment))))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        if (cmbPatient.getSelectedItem() == null) {

            throw new IllegalArgumentException(
                    "Please select a patient."
            );
        }

        if (cmbDentist.getSelectedItem() == null) {

            throw new IllegalArgumentException(
                    "Please select a dentist."
            );
        }

        if (cmbTreatment.getSelectedItem() == null) {

            throw new IllegalArgumentException(
                    "Please select a treatment."
            );
        }

        String dateText =
                txtAppointmentDate.getText().trim();

        String timeText =
                txtAppointmentTime.getText().trim();

        if (dateText.isEmpty() ||
                dateText.equals("YYYY-MM-DD")) {

            throw new IllegalArgumentException(
                    "Please enter the appointment date."
            );
        }

        if (timeText.isEmpty() ||
                timeText.equals("HH:MM")) {

            throw new IllegalArgumentException(
                    "Please enter the appointment time."
            );
        }

        int patientId =
                getSelectedId(cmbPatient);

        int dentistId =
                getSelectedId(cmbDentist);

        int treatmentId =
                getSelectedId(cmbTreatment);

        Appointment appointment =
                new Appointment();

        appointment.setPatientId(patientId);

        appointment.setDentistId(dentistId);

        appointment.setTreatmentId(treatmentId);

        appointment.setAppointmentDate(
                LocalDate.parse(dateText)
        );

        appointment.setAppointmentTime(
                LocalTime.parse(timeText)
        );

        appointment.setStatus(
                cmbStatus.getSelectedItem()
                        .toString()
        );

        appointment.setNotes(
                txtNotes.getText().trim()
        );

        String json =
                objectMapper.writeValueAsString(
                        appointment
                );

        System.out.println(
                "CREATE APPOINTMENT JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                appointmentApiClient
                        .createAppointment(json);

        if (response.statusCode() == 201) {

            Appointment createdAppointment =
                    objectMapper.readValue(
                            response.body(),
                            Appointment.class
                    );

            txtAppointmentNo.setText(
                    createdAppointment
                            .getAppointmentNo()
            );

            loadAppointments();

            selectAppointmentRow(
                    createdAppointment
                            .getAppointmentNo()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment saved successfully.\n"
                    + "Appointment Number: "
                    + createdAppointment
                            .getAppointmentNo()
            );

        } else {

            showApiError(
                    response,
                    "Appointment Error"
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter the date as YYYY-MM-DD "
                + "and time as HH:MM or HH:MM:SS.",
                "Appointment Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Appointment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSaveAppointmentActionPerformed

    private void btnCancelAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search and select an appointment first."
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel appointment "
                        + appointmentNo + "?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        // Find appointment through REST.

        HttpResponse<String> searchResponse =
                appointmentApiClient
                        .searchAppointment(
                                appointmentNo
                        );

        if (searchResponse.statusCode() != 200) {

            showApiError(
                    searchResponse,
                    "Cancel Error"
            );

            return;
        }

        Appointment appointment =
                objectMapper.readValue(
                        searchResponse.body(),
                        Appointment.class
                );

        // Cancel through REST.

        HttpResponse<String> response =
                appointmentApiClient
                        .cancelAppointment(
                                appointment
                                        .getAppointmentId()
                        );

        if (response.statusCode() == 200) {

            cmbStatus.setSelectedItem(
                    "CANCELLED"
            );

            loadAppointments();

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment cancelled successfully."
            );

        } else {

            showApiError(
                    response,
                    "Cancel Error"
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Cancel Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnCancelAppointmentActionPerformed

    private void cmbPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPatientActionPerformed
        // TODO add your handling code here:      
    }//GEN-LAST:event_cmbPatientActionPerformed

    private void btnUpdateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search for and select an appointment first.",
                    "Update Appointment",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (cmbPatient.getSelectedItem() == null ||
                cmbDentist.getSelectedItem() == null ||
                cmbTreatment.getSelectedItem() == null) {

            throw new IllegalArgumentException(
                    "Please select patient, dentist and treatment."
            );
        }

        String dateText =
                txtAppointmentDate
                        .getText()
                        .trim();

        String timeText =
                txtAppointmentTime
                        .getText()
                        .trim();

        if (dateText.isEmpty() ||
                dateText.equals("YYYY-MM-DD")) {

            throw new IllegalArgumentException(
                    "Please enter the appointment date."
            );
        }

        if (timeText.isEmpty() ||
                timeText.equals("HH:MM")) {

            throw new IllegalArgumentException(
                    "Please enter the appointment time."
            );
        }

        // First retrieve the existing appointment
        // through REST.

        HttpResponse<String> searchResponse =
                appointmentApiClient
                        .searchAppointment(
                                appointmentNo
                        );

        if (searchResponse.statusCode() != 200) {

            showApiError(
                    searchResponse,
                    "Update Appointment Error"
            );

            return;
        }

        Appointment appointment =
                objectMapper.readValue(
                        searchResponse.body(),
                        Appointment.class
                );

        // Update values from the form.

        appointment.setPatientId(
                getSelectedId(cmbPatient)
        );

        appointment.setDentistId(
                getSelectedId(cmbDentist)
        );

        appointment.setTreatmentId(
                getSelectedId(cmbTreatment)
        );

        appointment.setAppointmentDate(
                LocalDate.parse(dateText)
        );

        appointment.setAppointmentTime(
                LocalTime.parse(timeText)
        );

        appointment.setStatus(
                cmbStatus.getSelectedItem()
                        .toString()
        );

        appointment.setNotes(
                txtNotes.getText().trim()
        );

        String json =
                objectMapper.writeValueAsString(
                        appointment
                );

        System.out.println(
                "UPDATE APPOINTMENT JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                appointmentApiClient
                        .updateAppointment(
                                appointment
                                        .getAppointmentId(),
                                json
                        );

        if (response.statusCode() == 200) {

            loadAppointments();

            selectAppointmentRow(
                    appointmentNo
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment updated successfully."
            );

        } else {

            showApiError(
                    response,
                    "Update Appointment Error"
            );
        }

    } catch (
            java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date or time format.",
                "Update Appointment Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Update Appointment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnUpdateAppointmentActionPerformed

    private void btnSearchAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtSearchAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an appointment number.",
                    "Search",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        HttpResponse<String> response =
                appointmentApiClient
                        .searchAppointment(
                                appointmentNo
                        );

        if (response.statusCode() == 404) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found.",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Search Error"
            );

            return;
        }

        Appointment appointment =
                objectMapper.readValue(
                        response.body(),
                        Appointment.class
                );

        txtAppointmentNo.setText(
                appointment.getAppointmentNo()
        );

        txtAppointmentDate.setText(
                appointment.getAppointmentDate()
                        .toString()
        );

        txtAppointmentTime.setText(
                appointment.getAppointmentTime()
                        .toString()
        );

        cmbStatus.setSelectedItem(
                appointment.getStatus()
        );

        txtNotes.setText(
                appointment.getNotes() == null
                        ? ""
                        : appointment.getNotes()
        );

        selectPatient(
                appointment.getPatientId()
        );

        selectDentist(
                appointment.getDentistId()
        );

        selectTreatment(
                appointment.getTreatmentId()
        );

        selectAppointmentRow(
                appointment.getAppointmentNo()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Search Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSearchAppointmentActionPerformed

    private void btnClearAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAppointmentActionPerformed
        // TODO add your handling code here:
        txtAppointmentNo.setText("");
    txtSearchAppointmentNo.setText("");

    txtAppointmentDate.setText("");
    txtAppointmentTime.setText("");
    txtNotes.setText("");

    if (cmbPatient.getItemCount() > 0) {
        cmbPatient.setSelectedIndex(0);
    }

    if (cmbDentist.getItemCount() > 0) {
        cmbDentist.setSelectedIndex(0);
    }

    if (cmbTreatment.getItemCount() > 0) {
        cmbTreatment.setSelectedIndex(0);
    }

    if (cmbStatus.getItemCount() > 0) {
        cmbStatus.setSelectedItem("SCHEDULED");
    }

    tblAppointments.clearSelection();
    }//GEN-LAST:event_btnClearAppointmentActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         goBackToDashboard();
    }//GEN-LAST:event_btnBackActionPerformed

    private void showApiError(
        HttpResponse<String> response,
        String title) {

    JOptionPane.showMessageDialog(
            this,
            "HTTP Status: "
            + response.statusCode()
            + "\n\nServer Response:\n"
            + response.body(),
            title,
            JOptionPane.ERROR_MESSAGE
    );
}
    
    private void goBackToDashboard() {

    DashboardForm dashboard =
            new DashboardForm(currentUser);

    dashboard.setVisible(true);

    this.dispose();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AppointmentForm().setVisible(true));
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelAppointment;
    private javax.swing.JButton btnClearAppointment;
    private javax.swing.JButton btnSaveAppointment;
    private javax.swing.JButton btnSearchAppointment;
    private javax.swing.JButton btnUpdateAppointment;
    private javax.swing.JComboBox<String> cmbDentist;
    private javax.swing.JComboBox<String> cmbPatient;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbTreatment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentNo;
    private javax.swing.JTextField txtAppointmentTime;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtSearchAppointmentNo;
    // End of variables declaration//GEN-END:variables
}
