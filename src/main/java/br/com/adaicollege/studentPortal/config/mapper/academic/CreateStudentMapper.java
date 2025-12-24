package br.com.adaicollege.studentPortal.config.mapper.academic;

import br.com.adaicollege.studentPortal.data.academicDTO.CreateStudentDTO;
import br.com.adaicollege.studentPortal.model.academic.CreateStudent;

public class CreateStudentMapper {

    /*
        private String socialSecurityNumber;
        private String firstName;
        private String familyName;
        private String email;
        private LocalDate birthDate;
        private CollegeCourse courseEnrolled;
        private Modules moduleName; // first modulo enrolled (could be chosen after)
        private StudentStatus studentStatus; // active or not
        private LocalDateTime enrolledAt;
    */

    // ======================================================
    // DTO → Entity
    // ======================================================
    public static CreateStudent toEntity(CreateStudentDTO dto) {

        if (dto == null) return null;
        CreateStudent student = new CreateStudent();

        student.setId(dto.getId());
        student.setSocialSecurityNumber(dto.getSocialSecurityNumber());
        student.setFirstName(dto.getFirstName());
        student.setFamilyName(dto.getFamilyName());
        student.setEmail(dto.getEmail());
        student.setBirthDate(dto.getBirthDate());
        student.setCourseEnrolled(dto.getCourseEnrolled());
        student.setModuleName(dto.getModuleName());
        student.setStudentStatus(dto.getStudentStatus());
        student.setEnrolledAt(dto.getEnrolledAt());

        return student;
    }

    // ======================================================
    // Entity → DTO
    // ======================================================
    public static CreateStudentDTO toDTO(CreateStudent student) {

        if (student == null) return null;
        CreateStudentDTO dto = new CreateStudentDTO();

        dto.setId(student.getId());
        dto.setId(student.getId());
        dto.setSocialSecurityNumber(student.getSocialSecurityNumber());
        dto.setFirstName(student.getFirstName());
        dto.setFamilyName(student.getFamilyName());
        dto.setEmail(student.getEmail());
        dto.setBirthDate(student.getBirthDate());
        dto.setCourseEnrolled(student.getCourseEnrolled());
        dto.setModuleName(student.getModuleName());
        dto.setStudentStatus(student.getStudentStatus());
        dto.setEnrolledAt(student.getEnrolledAt());

        return dto;
    }


}
