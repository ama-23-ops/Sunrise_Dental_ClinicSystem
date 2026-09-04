package com.sunrisedental.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import com.sunrisedental.model.User;
import com.sunrisedental.util.ColorTheme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author iffah
 */
public class HelpForm extends javax.swing.JFrame {
        private User currentUser;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HelpForm.class.getName());

    /**
     * Creates new form HelpForm
     */
    public HelpForm() {
        initComponents();
            applyTheme();

    }
    
    public HelpForm(User currentUser) {
    initComponents();

    this.currentUser = currentUser;

    applyTheme();

    setTitle("Sunrise Dental Clinic - Help & Support");
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
}
    
    // ==========================================
// APPLY SUNRISE DENTAL THEME
// ==========================================

private void applyTheme() {

    // Main background
    pnlMain.setBackground(ColorTheme.BACKGROUND);
    pnlHeader.setBackground(ColorTheme.WHITE);

    // Header
    lblTitle.setText("SUNRISE DENTAL CLINIC");
    lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblTitle.setForeground(ColorTheme.PRIMARY);

    lblSubtitle.setText("Help & Support");
    lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    lblSubtitle.setForeground(ColorTheme.SECONDARY_TEXT);

    // Back button
    btnBack.setText("Back");
    btnBack.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btnBack.setForeground(ColorTheme.WHITE);
    btnBack.setBackground(ColorTheme.PRIMARY);
    btnBack.setFocusPainted(false);
    btnBack.setBorderPainted(false);
    btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // Main heading
    lblHelpTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
    lblHelpTitle.setForeground(ColorTheme.PRIMARY_DARK);

    lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    lblDescription.setForeground(ColorTheme.SECONDARY_TEXT);

    // Help cards
    styleHelpCard(
            pnlGettingStarted,
            lblGettingStartedTitle,
            lblGettingStartedText
    );

    styleHelpCard(
            pnlPatientsHelp,
            lblPatientsHelp,
            txtPatientsHelp
    );

    styleHelpCard(
            pnlAppointmentsHelp,
            lblAppointmentsHelp,
            txtAppointmentsHelp
    );

    styleHelpCard(
            pnlBillingHelp,
            lblBillingHelp,
            txtBillingHelp
    );

    styleHelpCard(
            pnlDentistTreatmentHelp,
            lblDentistTreatmentHelp,
            txtDentistTreatmentHelp
    );

    styleHelpCard(
            pnlUsersHelp,
            lblUsersHelp,
            txtUsersHelp
    );

    // FAQ section
    pnlFAQ.setBackground(ColorTheme.WHITE);
    pnlFAQ.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ColorTheme.BORDER),
                    new EmptyBorder(12, 15, 12, 15)
            )
    );

    lblFAQTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
    lblFAQTitle.setForeground(ColorTheme.PRIMARY_DARK);

    styleFAQLabel(jLabel1);
    styleFAQLabel(jLabel2);
    styleFAQLabel(jLabel3);
    styleFAQLabel(jLabel4);
    styleFAQLabel(jLabel5);
    styleFAQLabel(jLabel6);
    styleFAQLabel(jLabel7);
    styleFAQLabel(jLabel8);

    // FAQ text
    jLabel1.setText("Q: How do I register a new patient?");
    jLabel2.setText("Open Patients from the sidebar, complete the required information and click Save.");

    jLabel3.setText("Q: How do I book an appointment?");
    jLabel4.setText("Open Appointments, select the patient, dentist and treatment, then save the appointment.");

    jLabel5.setText("Q: How do I generate a bill?");
    jLabel6.setText("Open Billing, select the relevant appointment and complete the payment information.");

    jLabel7.setText("Q: Why can't I access User Management?");
    jLabel8.setText("User Management is restricted to Administrator users.");

    // Refresh the form
    pnlMain.revalidate();
    pnlMain.repaint();
}

private void styleHelpCard(
        JPanel panel,
        JLabel title,
        JLabel description) {

    panel.setBackground(ColorTheme.WHITE);

    panel.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ColorTheme.BORDER),
                    new EmptyBorder(15, 15, 15, 15)
            )
    );

    title.setFont(new Font("Segoe UI", Font.BOLD, 14));
    title.setForeground(ColorTheme.PRIMARY_DARK);

    description.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    description.setForeground(ColorTheme.SECONDARY_TEXT);

    // Allow the description to wrap instead of being cut off
    description.setText(
            "<html><div style='width:180px;'>"
            + description.getText()
            + "</div></html>"
    );
}

private void styleFAQLabel(JLabel label) {

    label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    label.setForeground(ColorTheme.TEXT);

    label.setBorder(
            new EmptyBorder(6, 5, 6, 5)
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

        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lblHelpTitle = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        pnlGettingStarted = new javax.swing.JPanel();
        lblGettingStartedTitle = new javax.swing.JLabel();
        lblGettingStartedText = new javax.swing.JLabel();
        pnlPatientsHelp = new javax.swing.JPanel();
        lblPatientsHelp = new javax.swing.JLabel();
        txtPatientsHelp = new javax.swing.JLabel();
        pnlAppointmentsHelp = new javax.swing.JPanel();
        lblAppointmentsHelp = new javax.swing.JLabel();
        txtAppointmentsHelp = new javax.swing.JLabel();
        pnlBillingHelp = new javax.swing.JPanel();
        lblBillingHelp = new javax.swing.JLabel();
        txtBillingHelp = new javax.swing.JLabel();
        pnlDentistTreatmentHelp = new javax.swing.JPanel();
        lblDentistTreatmentHelp = new javax.swing.JLabel();
        txtDentistTreatmentHelp = new javax.swing.JLabel();
        pnlFAQ = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlUsersHelp = new javax.swing.JPanel();
        lblUsersHelp = new javax.swing.JLabel();
        txtUsersHelp = new javax.swing.JLabel();
        lblFAQTitle = new javax.swing.JLabel();
        pnlMain1 = new javax.swing.JPanel();
        pnlHeader1 = new javax.swing.JPanel();
        lblTitle1 = new javax.swing.JLabel();
        lblSubtitle1 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        lblHelpTitle1 = new javax.swing.JLabel();
        lblDescription1 = new javax.swing.JLabel();
        pnlGettingStarted1 = new javax.swing.JPanel();
        lblGettingStartedTitle1 = new javax.swing.JLabel();
        lblGettingStartedText1 = new javax.swing.JLabel();
        pnlPatientsHelp1 = new javax.swing.JPanel();
        lblPatientsHelp1 = new javax.swing.JLabel();
        txtPatientsHelp1 = new javax.swing.JLabel();
        pnlAppointmentsHelp1 = new javax.swing.JPanel();
        lblAppointmentsHelp1 = new javax.swing.JLabel();
        txtAppointmentsHelp1 = new javax.swing.JLabel();
        pnlBillingHelp1 = new javax.swing.JPanel();
        lblBillingHelp1 = new javax.swing.JLabel();
        txtBillingHelp1 = new javax.swing.JLabel();
        pnlDentistTreatmentHelp1 = new javax.swing.JPanel();
        lblDentistTreatmentHelp1 = new javax.swing.JLabel();
        txtDentistTreatmentHelp1 = new javax.swing.JLabel();
        pnlFAQ1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pnlUsersHelp1 = new javax.swing.JPanel();
        lblUsersHelp1 = new javax.swing.JLabel();
        txtUsersHelp1 = new javax.swing.JLabel();
        lblFAQTitle1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sunrise Dental Clinic - Help & Support");
        setResizable(false);

        lblTitle.setText("SUNRISE DENTAL CLINIC");

        lblSubtitle.setText("Help & Support");

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblSubtitle, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(211, 211, 211))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(25, 25, 25))
        );

        lblHelpTitle.setText("How can we help you?");

        lblDescription.setText("Find quick guidance for using the Sunrise Dental Clinic Management System.");

        lblGettingStartedTitle.setText("GETTING STARTED");

        lblGettingStartedText.setText("Learn how to log in and navigate the dashboard.");

        javax.swing.GroupLayout pnlGettingStartedLayout = new javax.swing.GroupLayout(pnlGettingStarted);
        pnlGettingStarted.setLayout(pnlGettingStartedLayout);
        pnlGettingStartedLayout.setHorizontalGroup(
            pnlGettingStartedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGettingStartedLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblGettingStartedTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlGettingStartedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGettingStartedText, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGettingStartedLayout.setVerticalGroup(
            pnlGettingStartedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGettingStartedLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblGettingStartedTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGettingStartedText, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lblPatientsHelp.setText("PATIENT MANAGEMENT");

        txtPatientsHelp.setText("Add, search and update patient information.");

        javax.swing.GroupLayout pnlPatientsHelpLayout = new javax.swing.GroupLayout(pnlPatientsHelp);
        pnlPatientsHelp.setLayout(pnlPatientsHelpLayout);
        pnlPatientsHelpLayout.setHorizontalGroup(
            pnlPatientsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientsHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientsHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPatientsHelpLayout.createSequentialGroup()
                        .addComponent(txtPatientsHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))))
        );
        pnlPatientsHelpLayout.setVerticalGroup(
            pnlPatientsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientsHelpLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblPatientsHelp)
                .addGap(30, 30, 30)
                .addComponent(txtPatientsHelp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAppointmentsHelp.setText("APPOINTMENTS");

        txtAppointmentsHelp.setText("Book, update and cancel appointments.");

        javax.swing.GroupLayout pnlAppointmentsHelpLayout = new javax.swing.GroupLayout(pnlAppointmentsHelp);
        pnlAppointmentsHelp.setLayout(pnlAppointmentsHelpLayout);
        pnlAppointmentsHelpLayout.setHorizontalGroup(
            pnlAppointmentsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentsHelpLayout.createSequentialGroup()
                .addGroup(pnlAppointmentsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAppointmentsHelpLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblAppointmentsHelp, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAppointmentsHelpLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAppointmentsHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAppointmentsHelpLayout.setVerticalGroup(
            pnlAppointmentsHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentsHelpLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblAppointmentsHelp)
                .addGap(18, 18, 18)
                .addComponent(txtAppointmentsHelp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBillingHelp.setText("BILLING");

        txtBillingHelp.setText("Generate bills and manage payment information.");

        javax.swing.GroupLayout pnlBillingHelpLayout = new javax.swing.GroupLayout(pnlBillingHelp);
        pnlBillingHelp.setLayout(pnlBillingHelpLayout);
        pnlBillingHelpLayout.setHorizontalGroup(
            pnlBillingHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillingHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBillingHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBillingHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBillingHelpLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblBillingHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBillingHelpLayout.setVerticalGroup(
            pnlBillingHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillingHelpLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblBillingHelp)
                .addGap(18, 18, 18)
                .addComponent(txtBillingHelp)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        lblDentistTreatmentHelp.setText("DENTISTS & TREATMENTS");

        txtDentistTreatmentHelp.setText("Manage dentist and treatment information.");

        javax.swing.GroupLayout pnlDentistTreatmentHelpLayout = new javax.swing.GroupLayout(pnlDentistTreatmentHelp);
        pnlDentistTreatmentHelp.setLayout(pnlDentistTreatmentHelpLayout);
        pnlDentistTreatmentHelpLayout.setHorizontalGroup(
            pnlDentistTreatmentHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDentistTreatmentHelpLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDentistTreatmentHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDentistTreatmentHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDentistTreatmentHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlDentistTreatmentHelpLayout.setVerticalGroup(
            pnlDentistTreatmentHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDentistTreatmentHelpLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblDentistTreatmentHelp)
                .addGap(18, 18, 18)
                .addComponent(txtDentistTreatmentHelp)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel1.setText("Q: How do I register a new patient?");

        jLabel2.setText("Open Patients from the sidebar, complete the required information and click Save.");

        jLabel3.setText("Q: How do I book an appointment?");

        jLabel4.setText("Open Appointments, enter the required details and save the appointment.");

        jLabel5.setText("Q: How do I generate a bill?");

        jLabel6.setText("Open Billing, select the appointment and complete the payment information.");

        jLabel7.setText("Q: Why can't I access User Management?");

        jLabel8.setText("User Management is restricted to Administrator users.");

        javax.swing.GroupLayout pnlFAQLayout = new javax.swing.GroupLayout(pnlFAQ);
        pnlFAQ.setLayout(pnlFAQLayout);
        pnlFAQLayout.setHorizontalGroup(
            pnlFAQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFAQLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(pnlFAQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFAQLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(409, 409, 409))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFAQLayout.createSequentialGroup()
                        .addGroup(pnlFAQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFAQLayout.setVerticalGroup(
            pnlFAQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFAQLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblUsersHelp.setText("USER MANAGEMENT");

        txtUsersHelp.setText("Manage users and access permissions.");

        javax.swing.GroupLayout pnlUsersHelpLayout = new javax.swing.GroupLayout(pnlUsersHelp);
        pnlUsersHelp.setLayout(pnlUsersHelpLayout);
        pnlUsersHelpLayout.setHorizontalGroup(
            pnlUsersHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersHelpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsersHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsersHelpLayout.createSequentialGroup()
                        .addComponent(lblUsersHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlUsersHelpLayout.createSequentialGroup()
                        .addComponent(txtUsersHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );
        pnlUsersHelpLayout.setVerticalGroup(
            pnlUsersHelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersHelpLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblUsersHelp)
                .addGap(18, 18, 18)
                .addComponent(txtUsersHelp)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        lblFAQTitle.setText("Frequently Asked Questions");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(1031, 1031, 1031)
                .addComponent(lblFAQTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHelpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlGettingStarted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBillingHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(pnlPatientsHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlAppointmentsHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(pnlDentistTreatmentHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(pnlUsersHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(pnlFAQ, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHelpTitle)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblDescription)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlPatientsHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlGettingStarted, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlAppointmentsHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDentistTreatmentHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlUsersHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBillingHelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblFAQTitle)
                        .addGap(18, 18, 18)
                        .addComponent(pnlFAQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        lblTitle1.setText("SUNRISE DENTAL CLINIC");

        lblSubtitle1.setText("Help & Support");

        btnBack1.setText("Back");
        btnBack1.addActionListener(this::btnBack1ActionPerformed);

        javax.swing.GroupLayout pnlHeader1Layout = new javax.swing.GroupLayout(pnlHeader1);
        pnlHeader1.setLayout(pnlHeader1Layout);
        pnlHeader1Layout.setHorizontalGroup(
            pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeader1Layout.createSequentialGroup()
                .addGroup(pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeader1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblSubtitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHeader1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack1)
                .addGap(211, 211, 211))
        );
        pnlHeader1Layout.setVerticalGroup(
            pnlHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeader1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitle1)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeader1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack1)
                .addGap(25, 25, 25))
        );

        lblHelpTitle1.setText("How can we help you?");

        lblDescription1.setText("Find quick guidance for using the Sunrise Dental Clinic Management System.");

        lblGettingStartedTitle1.setText("GETTING STARTED");

        lblGettingStartedText1.setText("Learn how to log in and navigate the dashboard.");

        javax.swing.GroupLayout pnlGettingStarted1Layout = new javax.swing.GroupLayout(pnlGettingStarted1);
        pnlGettingStarted1.setLayout(pnlGettingStarted1Layout);
        pnlGettingStarted1Layout.setHorizontalGroup(
            pnlGettingStarted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGettingStarted1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblGettingStartedTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(pnlGettingStarted1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGettingStartedText1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlGettingStarted1Layout.setVerticalGroup(
            pnlGettingStarted1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGettingStarted1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblGettingStartedTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGettingStartedText1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        lblPatientsHelp1.setText("PATIENT MANAGEMENT");

        txtPatientsHelp1.setText("Add, search and update patient information.");

        javax.swing.GroupLayout pnlPatientsHelp1Layout = new javax.swing.GroupLayout(pnlPatientsHelp1);
        pnlPatientsHelp1.setLayout(pnlPatientsHelp1Layout);
        pnlPatientsHelp1Layout.setHorizontalGroup(
            pnlPatientsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientsHelp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPatientsHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPatientsHelp1Layout.createSequentialGroup()
                        .addComponent(txtPatientsHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))))
        );
        pnlPatientsHelp1Layout.setVerticalGroup(
            pnlPatientsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientsHelp1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblPatientsHelp1)
                .addGap(30, 30, 30)
                .addComponent(txtPatientsHelp1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAppointmentsHelp1.setText("APPOINTMENTS");

        txtAppointmentsHelp1.setText("Book, update and cancel appointments.");

        javax.swing.GroupLayout pnlAppointmentsHelp1Layout = new javax.swing.GroupLayout(pnlAppointmentsHelp1);
        pnlAppointmentsHelp1.setLayout(pnlAppointmentsHelp1Layout);
        pnlAppointmentsHelp1Layout.setHorizontalGroup(
            pnlAppointmentsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentsHelp1Layout.createSequentialGroup()
                .addGroup(pnlAppointmentsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAppointmentsHelp1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblAppointmentsHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAppointmentsHelp1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAppointmentsHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlAppointmentsHelp1Layout.setVerticalGroup(
            pnlAppointmentsHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAppointmentsHelp1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblAppointmentsHelp1)
                .addGap(18, 18, 18)
                .addComponent(txtAppointmentsHelp1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblBillingHelp1.setText("BILLING");

        txtBillingHelp1.setText("Generate bills and manage payment information.");

        javax.swing.GroupLayout pnlBillingHelp1Layout = new javax.swing.GroupLayout(pnlBillingHelp1);
        pnlBillingHelp1.setLayout(pnlBillingHelp1Layout);
        pnlBillingHelp1Layout.setHorizontalGroup(
            pnlBillingHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillingHelp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBillingHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBillingHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBillingHelp1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblBillingHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBillingHelp1Layout.setVerticalGroup(
            pnlBillingHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBillingHelp1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblBillingHelp1)
                .addGap(18, 18, 18)
                .addComponent(txtBillingHelp1)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        lblDentistTreatmentHelp1.setText("DENTISTS & TREATMENTS");

        txtDentistTreatmentHelp1.setText("Manage dentist and treatment information.");

        javax.swing.GroupLayout pnlDentistTreatmentHelp1Layout = new javax.swing.GroupLayout(pnlDentistTreatmentHelp1);
        pnlDentistTreatmentHelp1.setLayout(pnlDentistTreatmentHelp1Layout);
        pnlDentistTreatmentHelp1Layout.setHorizontalGroup(
            pnlDentistTreatmentHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDentistTreatmentHelp1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDentistTreatmentHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDentistTreatmentHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDentistTreatmentHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlDentistTreatmentHelp1Layout.setVerticalGroup(
            pnlDentistTreatmentHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDentistTreatmentHelp1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblDentistTreatmentHelp1)
                .addGap(18, 18, 18)
                .addComponent(txtDentistTreatmentHelp1)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jLabel9.setText("Q: How do I register a new patient?");

        jLabel10.setText("Open Patients from the sidebar, complete the required information and click Save.");

        jLabel11.setText("Q: How do I book an appointment?");

        jLabel12.setText("Open Appointments, enter the required details and save the appointment.");

        jLabel13.setText("Q: How do I generate a bill?");

        jLabel14.setText("Open Billing, select the appointment and complete the payment information.");

        jLabel15.setText("Q: Why can't I access User Management?");

        jLabel16.setText("User Management is restricted to Administrator users.");

        javax.swing.GroupLayout pnlFAQ1Layout = new javax.swing.GroupLayout(pnlFAQ1);
        pnlFAQ1.setLayout(pnlFAQ1Layout);
        pnlFAQ1Layout.setHorizontalGroup(
            pnlFAQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFAQ1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(pnlFAQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFAQ1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(409, 409, 409))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFAQ1Layout.createSequentialGroup()
                        .addGroup(pnlFAQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFAQ1Layout.setVerticalGroup(
            pnlFAQ1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFAQ1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addGap(29, 29, 29)
                .addComponent(jLabel11)
                .addGap(30, 30, 30)
                .addComponent(jLabel12)
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblUsersHelp1.setText("USER MANAGEMENT");

        txtUsersHelp1.setText("Manage users and access permissions.");

        javax.swing.GroupLayout pnlUsersHelp1Layout = new javax.swing.GroupLayout(pnlUsersHelp1);
        pnlUsersHelp1.setLayout(pnlUsersHelp1Layout);
        pnlUsersHelp1Layout.setHorizontalGroup(
            pnlUsersHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersHelp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsersHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsersHelp1Layout.createSequentialGroup()
                        .addComponent(lblUsersHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlUsersHelp1Layout.createSequentialGroup()
                        .addComponent(txtUsersHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );
        pnlUsersHelp1Layout.setVerticalGroup(
            pnlUsersHelp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersHelp1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblUsersHelp1)
                .addGap(18, 18, 18)
                .addComponent(txtUsersHelp1)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        lblFAQTitle1.setText("Frequently Asked Questions");

        javax.swing.GroupLayout pnlMain1Layout = new javax.swing.GroupLayout(pnlMain1);
        pnlMain1.setLayout(pnlMain1Layout);
        pnlMain1Layout.setHorizontalGroup(
            pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMain1Layout.createSequentialGroup()
                .addGap(1031, 1031, 1031)
                .addComponent(lblFAQTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMain1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHelpTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMain1Layout.createSequentialGroup()
                        .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlGettingStarted1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBillingHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMain1Layout.createSequentialGroup()
                                .addComponent(pnlPatientsHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlAppointmentsHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMain1Layout.createSequentialGroup()
                                .addComponent(pnlDentistTreatmentHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(pnlUsersHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(pnlFAQ1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200))
        );
        pnlMain1Layout.setVerticalGroup(
            pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMain1Layout.createSequentialGroup()
                .addComponent(pnlHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHelpTitle1)
                .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMain1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblDescription1)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlPatientsHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlGettingStarted1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlAppointmentsHelp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDentistTreatmentHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlUsersHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlBillingHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMain1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblFAQTitle1)
                        .addGap(18, 18, 18)
                        .addComponent(pnlFAQ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(400, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMain1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (currentUser != null) {

        new DashboardForm(currentUser).setVisible(true);

        this.dispose();

    } else {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "User session not found.",
                "Navigation Error",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );
    }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new HelpForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAppointmentsHelp;
    private javax.swing.JLabel lblAppointmentsHelp1;
    private javax.swing.JLabel lblBillingHelp;
    private javax.swing.JLabel lblBillingHelp1;
    private javax.swing.JLabel lblDentistTreatmentHelp;
    private javax.swing.JLabel lblDentistTreatmentHelp1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblDescription1;
    private javax.swing.JLabel lblFAQTitle;
    private javax.swing.JLabel lblFAQTitle1;
    private javax.swing.JLabel lblGettingStartedText;
    private javax.swing.JLabel lblGettingStartedText1;
    private javax.swing.JLabel lblGettingStartedTitle;
    private javax.swing.JLabel lblGettingStartedTitle1;
    private javax.swing.JLabel lblHelpTitle;
    private javax.swing.JLabel lblHelpTitle1;
    private javax.swing.JLabel lblPatientsHelp;
    private javax.swing.JLabel lblPatientsHelp1;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblSubtitle1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lblUsersHelp;
    private javax.swing.JLabel lblUsersHelp1;
    private javax.swing.JPanel pnlAppointmentsHelp;
    private javax.swing.JPanel pnlAppointmentsHelp1;
    private javax.swing.JPanel pnlBillingHelp;
    private javax.swing.JPanel pnlBillingHelp1;
    private javax.swing.JPanel pnlDentistTreatmentHelp;
    private javax.swing.JPanel pnlDentistTreatmentHelp1;
    private javax.swing.JPanel pnlFAQ;
    private javax.swing.JPanel pnlFAQ1;
    private javax.swing.JPanel pnlGettingStarted;
    private javax.swing.JPanel pnlGettingStarted1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlHeader1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMain1;
    private javax.swing.JPanel pnlPatientsHelp;
    private javax.swing.JPanel pnlPatientsHelp1;
    private javax.swing.JPanel pnlUsersHelp;
    private javax.swing.JPanel pnlUsersHelp1;
    private javax.swing.JLabel txtAppointmentsHelp;
    private javax.swing.JLabel txtAppointmentsHelp1;
    private javax.swing.JLabel txtBillingHelp;
    private javax.swing.JLabel txtBillingHelp1;
    private javax.swing.JLabel txtDentistTreatmentHelp;
    private javax.swing.JLabel txtDentistTreatmentHelp1;
    private javax.swing.JLabel txtPatientsHelp;
    private javax.swing.JLabel txtPatientsHelp1;
    private javax.swing.JLabel txtUsersHelp;
    private javax.swing.JLabel txtUsersHelp1;
    // End of variables declaration//GEN-END:variables
}
