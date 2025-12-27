package br.com.adaicollege.studentPortal.data.academic.student;

import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponse(
        String id,
        String socialSecurityNumber,
        String firstName,
        String familyName,
        String email,
        LocalDate birthDate,
        CollegeCourse courseEnrolled,
        String moduleNameId,
        StudentStatus studentStatus,
        LocalDateTime enrolledAt
) {
    public StudentResponse(CreateStudent student) {
        this(
                student.getId(),
                student.getSocialSecurityNumber(),
                student.getFirstName(),
                student.getFamilyName(),
                student.getEmail(),
                student.getBirthDate(),
                student.getCourseEnrolled(),
                student.getModuleNameId(),
                student.getStudentStatus(),
                student.getEnrolledAt()
        );
    }
}
