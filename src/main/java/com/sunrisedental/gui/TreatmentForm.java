/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sunrisedental.gui;

import com.sunrisedental.client.TreatmentApiClient;
import com.sunrisedental.model.Treatment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author iffah
 */
public class TreatmentForm extends javax.swing.JFrame {
    private User currentUser;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TreatmentForm.class.getName());

    /**
     * Creates new form TreatmentForm
     */
    public TreatmentForm() {
        initComponents();
    }
    
    public TreatmentForm(User currentUser) {

    initComponents();

    this.currentUser = currentUser;
    setupTreatmentUI();

    setLocationRelativeTo(null);

        txtTreatmentId.setEditable(false);

        loadTreatments();
}
    
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
    
    private void loadTreatments() {

    try {

        HttpResponse<String> response =
                treatmentApiClient.getAllTreatments();

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

        DefaultTableModel model =
                (DefaultTableModel)
                tblTreatments.getModel();

        model.setRowCount(0);

        for (Treatment treatment : treatments) {

            model.addRow(new Object[]{

                treatment.getTreatmentId(),
                treatment.getTreatmentName(),
                treatment.getTreatmentCost(),
                treatment.getDescription(),
                treatment.isActive()

            });
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
    
    private void setupTreatmentUI() {

    setTitle("Sunrise Dental Clinic - Treatment Management");
    getContentPane().setBackground(ColorTheme.BACKGROUND);

    styleTreatmentTitle();
    styleTreatmentLabels();
    styleTreatmentFields();
    styleTreatmentButtons();
    styleTreatmentTable();
    styleTreatmentCheckBox();
}
    
    private void styleTreatmentTitle() {

    jLabel1.setText("TREATMENT MANAGEMENT");
    jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 26));
    jLabel1.setForeground(ColorTheme.PRIMARY_DARK);

    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setVerticalAlignment(SwingConstants.CENTER);
}
    
    private void styleTreatmentLabels() {

    styleTreatmentLabel(jLabel2);
    styleTreatmentLabel(jLabel3);
    styleTreatmentLabel(jLabel4);
    styleTreatmentLabel(jLabel5);
    styleTreatmentLabel(jLabel6);
}

private void styleTreatmentLabel(javax.swing.JLabel label) {

    label.setFont(new Font("Segoe UI", Font.BOLD, 13));
    label.setForeground(ColorTheme.TEXT);
}

private void styleTreatmentFields() {

    styleTreatmentTextField(txtTreatmentId);
    styleTreatmentTextField(txtTreatmentName);
    styleTreatmentTextField(txtTreatmentCost);

    // Auto-generated ID
    txtTreatmentId.setBackground(ColorTheme.LIGHT_TEAL);
    txtTreatmentId.setForeground(ColorTheme.SECONDARY_TEXT);

    // Description
    txtTreatmentDescription.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    txtTreatmentDescription.setForeground(ColorTheme.TEXT);
    txtTreatmentDescription.setBackground(ColorTheme.WHITE);

    txtTreatmentDescription.setLineWrap(true);
    txtTreatmentDescription.setWrapStyleWord(true);

    txtTreatmentDescription.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            ColorTheme.BORDER, 1
                    ),
                    new EmptyBorder(8, 10, 8, 10)
            )
    );

    jScrollPane1.setBorder(
            BorderFactory.createLineBorder(
                    ColorTheme.BORDER, 1
            )
    );

    jScrollPane1.getViewport().setBackground(
            ColorTheme.WHITE
    );
}

private void styleTreatmentTextField(JTextField field) {

    field.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    field.setForeground(ColorTheme.TEXT);
    field.setBackground(ColorTheme.WHITE);
    field.setCaretColor(ColorTheme.PRIMARY);

    field.setBorder(
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            ColorTheme.BORDER, 1
                    ),
                    new EmptyBorder(
                            7, 10, 7, 10
                    )
            )
    );
}

private void styleTreatmentCheckBox() {

    chkTreatmentActive.setFont(
            new Font("Segoe UI", Font.PLAIN, 13)
    );

    chkTreatmentActive.setForeground(
            ColorTheme.TEXT
    );

    chkTreatmentActive.setBackground(
            ColorTheme.BACKGROUND
    );

    chkTreatmentActive.setFocusPainted(false);
}

private void styleTreatmentButtons() {

    styleTreatmentButton(
            btnSaveTreatment,
            "SAVE",
            ColorTheme.PRIMARY,
            ColorTheme.WHITE
    );

    styleTreatmentButton(
            jButton2,
            "UPDATE",
            ColorTheme.GOLD,
            ColorTheme.TEXT
    );

    styleTreatmentButton(
            jButton3,
            "CLEAR",
            ColorTheme.SECONDARY_TEXT,
            ColorTheme.WHITE
    );

    styleTreatmentButton(
            btnBack,
            "←  BACK",
            ColorTheme.PRIMARY_DARK,
            ColorTheme.WHITE
    );
}

private void styleTreatmentButton(
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

private void styleTreatmentTable() {

    tblTreatments.setFont(
            new Font("Segoe UI", Font.PLAIN, 12)
    );

    tblTreatments.setForeground(
            ColorTheme.TEXT
    );

    tblTreatments.setBackground(
            ColorTheme.WHITE
    );

    tblTreatments.setRowHeight(30);

    tblTreatments.setGridColor(
            ColorTheme.BORDER
    );

    tblTreatments.setShowVerticalLines(false);
    tblTreatments.setShowHorizontalLines(true);

    tblTreatments.setSelectionBackground(
            ColorTheme.LIGHT_TEAL
    );

    tblTreatments.setSelectionForeground(
            ColorTheme.TEXT
    );

    tblTreatments.setFillsViewportHeight(true);


    JTableHeader header =
        tblTreatments.getTableHeader();

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

    // Center Treatment ID
    DefaultTableCellRenderer centerRenderer =
            new DefaultTableCellRenderer();

    centerRenderer.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    tblTreatments
            .getColumnModel()
            .getColumn(0)
            .setCellRenderer(centerRenderer);


    // Column widths
    tblTreatments
            .getColumnModel()
            .getColumn(0)
            .setPreferredWidth(70);

    tblTreatments
            .getColumnModel()
            .getColumn(1)
            .setPreferredWidth(130);

    tblTreatments
            .getColumnModel()
            .getColumn(2)
            .setPreferredWidth(90);

    tblTreatments
            .getColumnModel()
            .getColumn(3)
            .setPreferredWidth(220);

    tblTreatments
            .getColumnModel()
            .getColumn(4)
            .setPreferredWidth(100);


    jScrollPane2.setBorder(
            BorderFactory.createLineBorder(
                    ColorTheme.BORDER,
                    1
            )
    );

    jScrollPane2.getViewport().setBackground(
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
        btnSaveTreatment = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtTreatmentId = new javax.swing.JTextField();
        txtTreatmentName = new javax.swing.JTextField();
        txtTreatmentCost = new javax.swing.JTextField();
        chkTreatmentActive = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTreatmentDescription = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTreatments = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("TREATMENT MANAGEMENT ");

        jLabel2.setText("Treatment ID:");

        jLabel3.setText("Treatment Name:");

        jLabel4.setText("Cost: ");

        jLabel5.setText("Description: ");

        jLabel6.setText("Active:  ");

        btnSaveTreatment.setText("SAVE");
        btnSaveTreatment.addActionListener(this::btnSaveTreatmentActionPerformed);

        jButton2.setText("UPDATE");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("CLEAR");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        txtTreatmentDescription.setColumns(20);
        txtTreatmentDescription.setRows(5);
        jScrollPane1.setViewportView(txtTreatmentDescription);

        tblTreatments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Treatment Id", "Treatment Name", "Cost", "Description", "Active Status"
            }
        ));
        tblTreatments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTreatmentsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTreatments);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(txtTreatmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTreatmentCost, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSaveTreatment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(chkTreatmentActive)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnBack)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTreatmentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTreatmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTreatmentCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(chkTreatmentActive))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveTreatment)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveTreatmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTreatmentActionPerformed
        // TODO add your handling code here:
        try {

        String name =
                txtTreatmentName.getText().trim();

        String costText =
                txtTreatmentCost.getText().trim();

        if (name.isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment name is required."
            );
        }

        if (costText.isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment cost is required."
            );
        }

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentName(name);

        treatment.setTreatmentCost(
                new java.math.BigDecimal(
                        costText
                )
        );

        treatment.setDescription(
                txtTreatmentDescription
                        .getText()
                        .trim()
        );

        treatment.setActive(
                chkTreatmentActive.isSelected()
        );

        String json =
                objectMapper.writeValueAsString(
                        treatment
                );

        System.out.println(
                "CREATE TREATMENT JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                treatmentApiClient.createTreatment(
                        json
                );

        if (response.statusCode() == 201) {

            Treatment createdTreatment =
                    objectMapper.readValue(
                            response.body(),
                            Treatment.class
                    );

            txtTreatmentId.setText(
                    String.valueOf(
                            createdTreatment
                                    .getTreatmentId()
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment saved successfully.\n"
                    + "Treatment ID: "
                    + createdTreatment.getTreatmentId()
            );

            loadTreatments();

        } else {

            showApiError(
                    response,
                    "Save Treatment Error"
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment cost must be a valid number.",
                "Save Treatment Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Save Treatment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnSaveTreatmentActionPerformed

    private void tblTreatmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTreatmentsMouseClicked
        // TODO add your handling code here:
        try {

        int row =
                tblTreatments.getSelectedRow();

        if (row == -1) {
            return;
        }

        int treatmentId =
                Integer.parseInt(
                        tblTreatments
                                .getValueAt(row, 0)
                                .toString()
                );

        HttpResponse<String> response =
                treatmentApiClient.getTreatment(
                        treatmentId
                );

        if (response.statusCode() != 200) {

            showApiError(
                    response,
                    "Treatment Error"
            );

            return;
        }

        Treatment treatment =
                objectMapper.readValue(
                        response.body(),
                        Treatment.class
                );

        txtTreatmentId.setText(
                String.valueOf(
                        treatment.getTreatmentId()
                )
        );

        txtTreatmentName.setText(
                treatment.getTreatmentName()
        );

        txtTreatmentCost.setText(
                treatment.getTreatmentCost()
                        .toPlainString()
        );

        txtTreatmentDescription.setText(
                treatment.getDescription() == null
                        ? ""
                        : treatment.getDescription()
        );

        chkTreatmentActive.setSelected(
                treatment.isActive()
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Treatment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_tblTreatmentsMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {

        if (txtTreatmentId.getText()
                .trim()
                .isEmpty()) {

            throw new IllegalArgumentException(
                    "Please select a treatment first."
            );
        }

        String name =
                txtTreatmentName.getText().trim();

        String costText =
                txtTreatmentCost.getText().trim();

        if (name.isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment name is required."
            );
        }

        if (costText.isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment cost is required."
            );
        }

        int treatmentId =
                Integer.parseInt(
                        txtTreatmentId.getText()
                                .trim()
                );

        Treatment treatment =
                new Treatment();

        treatment.setTreatmentId(
                treatmentId
        );

        treatment.setTreatmentName(name);

        treatment.setTreatmentCost(
                new java.math.BigDecimal(
                        costText
                )
        );

        treatment.setDescription(
                txtTreatmentDescription
                        .getText()
                        .trim()
        );

        treatment.setActive(
                chkTreatmentActive.isSelected()
        );

        String json =
                objectMapper.writeValueAsString(
                        treatment
                );

        System.out.println(
                "UPDATE TREATMENT JSON:"
        );

        System.out.println(json);

        HttpResponse<String> response =
                treatmentApiClient.updateTreatment(
                        treatmentId,
                        json
                );

        if (response.statusCode() == 200) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment updated successfully."
            );

            loadTreatments();

        } else {

            showApiError(
                    response,
                    "Update Treatment Error"
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Treatment ID or cost is invalid.",
                "Update Treatment Error",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Update Treatment Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    txtTreatmentId.setText("");
    txtTreatmentName.setText("");
    txtTreatmentCost.setText("");
    txtTreatmentDescription.setText("");
    chkTreatmentActive.setSelected(true);

    tblTreatments.clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         goBackToDashboard();
    }//GEN-LAST:event_btnBackActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TreatmentForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSaveTreatment;
    private javax.swing.JCheckBox chkTreatmentActive;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTreatments;
    private javax.swing.JTextField txtTreatmentCost;
    private javax.swing.JTextArea txtTreatmentDescription;
    private javax.swing.JTextField txtTreatmentId;
    private javax.swing.JTextField txtTreatmentName;
    // End of variables declaration//GEN-END:variables
}
