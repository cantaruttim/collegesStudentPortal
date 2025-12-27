package br.com.adaicollege.studentPortal.model.academic;

import br.com.adaicollege.studentPortal.data.academicDTO.student.CreateStudentRequest;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "student_register")
public class CreateStudent {

    @Id
    private String id;

    @Indexed(unique = true)
    private String socialSecurityNumber;
    private String firstName;
    private String familyName;

    @Indexed(unique = true)
    private String email;
    private LocalDate birthDate;
    private CollegeCourse courseEnrolled;
    private String moduleNameId;
    private StudentStatus studentStatus;

    private LocalDateTime enrolledAt;

    public CreateStudent() {}

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CreateStudent that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSocialSecurityNumber(), that.getSocialSecurityNumber()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getFamilyName(), that.getFamilyName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getBirthDate(), that.getBirthDate()) && getCourseEnrolled() == that.getCourseEnrolled() && Objects.equals(getModuleNameId(), that.getModuleNameId()) && getStudentStatus() == that.getStudentStatus() && Objects.equals(getEnrolledAt(), that.getEnrolledAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSocialSecurityNumber(), getFirstName(), getFamilyName(), getEmail(), getBirthDate(), getCourseEnrolled(), getModuleNameId(), getStudentStatus(), getEnrolledAt());
    }

    public static CreateStudent from(CreateStudentRequest req) {
        CreateStudent student = new CreateStudent();
        student.setSocialSecurityNumber(req.socialSecurityNumber());
        student.setFirstName(req.firstName());
        student.setFamilyName(req.familyName());
        student.setEmail(req.email());
        student.setBirthDate(req.birthDate());
        student.setCourseEnrolled(req.courseEnrolled());
        student.setModuleNameId(req.moduleNameId());
        return student;
    }
}
