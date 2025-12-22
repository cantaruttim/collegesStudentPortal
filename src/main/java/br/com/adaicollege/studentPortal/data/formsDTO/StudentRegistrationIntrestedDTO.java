package br.com.adaicollege.studentPortal.data.formsDTO;

import java.time.LocalDateTime;

public class StudentRegistrationIntrestedDTO {

    // @Id
    private Integer id;

    private String fullName;
    private String email;
    private String courseIntrested;
    private String question;
    private LocalDateTime createAt;

    public StudentRegistrationIntrestedDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCourseIntrested() {
        return courseIntrested;
    }

    public void setCourseIntrested(String courseIntrested) {
        this.courseIntrested = courseIntrested;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
