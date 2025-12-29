package br.com.adaicollege.studentPortal.data.academic.secretary.teacher;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

public record UpdateTeacherRequest(
        String firstName,
        String familyName,
        String moduleNameId,
        CollegeCourse courseLectures
) { }
