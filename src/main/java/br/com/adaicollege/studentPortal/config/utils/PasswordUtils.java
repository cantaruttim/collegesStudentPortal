package br.com.adaicollege.studentPortal.config.utils;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public class PasswordUtils {
    public static String defaultPassword(String registrationNumber, String course) {

        if (registrationNumber == null || registrationNumber.length() < 4) {
            throw new IllegalArgumentException("Invalid registration number");
        }

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        CollegeCourse collegeCourse = CollegeCourse.valueOf(course);

        String courseCode;
        if (collegeCourse == CollegeCourse.COLLEGE_INTENSIVE) {
            courseCode = "TI";
        } else if (collegeCourse == CollegeCourse.COLLEGE_MASTER) {
            courseCode = "TM";
        } else if (collegeCourse == CollegeCourse.COLLEGE_COORP) {
            courseCode = "TC";
        } else {
            throw new RuntimeException("Course not enabled");
        }

        String lastDigits = registrationNumber.substring(registrationNumber.length() - 4);

        // study the best way to concat this.
        return year + courseCode + String.format("%02d", month) + lastDigits;
    }
}
