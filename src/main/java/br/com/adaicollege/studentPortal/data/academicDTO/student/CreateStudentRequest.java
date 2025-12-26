package br.com.adaicollege.studentPortal.data.academicDTO.student;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public record CreateStudentRequest(
        String socialSecurityNumber,
        String firstName,
        String familyName,
        String email,
        LocalDate birthDate,
        CollegeCourse courseEnrolled,
        String moduleNameId
) { }
