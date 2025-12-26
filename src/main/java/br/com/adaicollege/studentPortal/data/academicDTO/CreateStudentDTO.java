package br.com.adaicollege.studentPortal.data.academicDTO;

import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateStudentDTO(
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
    public CreateStudentDTO(CreateStudent entity) {
        this(
                entity.getId(),
                entity.getSocialSecurityNumber(),
                entity.getFirstName(),
                entity.getFamilyName(),
                entity.getEmail(),
                entity.getBirthDate(),
                entity.getCourseEnrolled(),
                entity.getModuleNameId(),
                entity.getStudentStatus(),
                entity.getEnrolledAt()
        );
    }
}
