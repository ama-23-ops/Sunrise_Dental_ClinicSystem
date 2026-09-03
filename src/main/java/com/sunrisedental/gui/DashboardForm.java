/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sunrisedental.model.User;
import com.sunrisedental.util.SessionManager;
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
import com.sunrisedental.util.ColorTheme;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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
    
    setupUI();

    setupUserAccess();
    
    setupTodayAppointmentsTable();
    
    loadDashboardStats();
    
    loadTodayAppointments();
    
    setLocationRelativeTo(null);
}
    
private void setupUI() {

    setTitle(
        "Sunrise Dental Clinic - Dashboard"
    );

    setResizable(false);

    getContentPane().setBackground(
        ColorTheme.BACKGROUND
    );

    /*
     * Rebuild the sidebar visually.
     * The existing buttons and their
     * action listeners are retained.
     */
    setupSidebar();

    /*
     * Main content area
     */
    pnlContent.setBackground(
        ColorTheme.BACKGROUND
    );

    /*
     * Header
     */
    styleHeader();

    /*
     * Statistic cards
     */
    styleStatCards();

    /*
     * Appointment section
     */
    styleAppointmentsTable();

    /*
     * Scroll pane
     */
    jScrollPane1.setBorder(
        BorderFactory.createLineBorder(
            ColorTheme.BORDER,
            1
        )
    );

    jScrollPane1.getViewport()
        .setBackground(
            ColorTheme.WHITE
        );
}

private void setupSidebar() {

    /*
     * Remove the old GroupLayout arrangement.
     * The buttons themselves are NOT deleted,
     * so their action listeners remain intact.
     */
    pnlMenu.removeAll();

    pnlMenu.setLayout(
        new BorderLayout()
    );

    pnlMenu.setBackground(
        ColorTheme.PRIMARY_DARK
    );


    // ==========================================
    // BRANDING
    // ==========================================

    JPanel brandPanel =
            new JPanel();

    brandPanel.setBackground(
        ColorTheme.PRIMARY_DARK
    );

    brandPanel.setLayout(
        new BoxLayout(
            brandPanel,
            BoxLayout.Y_AXIS
        )
    );

    brandPanel.setBorder(
        new EmptyBorder(
            22,
            20,
            20,
            20
        )
    );


    JLabel brandName =
            new JLabel();

    brandName.setText(
        "<html>"
        + "<span style='color:#F4B942;'>"
        + "SUNRISE"
        + "</span> "
        + "<span style='color:#FFFFFF;'>"
        + "DENTAL CLINIC"
        + "</span>"
        + "</html>"
    );

    brandName.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            19
        )
    );

    brandName.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );


    JLabel brandSubtitle =
            new JLabel(
                "Clinic Management System"
            );

    brandSubtitle.setForeground(
        ColorTheme.LIGHT_TEAL
    );

    brandSubtitle.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            11
        )
    );

    brandSubtitle.setAlignmentX(
        Component.CENTER_ALIGNMENT
    );


    brandPanel.add(
        brandName
    );

    brandPanel.add(
        Box.createVerticalStrut(5)
    );

    brandPanel.add(
        brandSubtitle
    );


    pnlMenu.add(
        brandPanel,
        BorderLayout.NORTH
    );


    // ==========================================
    // MENU BUTTON PANEL
    // ==========================================

    JPanel menuPanel =
            new JPanel();

    menuPanel.setBackground(
        ColorTheme.PRIMARY_DARK
    );

    menuPanel.setLayout(
        new BoxLayout(
            menuPanel,
            BoxLayout.Y_AXIS
        )
    );

    menuPanel.setBorder(
        new EmptyBorder(
            10,
            15,
            10,
            15
        )
    );


    // Dashboard
    styleMenuButton(
        jButton1,
        "Dashboard",
        "/images/dashboard.png",
        true
    );

    // Patients
    styleMenuButton(
        btnPatients,
        "Patients",
        "/images/patients.png",
        false
    );

    // Appointments
    styleMenuButton(
        btnAppointments,
        "Appointments",
        "/images/appointments.png",
        false
    );

    // Billing
    styleMenuButton(
        btnBilling,
        "Billing",
        "/images/billing.png",
        false
    );

    // Reports
    styleMenuButton(
        btnReports,
        "Reports",
        "/images/reports.png",
        false
    );

    // Dentists
    styleMenuButton(
        btnDentists,
        "Dentists",
        "/images/dentist.png",
        false
    );

    // Treatments
    styleMenuButton(
        btnTreatments,
        "Treatments",
        "/images/treatment.png",
        false
    );

    // Users
    styleMenuButton(
        btnUsers,
        "Users",
        "/images/users.png",
        false
    );


    menuPanel.add(jButton1);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnPatients);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnAppointments);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnBilling);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnReports);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnDentists);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnTreatments);
    menuPanel.add(Box.createVerticalStrut(8));

    menuPanel.add(btnUsers);


    pnlMenu.add(
        menuPanel,
        BorderLayout.CENTER
    );


    // ==========================================
    // LOGOUT
    // ==========================================

    JPanel logoutPanel =
            new JPanel();

    logoutPanel.setBackground(
        ColorTheme.PRIMARY_DARK
    );

    logoutPanel.setBorder(
        new EmptyBorder(
            10,
            15,
            20,
            15
        )
    );

    logoutPanel.setLayout(
        new BorderLayout()
    );

    styleLogoutButton(
        btnLogout
    );

    logoutPanel.add(
        btnLogout,
        BorderLayout.CENTER
    );

    pnlMenu.add(
        logoutPanel,
        BorderLayout.SOUTH
    );


    pnlMenu.revalidate();

    pnlMenu.repaint();
}
    
    private void styleStatCard(
        javax.swing.JPanel panel,
        javax.swing.JLabel title,
        javax.swing.JLabel subtitle,
        javax.swing.JLabel value,
        String titleText,
        String subtitleText) {

    panel.setBackground(
        ColorTheme.WHITE
    );

    panel.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                ColorTheme.BORDER,
                1
            ),
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        )
    );

    title.setText(titleText);

    title.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    title.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
    );

    subtitle.setText(subtitleText);

    subtitle.setForeground(
        ColorTheme.SECONDARY_TEXT
    );

    subtitle.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            12
        )
    );

    value.setForeground(
        ColorTheme.TEXT
    );

    value.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            28
        )
    );

    value.setHorizontalAlignment(
        SwingConstants.LEFT
    );
}
    
    private void styleRevenueCard() {

    jPanel3.setBackground(
        ColorTheme.WHITE
    );

    jPanel3.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                ColorTheme.BORDER,
                1
            ),
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        )
    );

    jLabel4.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    jLabel4.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
    );

    jLabel7.setForeground(
        ColorTheme.SECONDARY_TEXT
    );

    jLabel7.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            12
        )
    );

    jLabel8.setForeground(
        ColorTheme.GOLD
    );

    jLabel8.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            16
        )
    );

    lblRevenue.setForeground(
        ColorTheme.TEXT
    );

    lblRevenue.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            22
        )
    );
}
    
    private void styleMenuButton(
        JButton button,
        boolean selected) {

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            13
        )
    );

    button.setForeground(
        ColorTheme.WHITE
    );

    if (selected) {

        button.setBackground(
            ColorTheme.PRIMARY
        );

    } else {

        button.setBackground(
            ColorTheme.PRIMARY_DARK
        );
    }

    button.setFocusPainted(false);

    button.setBorderPainted(false);

    button.setOpaque(true);

    button.setHorizontalAlignment(
        SwingConstants.LEFT
    );

    button.setBorder(
        new EmptyBorder(
            10,
            15,
            10,
            15
        )
    );

    button.setCursor(
        new Cursor(
            Cursor.HAND_CURSOR
        )
    );
}
    
    private void styleLogoutButton(
        JButton button) {

    button.setText(
        "Logout"
    );

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            13
        )
    );

    button.setForeground(
        ColorTheme.WHITE
    );

    button.setBackground(
        ColorTheme.DANGER
    );

    button.setIcon(
        loadIcon(
            "/images/logout.png",
            22,
            22
        )
    );

    button.setIconTextGap(14);

    button.setFocusPainted(false);

    button.setBorderPainted(false);

    button.setOpaque(true);

    button.setHorizontalAlignment(
        SwingConstants.LEFT
    );

    button.setBorder(
        new EmptyBorder(
            11,
            15,
            11,
            15
        )
    );

    button.setMaximumSize(
        new Dimension(
            Integer.MAX_VALUE,
            48
        )
    );

    button.setCursor(
        new Cursor(
            Cursor.HAND_CURSOR
        )
    );
}
    
    private void styleMenuButton(
        JButton button,
        String text,
        String iconPath,
        boolean selected) {

    button.setText(text);

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            13
        )
    );

    button.setForeground(
        ColorTheme.WHITE
    );

    button.setBackground(
        selected
            ? ColorTheme.PRIMARY
            : ColorTheme.PRIMARY_DARK
    );

    button.setFocusPainted(false);

    button.setBorderPainted(false);

    button.setOpaque(true);

    button.setHorizontalAlignment(
        SwingConstants.LEFT
    );

    button.setIcon(
        loadIcon(
            iconPath,
            22,
            22
        )
    );

    button.setIconTextGap(14);

    button.setBorder(
        new EmptyBorder(
            11,
            15,
            11,
            15
        )
    );

    button.setMaximumSize(
        new Dimension(
            Integer.MAX_VALUE,
            48
        )
    );

    button.setAlignmentX(
        Component.LEFT_ALIGNMENT
    );

    button.setCursor(
        new Cursor(
            Cursor.HAND_CURSOR
        )
    );
}
    private ImageIcon loadIcon(
        String path,
        int width,
        int height) {

    java.net.URL resource =
            getClass().getResource(path);

    if (resource == null) {

        System.out.println(
            "Icon not found: "
            + path
        );

        return null;
    }

    ImageIcon original =
            new ImageIcon(resource);

    Image image =
            original.getImage()
                    .getScaledInstance(
                        width,
                        height,
                        Image.SCALE_SMOOTH
                    );

    return new ImageIcon(image);
}
    
    private void styleHeader() {

    lblTitle.setText(
        "<html>"
        + "<span style='color:#F4B942;'>"
        + "SUNRISE"
        + "</span> "
        + "<span style='color:#167D8D;'>"
        + "DENTAL CLINIC"
        + "</span>"
        + "</html>"
    );

    lblTitle.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            24
        )
    );

    lblTitle.setForeground(
        ColorTheme.PRIMARY_DARK
    );


    lblWelcome.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            14
        )
    );

    lblWelcome.setForeground(
        ColorTheme.SECONDARY_TEXT
    );


    lblWelcome.setHorizontalAlignment(
        SwingConstants.RIGHT
    );
}
    
    private void styleStatCards() {

    styleCard(
        jPanel1,
        jLabel1,
        jLabel5,
        lblTotalPatients,
        "PATIENTS",
        "Total registered patients"
    );

    styleCard(
        jPanel2,
        jLabel2,
        jLabel6,
        lblTodayAppointments,
        "APPOINTMENTS",
        "Scheduled for today"
    );


    // ==========================================
    // REVENUE CARD
    // ==========================================

    jPanel3.setBackground(
        ColorTheme.WHITE
    );

    jPanel3.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                ColorTheme.BORDER,
                1
            ),
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        )
    );

    jLabel4.setText("REVENUE");

    jLabel4.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    jLabel4.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
    );

    jLabel7.setText("Today");

    jLabel7.setForeground(
        ColorTheme.SECONDARY_TEXT
    );

    jLabel7.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            12
        )
    );

    jLabel8.setForeground(
        ColorTheme.GOLD
    );

    jLabel8.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            16
        )
    );

    lblRevenue.setForeground(
        ColorTheme.TEXT
    );

    lblRevenue.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            23
        )
    );
}
    
    private void styleCard(
        JPanel panel,
        JLabel title,
        JLabel subtitle,
        JLabel value,
        String titleText,
        String subtitleText) {

    panel.setBackground(
        ColorTheme.WHITE
    );

    panel.setBorder(
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                ColorTheme.BORDER,
                1
            ),
            new EmptyBorder(
                15,
                18,
                15,
                18
            )
        )
    );


    title.setText(
        titleText
    );

    title.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    title.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
    );


    subtitle.setText(
        subtitleText
    );

    subtitle.setForeground(
        ColorTheme.SECONDARY_TEXT
    );

    subtitle.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            12
        )
    );


    value.setForeground(
        ColorTheme.TEXT
    );

    value.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            28
        )
    );
}
    
    private void styleAppointmentsTable() {

    tblTodayAppointments.setBackground(
        ColorTheme.WHITE
    );

    tblTodayAppointments.setForeground(
        ColorTheme.TEXT
    );

    tblTodayAppointments.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            12
        )
    );

    tblTodayAppointments.setRowHeight(
        30
    );

    tblTodayAppointments.setGridColor(
        ColorTheme.BORDER
    );

    tblTodayAppointments.setShowVerticalLines(
        false
    );

    tblTodayAppointments.setSelectionBackground(
        ColorTheme.LIGHT_TEAL
    );

    tblTodayAppointments.setSelectionForeground(
        ColorTheme.TEXT
    );


    JTableHeader header =
        tblTodayAppointments.getTableHeader();

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
        SwingConstants.CENTER
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


    DefaultTableCellRenderer renderer =
            new DefaultTableCellRenderer();

    renderer.setHorizontalAlignment(
        SwingConstants.CENTER
    );

    renderer.setForeground(
        ColorTheme.TEXT
    );

    renderer.setBackground(
        ColorTheme.WHITE
    );

    tblTodayAppointments.setDefaultRenderer(
        Object.class,
        renderer
    );
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalPatients = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTodayAppointments = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblRevenue = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTodayAppointments = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenu.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTreatments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDentists, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBilling, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAppointments, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPatients, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPatients)
                .addGap(30, 30, 30)
                .addComponent(btnAppointments)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnBilling)
                .addGap(18, 18, 18)
                .addComponent(btnReports)
                .addGap(18, 18, 18)
                .addComponent(btnDentists)
                .addGap(18, 18, 18)
                .addComponent(btnTreatments)
                .addGap(18, 18, 18)
                .addComponent(btnUsers)
                .addGap(45, 45, 45)
                .addComponent(btnLogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 715));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel9.setText("Today's Appointments                ");

        lblWelcome.setText("Welcome, Administrator");

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

        jScrollPane2.setViewportView(jScrollPane1);

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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
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

        jLabel3.setText("DASHBOARD OVERVIEW ");

        lblTitle.setText("SUNRISE DENTAL CLINIC");

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9))
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(54, Short.MAX_VALUE))))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWelcome))
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(71, 71, 71)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 0, 850, -1));

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
    private javax.swing.JScrollPane jScrollPane2;
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
