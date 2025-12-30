package br.com.adaicollege.studentPortal.data.login;

public record UserLoginRequest(
    String registrationNumber,
    String studentPassword
) { }


