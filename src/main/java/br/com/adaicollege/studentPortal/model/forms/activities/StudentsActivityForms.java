package br.com.adaicollege.studentPortal.model.forms.activities;

import br.com.adaicollege.studentPortal.data.activities.forms.StudentsActivityFormRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;


@Document
public class StudentsActivityForms {
    // represents the data after each class

    @Id
    private String id;
    private String registrationNumber;
    private String fullName;
    private String email;

    private String moduleId;
    private String teacherId;

    private String firstQuestion;
    private String secondQuestion;

    private LocalDateTime createdAt;

    public StudentsActivityForms() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public String getSecondQuestion() {
        return secondQuestion;
    }

    public void setSecondQuestion(String secondQuestion) {
        this.secondQuestion = secondQuestion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StudentsActivityForms that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getRegistrationNumber(), that.getRegistrationNumber()) && Objects.equals(getFullName(), that.getFullName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getModuleId(), that.getModuleId()) && Objects.equals(getTeacherId(), that.getTeacherId()) && Objects.equals(getFirstQuestion(), that.getFirstQuestion()) && Objects.equals(getSecondQuestion(), that.getSecondQuestion()) && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRegistrationNumber(), getFullName(), getEmail(), getModuleId(), getTeacherId(), getFirstQuestion(), getSecondQuestion(), getCreatedAt());
    }

    public static StudentsActivityForms from(StudentsActivityFormRequest req) {
        StudentsActivityForms forms = new StudentsActivityForms();
        forms.setRegistrationNumber(req.registrationNumber());
        forms.setFullName(req.fullName());
        forms.setEmail(req.email());
        forms.setModuleId(req.moduleId());
        forms.setTeacherId(req.teacherId());
        forms.setFirstQuestion(req.firstQuestion());
        forms.setSecondQuestion(req.secondQuestion());
        return forms;
    }

}
