/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.dao.AppointmentDAO;
import com.sunrisedental.dao.DentistDAO;
import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.dao.TreatmentDAO;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.Patient;
import com.sunrisedental.model.Treatment;

import com.sunrisedental.service.AppointmentService;

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
    
    /**
     * Creates new form AppointmentForm
     */
    public AppointmentForm() {
        initComponents();
 
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
    
    private final AppointmentDAO appointmentDAO =
        new AppointmentDAO();

    private final AppointmentService appointmentService =
        new AppointmentService();

    private final PatientDAO patientDAO =
        new PatientDAO();

    private final DentistDAO dentistDAO =
        new DentistDAO();

    private final TreatmentDAO treatmentDAO =
        new TreatmentDAO();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AppointmentForm.class.getName());
    
    private void loadPatients() {

    try {

        List<Patient> patients =
                patientDAO.searchByName("");

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
                e.getMessage()
        );
    }
}
    
   private void loadDentists() {

    try {

        List<Dentist> dentists =
                dentistDAO.findActive();

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
                e.getMessage()
        );
    }
}
   
   private void loadTreatments() {

    try {

        List<Treatment> treatments =
                treatmentDAO.findActive();

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
                e.getMessage()
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
   
   private void loadAppointments() {

    try {

        List<Appointment> appointments =
                appointmentDAO.findAll();

        DefaultTableModel model =
                (DefaultTableModel) tblAppointments.getModel();

        model.setRowCount(0);

        for (Appointment appointment : appointments) {

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
                "Error loading appointments: "
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
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

        // Validate selections
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

        if (txtAppointmentDate.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter the appointment date."
            );
        }

        if (txtAppointmentTime.getText().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Please enter the appointment time."
            );
        }

        // Get IDs from ComboBoxes
        int patientId = getSelectedId(cmbPatient);
        int dentistId = getSelectedId(cmbDentist);
        int treatmentId = getSelectedId(cmbTreatment);

        // Create appointment object
        Appointment appointment = new Appointment();

        appointment.setPatientId(patientId);
        appointment.setDentistId(dentistId);
        appointment.setTreatmentId(treatmentId);

        appointment.setAppointmentDate(
                LocalDate.parse(
                        txtAppointmentDate.getText().trim()
                )
        );

        appointment.setAppointmentTime(
                LocalTime.parse(
                        txtAppointmentTime.getText().trim()
                )
        );

        appointment.setStatus(
                cmbStatus.getSelectedItem().toString()
        );

        appointment.setNotes(
                txtNotes.getText().trim()
        );

        // Save to database
        appointmentService.create(appointment);

        // Display generated appointment number
        txtAppointmentNo.setText(
                appointment.getAppointmentNo()
        );

        // Refresh table
        loadAppointments();

        // Select saved appointment in table
        selectAppointmentRow(
                appointment.getAppointmentNo()
        );

        JOptionPane.showMessageDialog(
                this,
                "Appointment saved successfully.\n"
                + "Appointment Number: "
                + appointment.getAppointmentNo()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Appointment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSaveAppointmentActionPerformed

    private void btnCancelAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtAppointmentNo.getText().trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search and select an appointment first."
            );

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel appointment "
                + appointmentNo + "?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        Appointment appointment =
                appointmentDAO.findByNo(appointmentNo);

        if (appointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found."
            );

            return;
        }

        appointmentService.cancel(
                appointment.getAppointmentId()
        );

        cmbStatus.setSelectedItem("CANCELLED");

        loadAppointments();

        JOptionPane.showMessageDialog(
                this,
                "Appointment cancelled successfully."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
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

        // Update requires an already saved appointment
        String appointmentNo =
                txtAppointmentNo.getText().trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search for and select an appointment first."
            );

            return;
        }

        // Get the appointment from database
        Appointment appointment =
                appointmentDAO.findByNo(appointmentNo);

        if (appointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found."
            );

            return;
        }

        // Validate ComboBox selections
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

        // Get IDs from ComboBoxes
        int patientId = getSelectedId(cmbPatient);
        int dentistId = getSelectedId(cmbDentist);
        int treatmentId = getSelectedId(cmbTreatment);

        // Update appointment object
        appointment.setPatientId(patientId);
        appointment.setDentistId(dentistId);
        appointment.setTreatmentId(treatmentId);

        appointment.setAppointmentDate(
                LocalDate.parse(
                        txtAppointmentDate.getText().trim()
                )
        );

        appointment.setAppointmentTime(
                LocalTime.parse(
                        txtAppointmentTime.getText().trim()
                )
        );

        appointment.setStatus(
                cmbStatus.getSelectedItem().toString()
        );

        appointment.setNotes(
                txtNotes.getText().trim()
        );

        // Update database
        appointmentService.update(appointment);

        // Refresh table
        loadAppointments();

        // Select updated row
        selectAppointmentRow(appointmentNo);

        JOptionPane.showMessageDialog(
                this,
                "Appointment updated successfully."
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage(),
                "Update Error",
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

        Appointment appointment =
                appointmentDAO.findByNo(
                        appointmentNo
                );

        if (appointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found."
            );

            return;
        }

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
                appointment.getNotes()
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

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage()
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
