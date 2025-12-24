package br.com.adaicollege.studentPortal.data.academicDTO;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

public class TeacherDTO {

    private String id;
    private String firstName;
    private String familyName;
    private String moduleName;
    private CollegeCourse courseLectures;

    public TeacherDTO() {}

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public CollegeCourse getCourseLectures() {
        return courseLectures;
    }

    public void setCourseLectures(CollegeCourse courseLectures) {
        this.courseLectures = courseLectures;
    }
}
