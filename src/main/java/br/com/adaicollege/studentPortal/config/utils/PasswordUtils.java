package br.com.adaicollege.studentPortal.config.utils;

import java.time.LocalDate;

public class PasswordUtils {
    public static String defaultPassword(String registrationNumber, String course) {

        if (registrationNumber == null || registrationNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid registration number");
        }

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        // course enrolled
        String courseEnrolled = course.substring(
                // change this logic
                course.length() - 2
        );

        String lastDigits = registrationNumber.substring(
                registrationNumber.length() - 4
        );

        return year + String.format("%02d", month) + lastDigits;
    }
}
