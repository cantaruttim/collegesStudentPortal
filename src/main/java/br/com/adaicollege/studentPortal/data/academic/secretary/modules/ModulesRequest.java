package br.com.adaicollege.studentPortal.data.academic.secretary.modules;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public record ModulesRequest(
    String moduleName,
    String moduleDescription,
    String teacherId,
    CollegeCourse course,
    Integer quantityClasses,
    LocalDate startDate,
    LocalDate endDate
) { }

