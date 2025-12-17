package br.com.adaicollege.studentPortal.model.academic;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public class Modules {

    // @Id
    private String id;
    private String moduleName;
    private String moduleDescription;
    private Teacher teacherName;
    private CollegeCourse course;
    private Integer quantityClasses;
    private LocalDate startDate;
    private LocalDate endDate;

    public Modules() {}

}
