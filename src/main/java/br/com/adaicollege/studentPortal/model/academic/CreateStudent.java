package br.com.adaicollege.studentPortal.model.academic;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateStudent {

    // @Id
    private String id;

    private String socialSecurityNumber;
    private String firstName;
    private String familyName;
    private String email;
    private LocalDate birthDate;
    private CollegeCourse courseEnrolled;
    private Modules moduleName;
    private StudentStatus studentStatus; // active or not

    private LocalDateTime enrolledAt;

    public CreateStudent() {}

}
