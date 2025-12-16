package br.com.adaicollege.studentPortal.model.academic;

public class StudentGrades {

    // @Id
    private Integer id;
    private String registrationNumber;
    private Integer activityResponse;
    private Integer examGrade;
    private Integer finalGrade;

    public StudentGrades() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getActivityResponse() {
        return activityResponse;
    }

    public void setActivityResponse(Integer activityResponse) {
        this.activityResponse = activityResponse;
    }

    public Integer getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(Integer examGrade) {
        this.examGrade = examGrade;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }
}
