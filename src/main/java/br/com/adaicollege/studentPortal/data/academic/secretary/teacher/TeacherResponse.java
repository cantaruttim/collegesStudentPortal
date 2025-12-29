package br.com.adaicollege.studentPortal.data.academic.secretary.teacher;

import br.com.adaicollege.studentPortal.model.academic.secretary.Teacher;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

public record TeacherResponse(
    String id,
    String firstName,
    String familyName,
    String moduleNameId,
    CollegeCourse courseLectures
) {
    public TeacherResponse(Teacher teacher) {
        this(
            teacher.getId(),
            teacher.getFirstName(),
            teacher.getFamilyName(),
            teacher.getModuleNameId(),
            teacher.getCourseLectures()
        );
    }
}


