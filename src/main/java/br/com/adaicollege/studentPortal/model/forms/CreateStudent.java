package br.com.adaicollege.studentPortal.model.forms;

import java.time.LocalDate;

public class CreateStudent {

    private String registrationNumber;
    private String firstName;
    private String familyName;
    private String email;
    private LocalDate birthDate;
    private String courseEnrolled;
    private String studentStatus; // active or not

    public CreateStudent() {}

}
