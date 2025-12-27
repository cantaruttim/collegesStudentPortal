package br.com.adaicollege.studentPortal.data.academic.student;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public record UpdateStudentRequest(
        String firstName,
        String familyName,
        String email,
        LocalDate birthDate,
        CollegeCourse courseEnrolled,
        String moduleNameId
) { }
