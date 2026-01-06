package br.com.adaicollege.studentPortal.model.academic.secretary;

import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherRequest;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teacher {

    @Id
    private String id;
    private String firstName;
    private String familyName;
    private String moduleNameId;
    private CollegeCourse courseLectures;

    public Teacher() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getModuleNameId() {
        return moduleNameId;
    }

    public void setModuleNameId(String moduleNameId) {
        this.moduleNameId = moduleNameId;
    }

    public CollegeCourse getCourseLectures() {
        return courseLectures;
    }

    public void setCourseLectures(CollegeCourse courseLectures) {
        this.courseLectures = courseLectures;
    }

    public static Teacher from(TeacherRequest req) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(req.firstName());
        teacher.setFamilyName(req.familyName());
        teacher.setModuleNameId(req.moduleNameId());
        teacher.setCourseLectures(req.courseLectures());
        return teacher;
    }


}
