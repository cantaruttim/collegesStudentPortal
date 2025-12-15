package br.com.adaicollege.studentPortal.model.forms;

import br.com.adaicollege.studentPortal.model.academic.Modules;
import br.com.adaicollege.studentPortal.model.academic.Teachers;

import java.time.LocalDateTime;

public class StudentsActivityForms {
    // represents the data after each class

    // @Id
    private String id;
    private String registrationNumber;
    private String fullName;
    private String email;

    private Modules moduleId;
    private Teachers teacherId;

    private String firstQuestion;
    private String secondQuestion;

    private LocalDateTime createdAt;

    public StudentsActivityForms() {}

}
