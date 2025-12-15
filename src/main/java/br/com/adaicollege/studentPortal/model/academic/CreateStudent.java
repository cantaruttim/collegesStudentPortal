package br.com.adaicollege.studentPortal.model.academic;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateStudent {

    // @Id
    private String id;

    private String registrationNumber;
    private String firstName;
    private String familyName;
    private String email;
    private LocalDate birthDate;
    private String courseEnrolled;
    private String studentStatus; // active or not

    private LocalDateTime enrolledAt;

    public CreateStudent() {}

}
