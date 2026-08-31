package com.sunrisedental.util;
import java.time.LocalDate;

public final class Validator {
    private Validator(){}
    public static void require(String value, String field){
        if(value == null || value.trim().isEmpty()) throw new IllegalArgumentException(field + " is required.");
    }
    public static boolean validPhone(String value){ return value != null && value.matches("^[+0-9][0-9 -]{6,18}$"); }
    public static boolean validEmail(String value){ return value == null || value.isBlank() || value.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"); }
    public static boolean validFutureOrToday(LocalDate date){ return date != null && !date.isBefore(LocalDate.now()); }
}
