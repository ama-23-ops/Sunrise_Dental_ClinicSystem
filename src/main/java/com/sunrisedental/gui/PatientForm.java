/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrisedental.client.PatientApiClient;
import com.sunrisedental.model.Patient;
import com.sunrisedental.model.User;

import java.net.http.HttpResponse;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.sunrisedental.util.ColorTheme;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author iffah
 */
public class PatientForm extends javax.swing.JFrame {
    private User currentUser;
    private final PatientApiClient patientApiClient =
        new PatientApiClient();

private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PatientForm.class.getName());

    /**
     * Creates new form PatientForm
     */
    public PatientForm() {
        initComponents();
    }

    public PatientForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;
    
    setupPatientUI();
    
    setLocationRelativeTo(null);

    txtPatientId.setEditable(false);

    loadPatients();
}
    
    private void setupPatientUI() {

    setTitle(
        "Sunrise Dental Clinic - Patient Management"
    );

    getContentPane().setBackground(
        ColorTheme.BACKGROUND
    );

    stylePatientLabels();

    stylePatientFields();

    stylePatientButtons();

    stylePatientTable();

    styleSearchArea();
    
    stylePageTitle();

}
    private void stylePageTitle() {

    jLabel11.setText(
        "PATIENT MANAGEMENT"
    );

    jLabel11.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            26
        )
    );

    jLabel11.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    jLabel11.setHorizontalAlignment(
        SwingConstants.CENTER
    );

    jLabel11.setVerticalAlignment(
        SwingConstants.CENTER
    );
}
    
    private void stylePatientLabels() {

    // Main section title
    jLabel2.setText(
        "Patient Details"
    );

    jLabel2.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            20
        )
    );

    jLabel2.setForeground(
        ColorTheme.PRIMARY_DARK
    );


    // Search label
    jLabel1.setText(
        "Search Patient"
    );

    jLabel1.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            13
        )
    );

    jLabel1.setForeground(
        ColorTheme.TEXT
    );


    // Form labels
    styleFormLabel(jLabel3);
    styleFormLabel(jLabel4);
    styleFormLabel(jLabel5);
    styleFormLabel(jLabel6);
    styleFormLabel(jLabel7);
    styleFormLabel(jLabel8);
    styleFormLabel(jLabel9);
    styleFormLabel(jLabel10);
}
    
    private void styleFormLabel(
        javax.swing.JLabel label) {

    label.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            13
        )
    );

    label.setForeground(
        ColorTheme.TEXT
    );
}
    
    private void stylePatientFields() {

    styleTextField(txtPatientId);
    styleTextField(txtFirstName);
    styleTextField(txtLastName);
    styleTextField(txtContact);
    styleTextField(txtEmail);
    styleTextField(txtDateOfBirth);

    txtPatientId.setBackground(
        ColorTheme.LIGHT_TEAL
    );

    txtPatientId.setForeground(
        ColorTheme.SECONDARY_TEXT
    );


    // Address
    txtAddress.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        )
    );

    txtAddress.setForeground(
        ColorTheme.TEXT
    );

    txtAddress.setBackground(
        ColorTheme.WHITE
    );

    txtAddress.setLineWrap(true);
    txtAddress.setWrapStyleWord(true);

    jScrollPane1.setBorder(
        BorderFactory.createLineBorder(
            ColorTheme.BORDER,
            1
        )
    );


    // Gender
    cmbGender.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        )
    );

    cmbGender.setBackground(
        ColorTheme.WHITE
    );

    cmbGender.setForeground(
        ColorTheme.TEXT
    );

    cmbGender.setBorder(
        BorderFactory.createLineBorder(
            ColorTheme.BORDER,
            1
        )
    );
}
    
    private void styleTextField(
        JTextField field) {

    field.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        )
    );

    field.setForeground(
        ColorTheme.TEXT
    );

    field.setBackground(
        ColorTheme.WHITE
    );

    field.setCaretColor(
        ColorTheme.PRIMARY
    );

    field.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                ColorTheme.BORDER,
                1
            ),
            new EmptyBorder(
                7,
                10,
                7,
                10
            )
        )
    );
}
    
    private void stylePatientButtons() {

    styleButton(
        btnSavePatient,
        "SAVE",
        ColorTheme.PRIMARY,
        ColorTheme.WHITE
    );

    styleButton(
        btnUpdatePatient,
        "UPDATE",
        ColorTheme.GOLD,
        ColorTheme.TEXT
    );

    styleButton(
        btnClearPatient,
        "CLEAR",
        ColorTheme.SECONDARY_TEXT,
        ColorTheme.WHITE
    );

    styleButton(
        btnSearch,
        "SEARCH",
        ColorTheme.PRIMARY,
        ColorTheme.WHITE
    );

    styleButton(
        btnBack,
        "←  BACK",
        ColorTheme.PRIMARY_DARK,
        ColorTheme.WHITE
    );
}
    
    private void styleButton(
        JButton button,
        String text,
        Color background,
        Color foreground) {

    button.setText(text);

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            12
        )
    );

    button.setBackground(
        background
    );

    button.setForeground(
        foreground
    );

    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setOpaque(true);

    button.setCursor(
        new Cursor(
            Cursor.HAND_CURSOR
        )
    );

    button.setBorder(
        new EmptyBorder(
            9,
            17,
            9,
            17
        )
    );
}
    
    private void styleSearchArea() {

    styleTextField(
        txtSearchPatient
    );

    txtSearchPatient.setToolTipText(
        "Search patients by name or patient information"
    );
}
    
private void stylePatientTable() {

    // =========================
    // TABLE BODY
    // =========================
    tblPatients.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tblPatients.setForeground(ColorTheme.TEXT);
    tblPatients.setBackground(ColorTheme.WHITE);

    tblPatients.setRowHeight(30);

    // Selection
    tblPatients.setSelectionBackground(ColorTheme.LIGHT_TEAL);
    tblPatients.setSelectionForeground(ColorTheme.TEXT);

    // Grid
    tblPatients.setShowVerticalLines(false);
    tblPatients.setShowHorizontalLines(true);
    tblPatients.setGridColor(ColorTheme.BORDER);

    // =========================
    // TABLE HEADER
    // =========================
    JTableHeader header = tblPatients.getTableHeader();

    header.setPreferredSize(
        new Dimension(header.getPreferredSize().width, 42)
    );

    // Custom header renderer
    DefaultTableCellRenderer headerRenderer =
            new DefaultTableCellRenderer();

    headerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
    headerRenderer.setVerticalAlignment(SwingConstants.CENTER);

    headerRenderer.setFont(
            new Font("Segoe UI", Font.BOLD, 14)
    );

    headerRenderer.setForeground(ColorTheme.WHITE);
    headerRenderer.setBackground(ColorTheme.PRIMARY_DARK);

    headerRenderer.setOpaque(true);

    headerRenderer.setBorder(
            BorderFactory.createMatteBorder(
                    0, 0, 2, 0,
                    ColorTheme.PRIMARY
            )
    );

    header.setDefaultRenderer(headerRenderer);

    // =========================
    // ID COLUMN
    // =========================
    DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();

    centerRenderer.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    tblPatients.getColumnModel()
            .getColumn(0)
            .setCellRenderer(centerRenderer);

    // =========================
    // COLUMN WIDTHS
    // =========================
    tblPatients.getColumnModel()
            .getColumn(0)
            .setPreferredWidth(55);

    tblPatients.getColumnModel()
            .getColumn(1)
            .setPreferredWidth(130);

    tblPatients.getColumnModel()
            .getColumn(2)
            .setPreferredWidth(130);

    tblPatients.getColumnModel()
            .getColumn(3)
            .setPreferredWidth(130);

    tblPatients.getColumnModel()
            .getColumn(4)
            .setPreferredWidth(200);
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
        btnSearch = new javax.swing.JButton();
        txtSearchPatient = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPatientId = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtContact = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDateOfBirth = new javax.swing.JTextField();
        cmbGender = new javax.swing.JComboBox<>();
        btnSavePatient = new javax.swing.JButton();
        btnUpdatePatient = new javax.swing.JButton();
        btnClearPatient = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPatients = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("Search Patient: ");

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(this::btnSearchActionPerformed);

        txtSearchPatient.addActionListener(this::txtSearchPatientActionPerformed);

        jLabel2.setText("PATIENT DETAILS");

        jLabel3.setText("Patient ID:");

        jLabel4.setText("First Name:");

        jLabel5.setText("Last Name:");

        jLabel6.setText("Address:");

        jLabel7.setText("Contact No:");

        jLabel8.setText("Email:   ");

        jLabel9.setText("Date of Birth:");

        jLabel10.setText("Gender:");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        txtDateOfBirth.setText("YYYY-MM-DD");

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male\t", "Female" }));

        btnSavePatient.setText("SAVE");
        btnSavePatient.addActionListener(this::btnSavePatientActionPerformed);

        btnUpdatePatient.setText("UPDATE");
        btnUpdatePatient.addActionListener(this::btnUpdatePatientActionPerformed);

        btnClearPatient.setText("CLEAR");
        btnClearPatient.addActionListener(this::btnClearPatientActionPerformed);

        tblPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID ", "First Name", "Last Name", "Contact ", "Email"
            }
        ));
        tblPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPatientsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPatients);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        jLabel11.setText("PATIENT MANAGEMENT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                        .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtDateOfBirth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtContact, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSavePatient)
                        .addGap(35, 35, 35)
                        .addComponent(btnUpdatePatient)
                        .addGap(34, 34, 34)
                        .addComponent(btnClearPatient))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearch)))))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSavePatient)
                            .addComponent(btnUpdatePatient)
                            .addComponent(btnClearPatient))
                        .addGap(95, 95, 95))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSavePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePatientActionPerformed
        // TODO add your handling code here:
        try {

        Patient patient = new Patient();

        patient.setFirstName(
                txtFirstName.getText().trim()
        );

        patient.setLastName(
                txtLastName.getText().trim()
        );

        patient.setAddress(
                txtAddress.getText().trim()
        );

        patient.setContactNumber(
                txtContact.getText().trim()
        );

        patient.setEmail(
                txtEmail.getText().trim()
        );

        String dob =
                txtDateOfBirth.getText().trim();

        if (!dob.isEmpty()
                && !dob.equals("YYYY-MM-DD")) {

            patient.setDateOfBirth(
                    java.time.LocalDate.parse(dob)
            );
        }

        patient.setGender(
                cmbGender.getSelectedItem()
                        .toString()
                        .trim()
        );

        String json =
                objectMapper.writeValueAsString(
                        patient
                );

        HttpResponse<String> response =
                patientApiClient.createPatient(
                        json
                );

        if (response.statusCode() == 201) {

            Patient createdPatient =
                    objectMapper.readValue(
                            response.body(),
                            Patient.class
                    );

            txtPatientId.setText(
                    String.valueOf(
                            createdPatient.getPatientId()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Patient saved successfully.\n"
                    + "Patient ID: "
                    + createdPatient.getPatientId()
            );

            loadPatients();

        } else {

            showApiError(
                    response,
                    "Save Error"
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date. Please use YYYY-MM-DD.",
                "Save Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSavePatientActionPerformed

    private void btnClearPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPatientActionPerformed
        // TODO add your handling code here:
        clearFields();

    tblPatients.clearSelection();
    }//GEN-LAST:event_btnClearPatientActionPerformed

    private void txtSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchPatientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchPatientActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        try {

        String searchText =
                txtSearchPatient.getText().trim();

        if (searchText.isEmpty()) {

            loadPatients();
            return;
        }

        HttpResponse<String> response =
                patientApiClient.searchPatients(
                        searchText
                );

        if (response.statusCode() == 200) {

            List<Patient> patients =
                    objectMapper.readValue(
                            response.body(),
                            new TypeReference<List<Patient>>() {}
                    );

            DefaultTableModel model =
                    (DefaultTableModel)
                    tblPatients.getModel();

            model.setRowCount(0);

            for (Patient patient : patients) {

                model.addRow(new Object[]{

                    patient.getPatientId(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getContactNumber(),
                    patient.getEmail()

                });
            }

            if (patients.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No patient found."
                );
            }

        } else {

            showApiError(
                    response,
                    "Search Error"
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Search Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void tblPatientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPatientsMouseClicked
        // TODO add your handling code here:
        try {

        int row =
                tblPatients.getSelectedRow();

        if (row == -1) {
            return;
        }

        int patientId =
                Integer.parseInt(
                        tblPatients
                                .getValueAt(row, 0)
                                .toString()
                );

        HttpResponse<String> response =
                patientApiClient.getPatient(
                        patientId
                );

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Patient Error"
            );

            return;
        }

        Patient patient =
                objectMapper.readValue(
                        response.body(),
                        Patient.class
                );

        txtPatientId.setText(
                String.valueOf(
                        patient.getPatientId()
                )
        );

        txtFirstName.setText(
                patient.getFirstName()
        );

        txtLastName.setText(
                patient.getLastName()
        );

        txtAddress.setText(
                patient.getAddress()
        );

        txtContact.setText(
                patient.getContactNumber()
        );

        txtEmail.setText(
                patient.getEmail()
        );

        if (patient.getDateOfBirth() != null) {

            txtDateOfBirth.setText(
                    patient.getDateOfBirth()
                            .toString()
            );

        } else {

            txtDateOfBirth.setText("");
        }

        cmbGender.setSelectedItem(
                patient.getGender()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Patient Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_tblPatientsMouseClicked

    private void btnUpdatePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePatientActionPerformed
        // TODO add your handling code here:
        try {

        if (txtPatientId.getText()
                .trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient first."
            );

            return;
        }

        Patient patient = new Patient();

        int patientId =
                Integer.parseInt(
                        txtPatientId.getText().trim()
                );

        patient.setPatientId(patientId);

        patient.setFirstName(
                txtFirstName.getText().trim()
        );

        patient.setLastName(
                txtLastName.getText().trim()
        );

        patient.setAddress(
                txtAddress.getText().trim()
        );

        patient.setContactNumber(
                txtContact.getText().trim()
        );

        patient.setEmail(
                txtEmail.getText().trim()
        );

        String dob =
                txtDateOfBirth.getText().trim();

        if (!dob.isEmpty()
                && !dob.equals("YYYY-MM-DD")) {

            patient.setDateOfBirth(
                    java.time.LocalDate.parse(dob)
            );
        }

        patient.setGender(
                cmbGender.getSelectedItem()
                        .toString()
                        .trim()
        );

        String json =
                objectMapper.writeValueAsString(
                        patient
                );
        System.out.println(
        "UPDATE JSON SENT:"
);

System.out.println(json);

        HttpResponse<String> response =
                patientApiClient.updatePatient(
                        patientId,
                        json
                );

        if (response.statusCode() == 200) {

            JOptionPane.showMessageDialog(
                    this,
                    "Patient updated successfully."
            );

            loadPatients();

        } else {

            showApiError(
                    response,
                    "Update Error"
            );
        }

    } catch (java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid date. Please use YYYY-MM-DD.",
                "Update Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid patient ID.",
                "Update Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Update Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnUpdatePatientActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
       goBackToDashboard();
    }//GEN-LAST:event_btnBackActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new PatientForm().setVisible(true));
    }
    
    private void loadPatients() {

    try {

        HttpResponse<String> response =
                patientApiClient.searchPatients("");

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Error"
            );

            return;
        }

        List<Patient> patients =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<Patient>>() {}
                );

        DefaultTableModel model =
                (DefaultTableModel)
                tblPatients.getModel();

        model.setRowCount(0);

        for (Patient patient : patients) {

            model.addRow(new Object[]{

                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getContactNumber(),
                patient.getEmail()

            });
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void showApiError(
        HttpResponse<String> response,
        String title) {

    String message =
            "HTTP Status: "
            + response.statusCode()
            + "\n\n"
            + "Server Response:\n"
            + response.body();

    JOptionPane.showMessageDialog(
            this,
            message,
            title,
            JOptionPane.ERROR_MESSAGE
    );
}
    
    private void clearFields() {

    txtPatientId.setText("");
    txtFirstName.setText("");
    txtLastName.setText("");
    txtAddress.setText("");
    txtContact.setText("");
    txtEmail.setText("");
    txtDateOfBirth.setText("YYYY-MM-DD");

    cmbGender.setSelectedIndex(0);

    txtFirstName.requestFocus();
}
    
    private void goBackToDashboard() {

    DashboardForm dashboard =
            new DashboardForm(currentUser);

    dashboard.setVisible(true);

    this.dispose();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearPatient;
    private javax.swing.JButton btnSavePatient;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdatePatient;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable tblPatients;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtDateOfBirth;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPatientId;
    private javax.swing.JTextField txtSearchPatient;
    // End of variables declaration//GEN-END:variables
}
