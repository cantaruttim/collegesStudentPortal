package br.com.adaicollege.studentPortal.data.academicDTO;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateStudentDTO {

    private String id;

    private String socialSecurityNumber;
    private String firstName;
    private String familyName;
    private String email;
    private LocalDate birthDate;
    private CollegeCourse courseEnrolled;
    private String moduleNameId; // first modulo enrolled (could be chosen after)
    private StudentStatus studentStatus; // active or not

    private LocalDateTime enrolledAt;

    public CreateStudentDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CollegeCourse getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(CollegeCourse courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    public String getModuleNameId() {
        return moduleNameId;
    }

    public void setModuleNameId(String moduleNameId) {
        this.moduleNameId = moduleNameId;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
