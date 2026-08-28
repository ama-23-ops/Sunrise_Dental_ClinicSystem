package com.sunrisedental.util;

public final class NumberGenerator {
    private NumberGenerator(){}
    public static String appointmentNo(int id){ return String.format("APT-%04d", id); }
    public static String billNo(int id){ return String.format("BILL-%04d", id); }
}
