package br.com.adaicollege.studentPortal.data.academic.secretary.modules;

import br.com.adaicollege.studentPortal.model.academic.secretary.Modules;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public record ModulesResponse(
        String id,
        String moduleName,
        String moduleDescription,
        String teacherId,
        CollegeCourse course,
        Integer quantityClasses,
        LocalDate startDate,
        LocalDate endDate
) {

    public ModulesResponse(Modules module) {
        this(
            module.getId(),
            module.getModuleName(),
            module.getModuleDescription(),
            module.getTeacherId(),
            module.getCourse(),
            module.getQuantityClasses(),
            module.getStartDate(),
            module.getEndDate()
        );
    }
}
