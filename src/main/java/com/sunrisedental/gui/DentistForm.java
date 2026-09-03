/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.client.DentistApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunrisedental.model.Dentist;
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
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
/**
 *
 * @author iffah
 */
public class DentistForm extends javax.swing.JFrame {
    private User currentUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DentistForm.class.getName());

    /**
     * Creates new form DentistForm
     */
    public DentistForm() {
        initComponents();
    }
    
    public DentistForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;
    
    setupDentistUI();

        setLocationRelativeTo(null);

        txtDentistId.setEditable(false);

        loadDentists();
}
    
    private final DentistApiClient dentistApiClient =
        new DentistApiClient();

private final ObjectMapper objectMapper =
        new ObjectMapper()
                .findAndRegisterModules()
                .configure(
                        com.fasterxml.jackson.databind.DeserializationFeature
                                .FAIL_ON_UNKNOWN_PROPERTIES,
                        false
                );
    private void loadDentists() {

    try {

        HttpResponse<String> response =
                dentistApiClient.getAllDentists();

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

        DefaultTableModel model =
                (DefaultTableModel)
                tblDentists.getModel();

        model.setRowCount(0);

        for (Dentist dentist : dentists) {

            model.addRow(new Object[]{

                dentist.getDentistId(),
                dentist.getDentistName(),
                dentist.getContactNumber(),
                dentist.getSpecialization(),
                dentist.isActive()

            });
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
    
    private void setupDentistUI() {

    setTitle("Sunrise Dental Clinic - Dentist Management");
    getContentPane().setBackground(ColorTheme.BACKGROUND);

    styleDentistTitle();
    styleDentistLabels();
    styleDentistFields();
    styleDentistButtons();
    styleDentistTable();
    styleDentistCheckBox();
}
    
    private void styleDentistTitle() {

    jLabel1.setText("DENTIST MANAGEMENT");
    jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 26));
    jLabel1.setForeground(ColorTheme.PRIMARY_DARK);

    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setVerticalAlignment(SwingConstants.CENTER);
}
    
    private void styleDentistLabels() {

    styleDentistLabel(jLabel2);
    styleDentistLabel(jLabel3);
    styleDentistLabel(jLabel4);
    styleDentistLabel(jLabel5);
    styleDentistLabel(jLabel6);
}

private void styleDentistLabel(javax.swing.JLabel label) {

    label.setFont(
            new Font("Segoe UI", Font.BOLD, 13)
    );

    label.setForeground(ColorTheme.TEXT);
}

private void styleDentistFields() {

    styleDentistTextField(txtDentistId);
    styleDentistTextField(txtDentistName);
    styleDentistTextField(txtDentistContact);
    styleDentistTextField(txtSpecialization);

    // Auto-generated ID
    txtDentistId.setBackground(
            ColorTheme.LIGHT_TEAL
    );

    txtDentistId.setForeground(
            ColorTheme.SECONDARY_TEXT
    );
}

private void styleDentistTextField(JTextField field) {

    field.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    field.setForeground(ColorTheme.TEXT);
    field.setBackground(ColorTheme.WHITE);
    field.setCaretColor(ColorTheme.PRIMARY);

    field.setBorder(
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

private void styleDentistCheckBox() {

    chkActive.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    chkActive.setForeground(
            ColorTheme.TEXT
    );

    chkActive.setBackground(
            ColorTheme.BACKGROUND
    );

    chkActive.setFocusPainted(false);
}

private void styleDentistButtons() {

    styleDentistButton(
            btnSaveDentist,
            "SAVE",
            ColorTheme.PRIMARY,
            ColorTheme.WHITE
    );

    styleDentistButton(
            btnUpdateDentist,
            "UPDATE",
            ColorTheme.GOLD,
            ColorTheme.TEXT
    );

    styleDentistButton(
            btnClearDentist,
            "CLEAR",
            ColorTheme.SECONDARY_TEXT,
            ColorTheme.WHITE
    );

    styleDentistButton(
            btnBack,
            "←  BACK",
            ColorTheme.PRIMARY_DARK,
            ColorTheme.WHITE
    );
}

private void styleDentistButton(
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

private void styleDentistTable() {

    tblDentists.setFont(
            new Font("Segoe UI", Font.PLAIN, 12)
    );

    tblDentists.setForeground(
            ColorTheme.TEXT
    );

    tblDentists.setBackground(
            ColorTheme.WHITE
    );

    tblDentists.setRowHeight(30);

    tblDentists.setGridColor(
            ColorTheme.BORDER
    );

    tblDentists.setShowVerticalLines(false);
    tblDentists.setShowHorizontalLines(true);

    tblDentists.setSelectionBackground(
            ColorTheme.LIGHT_TEAL
    );

    tblDentists.setSelectionForeground(
            ColorTheme.TEXT
    );

    tblDentists.setFillsViewportHeight(true);
        
         JTableHeader header =
        tblDentists.getTableHeader();

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

    // Center Dentist ID
    DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();

    centerRenderer.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    tblDentists
            .getColumnModel()
            .getColumn(0)
            .setCellRenderer(centerRenderer);


    // Column widths
    tblDentists
            .getColumnModel()
            .getColumn(0)
            .setPreferredWidth(70);

    tblDentists
            .getColumnModel()
            .getColumn(1)
            .setPreferredWidth(150);

    tblDentists
            .getColumnModel()
            .getColumn(2)
            .setPreferredWidth(120);

    tblDentists
            .getColumnModel()
            .getColumn(3)
            .setPreferredWidth(160);

    tblDentists
            .getColumnModel()
            .getColumn(4)
            .setPreferredWidth(80);


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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDentistId = new javax.swing.JTextField();
        txtDentistName = new javax.swing.JTextField();
        txtDentistContact = new javax.swing.JTextField();
        txtSpecialization = new javax.swing.JTextField();
        chkActive = new javax.swing.JCheckBox();
        btnSaveDentist = new javax.swing.JButton();
        btnUpdateDentist = new javax.swing.JButton();
        btnClearDentist = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDentists = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DENTIST MANAGEMENT");

        jLabel2.setText("Dentist ID:");

        jLabel3.setText("Dentist Name: ");

        jLabel4.setText("Contact Number:");

        jLabel5.setText("Specialization:");

        jLabel6.setText("Active: ");

        txtDentistId.addActionListener(this::txtDentistIdActionPerformed);

        txtDentistContact.addActionListener(this::txtDentistContactActionPerformed);

        chkActive.addActionListener(this::chkActiveActionPerformed);

        btnSaveDentist.setText("SAVE");
        btnSaveDentist.addActionListener(this::btnSaveDentistActionPerformed);

        btnUpdateDentist.setText("UPDATE");
        btnUpdateDentist.addActionListener(this::btnUpdateDentistActionPerformed);

        btnClearDentist.setText("CLEAR");

        tblDentists.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Dentist  Id", "Dentist Name", "Contact", "Specialization", "Active"
            }
        ));
        tblDentists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDentistsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDentists);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSaveDentist)
                                .addGap(47, 47, 47)
                                .addComponent(btnUpdateDentist)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(btnClearDentist))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(txtSpecialization))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDentistName))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDentistId))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDentistContact)))
                        .addGap(30, 30, 30)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154)
                .addComponent(btnBack)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnBack)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDentistId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDentistName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(txtDentistContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(chkActive, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveDentist)
                            .addComponent(btnUpdateDentist)
                            .addComponent(btnClearDentist)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveDentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveDentistActionPerformed
        // TODO add your handling code here:
        try {

        Dentist dentist = new Dentist();

        dentist.setDentistName(
                txtDentistName.getText().trim()
        );

        dentist.setContactNumber(
                txtDentistContact.getText().trim()
        );

        dentist.setSpecialization(
                txtSpecialization.getText().trim()
        );

        dentist.setActive(
                chkActive.isSelected()
        );

        String json =
                objectMapper.writeValueAsString(
                        dentist
                );

        System.out.println(
                "CREATE DENTIST JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                dentistApiClient.createDentist(
                        json
                );

        if (response.statusCode() == 201) {

            Dentist createdDentist =
                    objectMapper.readValue(
                            response.body(),
                            Dentist.class
                    );

            txtDentistId.setText(
                    String.valueOf(
                            createdDentist.getDentistId()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Dentist saved successfully.\n"
                    + "Dentist ID: "
                    + createdDentist.getDentistId()
            );

            loadDentists();

        } else {

            showApiError(
                    response,
                    "Save Dentist Error"
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Save Dentist Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSaveDentistActionPerformed

    private void tblDentistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDentistsMouseClicked
        // TODO add your handling code here:
        try {

        int row =
                tblDentists.getSelectedRow();

        if (row == -1) {
            return;
        }

        int dentistId =
                Integer.parseInt(
                        tblDentists
                                .getValueAt(row, 0)
                                .toString()
                );

        HttpResponse<String> response =
                dentistApiClient.getDentist(
                        dentistId
                );

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Dentist Error"
            );

            return;
        }

        Dentist dentist =
                objectMapper.readValue(
                        response.body(),
                        Dentist.class
                );

        txtDentistId.setText(
                String.valueOf(
                        dentist.getDentistId()
                )
        );

        txtDentistName.setText(
                dentist.getDentistName()
        );

        txtDentistContact.setText(
                dentist.getContactNumber()
        );

        txtSpecialization.setText(
                dentist.getSpecialization()
        );

        chkActive.setSelected(
                dentist.isActive()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Dentist Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_tblDentistsMouseClicked

    private void btnUpdateDentistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDentistActionPerformed
        // TODO add your handling code here:
        try {

        if (txtDentistId.getText()
                .trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a dentist first."
            );

            return;
        }

        int dentistId =
                Integer.parseInt(
                        txtDentistId.getText().trim()
                );

        Dentist dentist = new Dentist();

        dentist.setDentistId(dentistId);

        dentist.setDentistName(
                txtDentistName.getText().trim()
        );

        dentist.setContactNumber(
                txtDentistContact.getText().trim()
        );

        dentist.setSpecialization(
                txtSpecialization.getText().trim()
        );

        dentist.setActive(
                chkActive.isSelected()
        );

        String json =
                objectMapper.writeValueAsString(
                        dentist
                );

        System.out.println(
                "UPDATE DENTIST JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                dentistApiClient.updateDentist(
                        dentistId,
                        json
                );

        if (response.statusCode() == 200) {

            JOptionPane.showMessageDialog(
                    this,
                    "Dentist updated successfully."
            );

            loadDentists();

        } else {

            showApiError(
                    response,
                    "Update Dentist Error"
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Invalid dentist ID.",
                "Update Dentist Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Update Dentist Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnUpdateDentistActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         goBackToDashboard();
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtDentistIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDentistIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDentistIdActionPerformed

    private void txtDentistContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDentistContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDentistContactActionPerformed

    private void chkActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkActiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkActiveActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DentistForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClearDentist;
    private javax.swing.JButton btnSaveDentist;
    private javax.swing.JButton btnUpdateDentist;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDentists;
    private javax.swing.JTextField txtDentistContact;
    private javax.swing.JTextField txtDentistId;
    private javax.swing.JTextField txtDentistName;
    private javax.swing.JTextField txtSpecialization;
    // End of variables declaration//GEN-END:variables
}
