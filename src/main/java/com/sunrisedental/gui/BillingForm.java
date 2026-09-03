/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.client.AppointmentApiClient;
import com.sunrisedental.client.BillingApiClient;
import com.sunrisedental.client.PatientApiClient;
import com.sunrisedental.client.TreatmentApiClient;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Bill;
import com.sunrisedental.model.Patient;
import com.sunrisedental.model.Treatment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrisedental.model.User;

import java.math.BigDecimal;
import java.net.http.HttpResponse;

import javax.swing.JOptionPane;

import com.sunrisedental.util.ColorTheme;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author iffah
 */
public class BillingForm extends javax.swing.JFrame {
    private User currentUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BillingForm.class.getName());

    /**
     * Creates new form BillingForm
     */
    public BillingForm() {
        initComponents();
    }
    
    public BillingForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;
    setupBillingUI();

    setLocationRelativeTo(null);
        txtBillNumber.setEditable(false);
        txtPatientName.setEditable(false);
        txtTreatmentName.setEditable(false);
        txtTreatmentFee.setEditable(false);
        txtTotalAmount.setEditable(false);

        cmbPaymentStatus.removeAllItems();

        cmbPaymentStatus.addItem("UNPAID");
        cmbPaymentStatus.addItem("PAID");   

        cmbPaymentMethod.removeAllItems();

        cmbPaymentMethod.addItem("CASH");
        cmbPaymentMethod.addItem("CARD");
        cmbPaymentMethod.addItem("BANK_TRANSFER");
}
    
    private final AppointmentApiClient appointmentApiClient =
        new AppointmentApiClient();

private final PatientApiClient patientApiClient =
        new PatientApiClient();

private final TreatmentApiClient treatmentApiClient =
        new TreatmentApiClient();

private final BillingApiClient billingApiClient =
        new BillingApiClient();

private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
private void setupBillingUI() {
    setTitle("Sunrise Dental Clinic - Billing Management");
    getContentPane().setBackground(ColorTheme.BACKGROUND);

    styleBillingTitle();
    styleBillingLabels();
    styleBillingFields();
    styleBillingButtons();
}

private void styleBillingTitle() {
    jLabel1.setText("BILLING MANAGEMENT");
    jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 26));
    jLabel1.setForeground(ColorTheme.PRIMARY_DARK);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setVerticalAlignment(SwingConstants.CENTER);
}

private void styleBillingLabels() {
    styleBillingLabel(jLabel2);
    styleBillingLabel(jLabel3);
    styleBillingLabel(jLabel4);
    styleBillingLabel(jLabel5);
    styleBillingLabel(jLabel6);
    styleBillingLabel(jLabel7);
    styleBillingLabel(jLabel8);
    styleBillingLabel(jLabel9);
    styleBillingLabel(jLabel10);
}

private void styleBillingLabel(javax.swing.JLabel label) {
    label.setFont(new Font("Segoe UI", Font.BOLD, 13));
    label.setForeground(ColorTheme.TEXT);
}

private void styleBillingFields() {

    styleBillingTextField(txtBillNumber);
    styleBillingTextField(txtSearchAppointmentNo);
    styleBillingTextField(txtPatientName);
    styleBillingTextField(txtTreatmentName);
    styleBillingTextField(txtTreatmentFee);
    styleBillingTextField(txtConsultationFee);
    styleBillingTextField(txtTotalAmount);

    // Generated bill number
    txtBillNumber.setBackground(ColorTheme.LIGHT_TEAL);
    txtBillNumber.setForeground(ColorTheme.SECONDARY_TEXT);

    // Auto-filled fields
    txtPatientName.setBackground(ColorTheme.LIGHT_TEAL);
    txtTreatmentName.setBackground(ColorTheme.LIGHT_TEAL);
    txtTreatmentFee.setBackground(ColorTheme.LIGHT_TEAL);

    // Total amount - highlight it
    txtTotalAmount.setBackground(ColorTheme.LIGHT_TEAL);
    txtTotalAmount.setForeground(ColorTheme.PRIMARY_DARK);
    txtTotalAmount.setFont(new Font("Segoe UI", Font.BOLD, 14));

    // Payment dropdowns
    styleBillingComboBox(cmbPaymentStatus);
    styleBillingComboBox(cmbPaymentMethod);
}

private void styleBillingTextField(JTextField field) {

    field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    field.setForeground(ColorTheme.TEXT);
    field.setBackground(ColorTheme.WHITE);
    field.setCaretColor(ColorTheme.PRIMARY);

    field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ColorTheme.BORDER, 1),
            new EmptyBorder(7, 10, 7, 10)
    ));
}

private void styleBillingComboBox(JComboBox<String> comboBox) {

    comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    comboBox.setForeground(ColorTheme.TEXT);
    comboBox.setBackground(ColorTheme.WHITE);

    comboBox.setBorder(
            BorderFactory.createLineBorder(ColorTheme.BORDER, 1)
    );
}

private void styleBillingButtons() {

    styleBillingButton(
            btnSearchAppointment,
            "SEARCH",
            ColorTheme.PRIMARY,
            ColorTheme.WHITE
    );

    styleBillingButton(
            btnGenerateBill,
            "GENERATE BILL",
            ColorTheme.GOLD,
            ColorTheme.TEXT
    );

    styleBillingButton(
            btnClearBill,
            "CLEAR",
            ColorTheme.SECONDARY_TEXT,
            ColorTheme.WHITE
    );

    styleBillingButton(
            btnBack,
            "←  BACK",
            ColorTheme.PRIMARY_DARK,
            ColorTheme.WHITE
    );
}
private void styleBillingButton(
        JButton button,
        String text,
        Color background,
        Color foreground) {

    button.setText(text);
    button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    button.setForeground(foreground);
    button.setBackground(background);

    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setOpaque(true);

    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    button.setBorder(
            new EmptyBorder(9, 15, 9, 15)
    );
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
        jLabel9 = new javax.swing.JLabel();
        btnSearchAppointment = new javax.swing.JButton();
        btnGenerateBill = new javax.swing.JButton();
        btnClearBill = new javax.swing.JButton();
        txtSearchAppointmentNo = new javax.swing.JTextField();
        txtPatientName = new javax.swing.JTextField();
        txtTreatmentName = new javax.swing.JTextField();
        txtTreatmentFee = new javax.swing.JTextField();
        txtConsultationFee = new javax.swing.JTextField();
        txtTotalAmount = new javax.swing.JTextField();
        cmbPaymentStatus = new javax.swing.JComboBox<>();
        cmbPaymentMethod = new javax.swing.JComboBox<>();
        txtBillNumber = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("BILLING  MANAGEMENT                         ");

        jLabel2.setText("Appointment No:");

        jLabel3.setText("Patient Name:");

        jLabel4.setText("Treatment:");

        jLabel5.setText("Treatment Fee:");

        jLabel6.setText("Consultation:");

        jLabel7.setText("Total Amount: ");

        jLabel8.setText("Payment Status:");

        jLabel9.setText("Payment Method:");

        btnSearchAppointment.setText("SEARCH");
        btnSearchAppointment.addActionListener(this::btnSearchAppointmentActionPerformed);

        btnGenerateBill.setText("GENERATE BILL");
        btnGenerateBill.addActionListener(this::btnGenerateBillActionPerformed);

        btnClearBill.setText("CLEAR");
        btnClearBill.addActionListener(this::btnClearBillActionPerformed);

        txtConsultationFee.addActionListener(this::txtConsultationFeeActionPerformed);
        txtConsultationFee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsultationFeeKeyReleased(evt);
            }
        });

        cmbPaymentStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNPAID", "PAID" }));

        cmbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CARD", "BANK_TRANSFER" }));

        jLabel10.setText("Bill no");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTotalAmount)
                                .addGap(237, 237, 237))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSearchAppointmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSearchAppointment))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtTreatmentFee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 58, Short.MAX_VALUE)))
                        .addGap(94, 94, 94))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtConsultationFee, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGenerateBill)
                                .addGap(38, 38, 38)
                                .addComponent(btnClearBill)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBillNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearchAppointment))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchAppointmentNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtTreatmentFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtConsultationFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cmbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerateBill)
                    .addComponent(btnClearBill))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateBillActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtSearchAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            throw new IllegalArgumentException(
                    "Search an appointment first."
            );
        }


        // ==========================================
        // VALIDATE CONSULTATION FEE
        // ==========================================

        String consultationText =
                txtConsultationFee
                        .getText()
                        .trim();

        if (consultationText.isEmpty()) {

            throw new IllegalArgumentException(
                    "Please enter consultation fee."
            );
        }

        BigDecimal consultationFee =
                new BigDecimal(
                        consultationText
                );

        if (consultationFee.signum() < 0) {

            throw new IllegalArgumentException(
                    "Consultation fee cannot be negative."
            );
        }


        // ==========================================
        // PAYMENT DETAILS
        // ==========================================

        String paymentStatus =
                cmbPaymentStatus
                        .getSelectedItem()
                        .toString();

        String paymentMethod =
                cmbPaymentMethod
                        .getSelectedItem()
                        .toString();


        // ==========================================
        // CREATE BILL THROUGH REST
        // ==========================================

        HttpResponse<String> response =
                billingApiClient.createBill(
                        appointmentNo,
                        consultationFee.toPlainString(),
                        paymentStatus,
                        paymentMethod
                );


        // ==========================================
        // SUCCESS
        // ==========================================

        if (response.statusCode() == 201) {

            Bill bill =
                    objectMapper.readValue(
                            response.body(),
                            Bill.class
                    );

            txtBillNumber.setText(
                    bill.getBillNumber()
            );

            txtTotalAmount.setText(
                    bill.getTotalAmount()
                            .toPlainString()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Bill generated successfully.\n"
                    + "Bill Number: "
                    + bill.getBillNumber()
            );

        } else {

            showApiError(
                    response,
                    "Billing Error"
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Consultation fee must be a valid number.",
                "Billing Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Billing Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnGenerateBillActionPerformed

    private void btnClearBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearBillActionPerformed
        // TODO add your handling code here:
        txtSearchAppointmentNo.setText("");
    txtPatientName.setText("");
    txtTreatmentName.setText("");
    txtTreatmentFee.setText("");
    txtConsultationFee.setText("");
    txtTotalAmount.setText("");
    txtBillNumber.setText("");

    cmbPaymentStatus.setSelectedItem(
            "UNPAID"
    );

    cmbPaymentMethod.setSelectedItem(
            "CASH"
    );
    }//GEN-LAST:event_btnClearBillActionPerformed

    private void btnSearchAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAppointmentActionPerformed
        // TODO add your handling code here:
        try {

        String appointmentNo =
                txtSearchAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            throw new IllegalArgumentException(
                    "Please enter an appointment number."
            );
        }

        // ==========================================
        // 1. SEARCH APPOINTMENT THROUGH REST
        // ==========================================

        HttpResponse<String> appointmentResponse =
                appointmentApiClient
                        .searchAppointment(
                                appointmentNo
                        );

        if (appointmentResponse.statusCode() == 404) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment not found.",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (appointmentResponse.statusCode() != 200) {

            showApiError(
                    appointmentResponse,
                    "Appointment Search Error"
            );

            return;
        }

        Appointment appointment =
                objectMapper.readValue(
                        appointmentResponse.body(),
                        Appointment.class
                );


        // ==========================================
        // 2. GET PATIENT THROUGH REST
        // ==========================================

        HttpResponse<String> patientResponse =
                patientApiClient.getPatient(
                        appointment.getPatientId()
                );

        if (patientResponse.statusCode() != 200) {

            showApiError(
                    patientResponse,
                    "Patient Error"
            );

            return;
        }

        Patient patient =
                objectMapper.readValue(
                        patientResponse.body(),
                        Patient.class
                );


        // ==========================================
        // 3. GET TREATMENT THROUGH REST
        // ==========================================

        HttpResponse<String> treatmentResponse =
                treatmentApiClient.getTreatment(
                        appointment.getTreatmentId()
                );

        if (treatmentResponse.statusCode() != 200) {

            showApiError(
                    treatmentResponse,
                    "Treatment Error"
            );

            return;
        }

        Treatment treatment =
                objectMapper.readValue(
                        treatmentResponse.body(),
                        Treatment.class
                );


        // ==========================================
        // 4. DISPLAY INFORMATION
        // ==========================================

        txtPatientName.setText(
                patient.getFullName()
        );

        txtTreatmentName.setText(
                treatment.getTreatmentName()
        );

        txtTreatmentFee.setText(
                treatment.getTreatmentCost()
                        .toPlainString()
        );


        // ==========================================
        // 5. DEFAULT CONSULTATION FEE
        // ==========================================

        txtConsultationFee.setText(
                "0.00"
        );


        // ==========================================
        // 6. CALCULATE PREVIEW TOTAL
        // ==========================================

        BigDecimal total =
                treatment.getTreatmentCost()
                        .add(
                                new BigDecimal("0.00")
                        )
                        .setScale(
                                2,
                                java.math.RoundingMode.HALF_UP
                        );

        txtTotalAmount.setText(
                total.toPlainString()
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

    private void txtConsultationFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultationFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultationFeeActionPerformed

    private void txtConsultationFeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultationFeeKeyReleased
        // TODO add your handling code here:
        try {

        BigDecimal consultationFee =
                new BigDecimal(
                        txtConsultationFee
                                .getText()
                                .trim()
                );

        BigDecimal treatmentFee =
                new BigDecimal(
                        txtTreatmentFee
                                .getText()
                                .trim()
                );

        BigDecimal total =
                consultationFee
                        .add(treatmentFee)
                        .setScale(
                                2,
                                java.math.RoundingMode.HALF_UP
                        );

        txtTotalAmount.setText(
                total.toPlainString()
        );

    } catch (Exception e) {

        txtTotalAmount.setText("");
    }
    }//GEN-LAST:event_txtConsultationFeeKeyReleased

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
        java.awt.EventQueue.invokeLater(() -> new BillingForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearBill;
    private javax.swing.JButton btnGenerateBill;
    private javax.swing.JButton btnSearchAppointment;
    private javax.swing.JComboBox<String> cmbPaymentMethod;
    private javax.swing.JComboBox<String> cmbPaymentStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBillNumber;
    private javax.swing.JTextField txtConsultationFee;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtSearchAppointmentNo;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtTreatmentFee;
    private javax.swing.JTextField txtTreatmentName;
    // End of variables declaration//GEN-END:variables
}
