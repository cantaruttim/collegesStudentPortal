package br.com.adaicollege.studentPortal.model.auth;

public class UserLogin {

    private String registrationNumber;
    private String studentPassword;

    public UserLogin() {}

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
