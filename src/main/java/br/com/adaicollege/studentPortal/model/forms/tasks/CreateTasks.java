package br.com.adaicollege.studentPortal.model.forms.tasks;

import java.time.LocalDate;

public class CreateTasks {

    // create by teacher, used by students

    private String id;
    private String title;
    private String subtitle;
    private String description;
    private String teacherId;
    private String moduleId;
    private LocalDate createAt;
    private LocalDate expireAt;
    private String responseBy;

}
