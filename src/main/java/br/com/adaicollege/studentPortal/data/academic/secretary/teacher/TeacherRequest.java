package br.com.adaicollege.studentPortal.data.academic.secretary.teacher;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

public record TeacherRequest(
        String firstName,
        String familyName,
        String moduleNameId,
        CollegeCourse courseLectures
) { }
