package br.com.adaicollege.studentPortal.data.academic.secretary.modules;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public record ModulesUpdate(
        String moduleName,
        String moduleDescription,
        String teacherId,
        CollegeCourse course,
        Integer quantityClasses, // must be automatically calculated
        LocalDate startDate

) { }
