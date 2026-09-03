/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.client.AuthApiClient;
import com.sunrisedental.model.User;
import javax.swing.JOptionPane;
import com.sunrisedental.util.ColorTheme;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Component;

import java.net.http.HttpResponse;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author iffah
 */
public class LoginForm extends javax.swing.JFrame {
    private final AuthApiClient authApiClient =
        new AuthApiClient();

    private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginForm.class.getName());

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        
        setupLoginUI();
        setupBrandQuote();

        setLocationRelativeTo(null);
    }
    
    private void setupBrandQuote() {

    lblBrandQuote.setText(
        "<html><div style='text-align:center;'>"
        + "\"Bright Smiles.<br>"
        + "Better Care.<br>"
        + "Every Day.\""
        + "</div></html>"
    );

    lblBrandQuote.setFont(
        new Font(
            "Segoe UI",
            Font.ITALIC,
            19
        )
    );

    lblBrandQuote.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    lblBrandQuote.setHorizontalAlignment(
        SwingConstants.CENTER
    );

    lblBrandQuote.setVerticalAlignment(
        SwingConstants.CENTER
    );

    lblBrandQuote.setOpaque(false);
}
    
    private void setupLoginUI() {

    // ==========================================
    // MAIN WINDOW
    // ==========================================

    getContentPane().setBackground(ColorTheme.BACKGROUND);

    setTitle("Sunrise Dental Clinic - Login");

    setResizable(false);


    // ==========================================
    // PANELS
    // ==========================================

    jPanel1.setBackground(ColorTheme.PRIMARY);
    jPanel2.setBackground(ColorTheme.BACKGROUND);

    // ==========================================
    // TITLE
    // ==========================================

    lblTitle.setForeground(ColorTheme.WHITE);

    lblTitle.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            24
        )
    );


    // ==========================================
    // FORM LABELS
    // ==========================================

    lblUsername.setForeground(ColorTheme.WHITE);

    lblPassword.setForeground(ColorTheme.WHITE);

    lblUsername.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            15
        )
    );

    lblPassword.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            15
        )
    );


    // ==========================================
    // TEXT FIELDS
    // ==========================================

    styleTextField(txtUsername);

    stylePasswordField(txtPassword);


    // ==========================================
    // LOGIN BUTTON
    // ==========================================

    stylePrimaryButton(
        btnLogin,
        ColorTheme.GOLD,
        ColorTheme.TEXT
    );


    // ==========================================
    // CLEAR BUTTON
    // ==========================================

    styleSecondaryButton(btnClear);


    // ==========================================
    // MESSAGE
    // ==========================================

    lblMessage.setForeground(
        ColorTheme.DANGER
    );

    lblMessage.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            13
        )
    );


    // ==========================================
    // LOGO
    // ==========================================

    jLabel1.setIcon(
    new ImageIcon(
        getClass().getResource("/images/sunrise_logo.png")
    )
);

    

    jLabel1.setText("");


    // ==========================================
    // FOCUS
    // ==========================================

    txtUsername.requestFocusInWindow();
    
    
}
    
    private void styleTextField(
        javax.swing.JTextField field) {

    field.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            15
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
    
    private void stylePasswordField(
        javax.swing.JPasswordField field) {

    field.setFont(
        new Font(
            "Segoe UI",
            Font.PLAIN,
            15
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
    
    private void stylePrimaryButton(
        javax.swing.JButton button,
        Color background,
        Color foreground) {

    button.setBackground(background);

    button.setForeground(foreground);

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
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
            10,
            25,
            10,
            25
        )
    );
}
    
    private void styleSecondaryButton(
        javax.swing.JButton button) {

    button.setBackground(
        ColorTheme.LIGHT_TEAL
    );

    button.setForeground(
        ColorTheme.PRIMARY_DARK
    );

    button.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            14
        )
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
            10,
            25,
            10,
            25
        )
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

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblBrandQuote = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setText("SUNRISE DENTAL CLINIC");

        lblUsername.setText("Username");

        txtUsername.addActionListener(this::txtUsernameActionPerformed);

        lblPassword.setText("Password");

        txtPassword.addActionListener(this::txtPasswordActionPerformed);

        btnLogin.setBackground(new java.awt.Color(0, 0, 255));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnClear.setText("CLEAR");
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear))
                            .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(lblTitle)
                .addGap(130, 130, 130)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnClear))
                .addGap(36, 36, 36)
                .addComponent(lblMessage)
                .addGap(100, 100, 100))
        );

        jLabel1.setText("jLabel1");

        lblBrandQuote.setText("“Bright Smiles.Better Care.Every Day.”");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblBrandQuote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBrandQuote, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        try {

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                );


        // ==========================================
        // BASIC VALIDATION
        // ==========================================

        if (username.isEmpty()) {

            lblMessage.setText(
                    "Username is required"
            );

            return;
        }

        if (password.isEmpty()) {

            lblMessage.setText(
                    "Password is required"
            );

            return;
        }


        // ==========================================
        // LOGIN THROUGH REST API
        // ==========================================

        HttpResponse<String> response =
                authApiClient.login(
                        username,
                        password
                );


        // ==========================================
        // SUCCESS
        // ==========================================

        if (response.statusCode() == 200) {

            User user =
                    objectMapper.readValue(
                            response.body(),
                            User.class
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful. Welcome "
                    + user.getFullName()
            );


            DashboardForm dashboard =
                    new DashboardForm(user);

            dashboard.setVisible(true);

            this.dispose();

        } else {

            lblMessage.setText(
                    "Invalid username or password"
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Login Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtUsername.setText("");
    txtPassword.setText("");
    lblMessage.setText("");

    txtUsername.requestFocus();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblBrandQuote;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
