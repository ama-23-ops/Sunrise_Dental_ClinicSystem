package com.sunrisedental.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import com.sunrisedental.client.ReportApiClient;
import com.sunrisedental.report.AppointmentDetails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrisedental.model.User;

import java.net.http.HttpResponse;
import java.time.LocalDate;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author iffah
 */
public class ReportsForm extends javax.swing.JFrame {
    private User currentUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportsForm.class.getName());

    /**
     * Creates new form ReportsForm
     */
    public ReportsForm() {
        initComponents();
    }
    public ReportsForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;
    
    setupReportsUI();
}
    
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

private void setupReportsUI() {

    setTitle("Sunrise Dental Clinic - Daily Appointment Reports");
    getContentPane().setBackground(ColorTheme.BACKGROUND);

    styleReportTitle();
    styleReportLabels();
    styleReportDateField();
    styleReportButtons();
    styleReportTable();
}

private void styleReportTitle() {

    jLabel1.setText("DAILY APPOINTMENT REPORT");

    jLabel1.setFont(
            new Font("Segoe UI", Font.BOLD, 26)
    );

    jLabel1.setForeground(
            ColorTheme.PRIMARY_DARK
    );

    jLabel1.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    jLabel1.setVerticalAlignment(
            SwingConstants.CENTER
    );
}

private void styleReportLabels() {

    jLabel2.setFont(
            new Font("Segoe UI", Font.BOLD, 13)
    );

    jLabel2.setForeground(
            ColorTheme.TEXT
    );
}

private void styleReportDateField() {

    txtReportDate.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    txtReportDate.setForeground(
            ColorTheme.SECONDARY_TEXT
    );

    txtReportDate.setBackground(
            ColorTheme.WHITE
    );

    txtReportDate.setCaretColor(
            ColorTheme.PRIMARY
    );

    txtReportDate.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            ColorTheme.BORDER,
                            1
                    ),
                    new EmptyBorder(
                            7, 10, 7, 10
                    )
            )
    );
}

private void styleReportButtons() {

    styleReportButton(
            btnGenerateDailyReport,
            "GENERATE REPORT",
            ColorTheme.PRIMARY,
            ColorTheme.WHITE
    );

    styleReportButton(
            btnClearReport,
            "CLEAR",
            ColorTheme.SECONDARY_TEXT,
            ColorTheme.WHITE
    );

    styleReportButton(
            btnBack,
            "←  BACK",
            ColorTheme.PRIMARY_DARK,
            ColorTheme.WHITE
    );
}

private void styleReportButton(
        JButton button,
        String text,
        Color background,
        Color foreground) {

    button.setText(text);

    button.setFont(
            new Font("Segoe UI", Font.BOLD, 12)
    );

    button.setForeground(foreground);
    button.setBackground(background);

    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setOpaque(true);

    button.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
    );

    button.setBorder(
            new EmptyBorder(
                    9, 15, 9, 15
            )
    );
}

private void styleReportTable() {

    tblReport.setFont(
            new Font("Segoe UI", Font.PLAIN, 12)
    );

    tblReport.setForeground(
            ColorTheme.TEXT
    );

    tblReport.setBackground(
            ColorTheme.WHITE
    );

    tblReport.setRowHeight(30);

    tblReport.setGridColor(
            ColorTheme.BORDER
    );

    tblReport.setShowVerticalLines(false);
    tblReport.setShowHorizontalLines(true);

    tblReport.setSelectionBackground(
            ColorTheme.LIGHT_TEAL
    );

    tblReport.setSelectionForeground(
            ColorTheme.TEXT
    );

    tblReport.setFillsViewportHeight(true);


    JTableHeader header =
        tblReport.getTableHeader();

    header.setReorderingAllowed(false);

    header.setPreferredSize(
        new Dimension(
                header.getPreferredSize().width,
                42
        )
    );

    // Custom header renderer
    DefaultTableCellRenderer headerRenderer =
        new DefaultTableCellRenderer();

    headerRenderer.setHorizontalAlignment(
        SwingConstants.LEFT
    );

    headerRenderer.setVerticalAlignment(
        SwingConstants.CENTER
    );

    headerRenderer.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                14
        )
    );

    headerRenderer.setForeground(
        ColorTheme.WHITE
    );

    headerRenderer.setBackground(
        ColorTheme.PRIMARY_DARK
    );

    headerRenderer.setOpaque(true);

    headerRenderer.setBorder(
        BorderFactory.createMatteBorder(
                0, 0, 2, 0,
                ColorTheme.PRIMARY
        )
);

    header.setDefaultRenderer(headerRenderer);


    // Center selected columns
    DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();

    centerRenderer.setHorizontalAlignment(
            SwingConstants.CENTER
    );


    tblReport
            .getColumnModel()
            .getColumn(0)
            .setCellRenderer(centerRenderer);

    tblReport
            .getColumnModel()
            .getColumn(4)
            .setCellRenderer(centerRenderer);

    tblReport
            .getColumnModel()
            .getColumn(5)
            .setCellRenderer(centerRenderer);

    tblReport
            .getColumnModel()
            .getColumn(6)
            .setCellRenderer(centerRenderer);


    // Column widths
    tblReport
            .getColumnModel()
            .getColumn(0)
            .setPreferredWidth(100);

    tblReport
            .getColumnModel()
            .getColumn(1)
            .setPreferredWidth(140);

    tblReport
            .getColumnModel()
            .getColumn(2)
            .setPreferredWidth(130);

    tblReport
            .getColumnModel()
            .getColumn(3)
            .setPreferredWidth(150);

    tblReport
            .getColumnModel()
            .getColumn(4)
            .setPreferredWidth(90);

    tblReport
            .getColumnModel()
            .getColumn(5)
            .setPreferredWidth(80);

    tblReport
            .getColumnModel()
            .getColumn(6)
            .setPreferredWidth(100);


    jScrollPane1.setBorder(
            BorderFactory.createLineBorder(
                    ColorTheme.BORDER,
                    1
            )
    );

    jScrollPane1.getViewport().setBackground(
            ColorTheme.WHITE
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
        btnGenerateDailyReport = new javax.swing.JButton();
        txtReportDate = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        btnClearReport = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DAILY APPONMENT REPORT                            ");

        jLabel2.setText("Report Date:");

        btnGenerateDailyReport.setText("GENERATE DAILY APPOINTMENT REPORT");
        btnGenerateDailyReport.addActionListener(this::btnGenerateDailyReportActionPerformed);

        txtReportDate.setText("YYYY-MM-DD");
        txtReportDate.addActionListener(this::txtReportDateActionPerformed);

        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Appointment No ", "Patient Name ", "Dentist", "Treatment ", "Date ", "Time ", "Status"
            }
        ));
        jScrollPane1.setViewportView(tblReport);

        btnClearReport.setText("CLEAR");
        btnClearReport.addActionListener(this::btnClearReportActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerateDailyReport)
                                .addGap(29, 29, 29)
                                .addComponent(btnClearReport)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtReportDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerateDailyReport)
                    .addComponent(btnClearReport))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateDailyReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateDailyReportActionPerformed
        // TODO add your handling code here:
        try {

        String dateText =
                txtReportDate
                        .getText()
                        .trim();

        if (dateText.isEmpty() ||
                dateText.equalsIgnoreCase("YYYY-MM-DD")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a report date.",
                    "Report Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validate date locally first.

        LocalDate reportDate =
                LocalDate.parse(dateText);


        // ==========================================
        // CALL REST API
        // ==========================================

        HttpResponse<String> response =
                reportApiClient
                        .getDailyAppointments(
                                reportDate.toString()
                        );


        // ==========================================
        // CHECK RESPONSE
        // ==========================================

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Report Error"
            );

            return;
        }


        // ==========================================
        // CONVERT JSON TO OBJECTS
        // ==========================================

        List<AppointmentDetails> appointments =
                objectMapper.readValue(
                        response.body(),
                        new TypeReference<
                                List<AppointmentDetails>
                        >() {}
                );


        // ==========================================
        // UPDATE TABLE
        // ==========================================

        DefaultTableModel model =
                (DefaultTableModel)
                tblReport.getModel();

        model.setRowCount(0);


        for (AppointmentDetails appointment
                : appointments) {

            model.addRow(new Object[]{

                appointment.appointmentNo,
                appointment.patientName,
                appointment.dentistName,
                appointment.treatmentName,
                appointment.appointmentDate,
                appointment.appointmentTime,
                appointment.status

            });
        }


        // ==========================================
        // NO RESULTS
        // ==========================================

        if (appointments.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No appointments found for "
                    + reportDate,
                    "Daily Report",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    } catch (
            java.time.format.DateTimeParseException e) {

        JOptionPane.showMessageDialog(
                this,
                "Please enter date as YYYY-MM-DD.",
                "Report Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Report Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnGenerateDailyReportActionPerformed

    private void txtReportDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReportDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReportDateActionPerformed

    private void btnClearReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearReportActionPerformed
        // TODO add your handling code here:
    txtReportDate.setText("YYYY-MM-DD");
    
    DefaultTableModel model =
            (DefaultTableModel)
            tblReport.getModel();

    model.setRowCount(0);
    }//GEN-LAST:event_btnClearReportActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ReportsForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearReport;
    private javax.swing.JButton btnGenerateDailyReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReport;
    private javax.swing.JTextField txtReportDate;
    // End of variables declaration//GEN-END:variables
}
