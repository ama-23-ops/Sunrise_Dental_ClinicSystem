/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sunrisedental.model.User;
import com.sunrisedental.util.SessionManager;
import javax.swing.JOptionPane;
import com.sunrisedental.client.DashboardApiClient;
import com.sunrisedental.model.DashboardStats;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

import javax.swing.JOptionPane;
import com.sunrisedental.client.ReportApiClient;
import com.sunrisedental.report.AppointmentDetails;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iffah
 */
public class DashboardForm extends javax.swing.JFrame {
    
    private User currentUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardForm.class.getName());
    private final DashboardApiClient dashboardApiClient =
        new DashboardApiClient();
    
    private final ReportApiClient reportApiClient =
        new ReportApiClient();

private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    
    /**
     * Creates new form DashboardForm
     */
    public DashboardForm() {
        initComponents();
    }
    
    public DashboardForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;

    setupUserAccess();
    
    setupTodayAppointmentsTable();
    
    loadDashboardStats();
    
    loadTodayAppointments();
    
    setLocationRelativeTo(null);
}
    
    private void setupUserAccess() {

    lblWelcome.setText(
            "Welcome, "
            + currentUser.getFullName()
            + " ("
            + currentUser.getRole()
            + ")"
    );

    boolean isAdmin =
            currentUser.getRole()
            .equalsIgnoreCase("ADMIN");

    btnDentists.setVisible(isAdmin);

    btnTreatments.setVisible(isAdmin);

    btnUsers.setVisible(isAdmin);
}
    
    private void loadDashboardStats() {

    try {

        HttpResponse<String> response =
                dashboardApiClient.getStats();

        if (response.statusCode() != 200) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load dashboard statistics.\n"
                    + "HTTP Status: "
                    + response.statusCode()
                    + "\n\n"
                    + response.body(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        DashboardStats stats =
                objectMapper.readValue(
                        response.body(),
                        DashboardStats.class
                );


        // ==========================================
        // UPDATE DASHBOARD CARDS
        // ==========================================

        lblTotalPatients.setText(
                String.valueOf(
                        stats.getTotalPatients()
                )
        );

        lblTodayAppointments.setText(
                String.valueOf(
                        stats.getTodayAppointments()
                )
        );

        lblRevenue.setText(
                "Rs. "
                + stats.getTodayRevenue()
                        .setScale(
                                2,
                                java.math.RoundingMode.HALF_UP
                        )
                        .toPlainString()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load dashboard data.\n\n"
                + e.getMessage(),
                "Dashboard Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void loadTodayAppointments() {

    try {

        String today =
                LocalDate.now().toString();

        HttpResponse<String> response =
                reportApiClient
                        .getDailyAppointments(today);


        if (response.statusCode() != 200) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load today's appointments.\n"
                    + "HTTP Status: "
                    + response.statusCode(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        List<AppointmentDetails> appointments =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<
                                List<AppointmentDetails>
                        >() {}
                );


        DefaultTableModel model =
                (DefaultTableModel)
                tblTodayAppointments.getModel();


        model.setRowCount(0);


        for (AppointmentDetails appointment
                : appointments) {

            model.addRow(
                    new Object[]{

                        appointment.appointmentNo,

                        appointment.patientName,

                        appointment.dentistName,

                        appointment.treatmentName,

                        appointment.appointmentDate,

                        appointment.appointmentTime,

                        appointment.status
                    }
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to load today's appointments.\n\n"
                + e.getMessage(),
                "Dashboard Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void setupTodayAppointmentsTable() {

    DefaultTableModel model =
            new DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Appointment No",
                        "Patient",
                        "Dentist",
                        "Treatment",
                        "Date",
                        "Time",
                        "Status"
                    }
            ) {

        @Override
        public boolean isCellEditable(
                int row,
                int column) {

            return false;
        }
    };

    tblTodayAppointments.setModel(model);

    tblTodayAppointments.setRowHeight(28);

    tblTodayAppointments
            .setAutoResizeMode(
                    javax.swing.JTable
                            .AUTO_RESIZE_ALL_COLUMNS
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

        pnlMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnPatients = new javax.swing.JButton();
        btnAppointments = new javax.swing.JButton();
        btnBilling = new javax.swing.JButton();
        btnReports = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnDentists = new javax.swing.JButton();
        btnTreatments = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalPatients = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTodayAppointments = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblRevenue = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTodayAppointments = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMenu.setBackground(new java.awt.Color(0, 102, 255));

        jButton1.setText("DASHBOARD    ");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        btnPatients.setText("PATIENTS     ");
        btnPatients.addActionListener(this::btnPatientsActionPerformed);

        btnAppointments.setText("APPOINTMENTS ");
        btnAppointments.addActionListener(this::btnAppointmentsActionPerformed);

        btnBilling.setText("BILLING      ");
        btnBilling.addActionListener(this::btnBillingActionPerformed);

        btnReports.setText("REPORTS      ");
        btnReports.addActionListener(this::btnReportsActionPerformed);

        btnLogout.setText("LOGOUT       ");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnDentists.setText("DENTISTS ");
        btnDentists.addActionListener(this::btnDentistsActionPerformed);

        btnTreatments.setText("TREATMENTS ");
        btnTreatments.addActionListener(this::btnTreatmentsActionPerformed);

        btnUsers.setText("USERS ");
        btnUsers.addActionListener(this::btnUsersActionPerformed);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBilling)
                    .addComponent(btnAppointments)
                    .addComponent(btnPatients)
                    .addComponent(jButton1)
                    .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDentists)
                        .addComponent(btnReports)
                        .addGroup(pnlMenuLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLogout)
                                .addComponent(btnTreatments))))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addComponent(btnUsers)
                        .addGap(26, 26, 26)))
                .addGap(71, 71, 71))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnPatients)
                .addGap(18, 18, 18)
                .addComponent(btnAppointments)
                .addGap(18, 18, 18)
                .addComponent(btnBilling)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReports)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDentists)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTreatments)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsers)
                .addGap(32, 32, 32)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContent.setBackground(new java.awt.Color(0, 102, 204));

        jLabel3.setText("DASHBOARD OVERVIEW ");

        jLabel1.setText("PATIENTS   ");

        jLabel5.setText("Total      ");

        lblTotalPatients.setText("totalp");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalPatients, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lblTotalPatients)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel2.setText("APPOINMENTS");

        jLabel6.setText("Today      ");

        lblTodayAppointments.setText("appoi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTodayAppointments, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(lblTodayAppointments)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jLabel4.setText("REVENUE  ");

        jLabel7.setText("Today      ");

        lblRevenue.setText("rev");

        jLabel8.setText("Rs:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jLabel9.setText("Today's Appointments                ");

        tblTodayAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment No ", "Patient", "Dentist", "Treatment ", "Date ", "Time ", "Status"
            }
        ));
        tblTodayAppointments.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblTodayAppointments.setRowHeight(28);
        tblTodayAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblTodayAppointments);

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContentLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        lblTitle.setText("SUNRISE DENTAL CLINIC");

        lblWelcome.setText("Welcome, Administrator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(lblWelcome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAppointmentsActionPerformed
        // TODO add your handling code here:
        new AppointmentForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAppointmentsActionPerformed

    private void btnPatientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatientsActionPerformed
        // TODO add your handling code here:
        new PatientForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPatientsActionPerformed

    private void btnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBillingActionPerformed
        // TODO add your handling code here:
        new BillingForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBillingActionPerformed

    private void btnReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportsActionPerformed
        // TODO add your handling code here:
        new ReportsForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReportsActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {

        SessionManager.logout();

        new LoginForm().setVisible(true);

        this.dispose();
    }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTreatmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTreatmentsActionPerformed
        // TODO add your handling code here:
        if (!currentUser.getRole()
            .equalsIgnoreCase("ADMIN")) {

        JOptionPane.showMessageDialog(
                this,
                "Access denied. "
                + "Administrator privileges required."
        );

        return;
    }

    new TreatmentForm(currentUser).setVisible(true);
    }//GEN-LAST:event_btnTreatmentsActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        // TODO add your handling code here:
       if (!currentUser.getRole()
            .equalsIgnoreCase("ADMIN")) {

        JOptionPane.showMessageDialog(
                this,
                "Access denied. "
                + "Administrator privileges required."
        );

        return;
    }

    UserForm userForm =
            new UserForm(currentUser);

    userForm.setVisible(true);
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnDentistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDentistsActionPerformed
        // TODO add your handling code here:
        if (!currentUser.getRole()
            .equalsIgnoreCase("ADMIN")) {

        JOptionPane.showMessageDialog(
                this,
                "Access denied. "
                + "Administrator privileges required."
        );

        return;
    }

    new DentistForm(currentUser).setVisible(true);
    }//GEN-LAST:event_btnDentistsActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DashboardForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAppointments;
    private javax.swing.JButton btnBilling;
    private javax.swing.JButton btnDentists;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPatients;
    private javax.swing.JButton btnReports;
    private javax.swing.JButton btnTreatments;
    private javax.swing.JButton btnUsers;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRevenue;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTodayAppointments;
    private javax.swing.JLabel lblTotalPatients;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JTable tblTodayAppointments;
    // End of variables declaration//GEN-END:variables
}
