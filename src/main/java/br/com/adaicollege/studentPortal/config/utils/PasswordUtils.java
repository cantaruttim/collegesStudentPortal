package br.com.adaicollege.studentPortal.config.utils;

import java.time.LocalDate;

public class PasswordUtils {
    public static String defaultPassword(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid registration number");
        }

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        String lastDigits = registrationNumber.substring(
                registrationNumber.length() - 4
        );

        return year + String.format("%02d", month) + lastDigits;
    }
}
