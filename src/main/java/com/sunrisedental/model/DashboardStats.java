/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sunrisedental.model;

import java.math.BigDecimal;

public class DashboardStats {

    private int totalPatients;
    private int todayAppointments;
    private BigDecimal todayRevenue;

    public DashboardStats() {
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTodayAppointments() {
        return todayAppointments;
    }

    public void setTodayAppointments(int todayAppointments) {
        this.todayAppointments = todayAppointments;
    }

    public BigDecimal getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(
            BigDecimal todayRevenue) {

        this.todayRevenue = todayRevenue;
    }
}
