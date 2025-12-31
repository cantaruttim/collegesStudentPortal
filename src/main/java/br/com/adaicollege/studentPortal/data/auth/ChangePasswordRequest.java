package br.com.adaicollege.studentPortal.data.auth;

public record ChangePasswordRequest(
    String registrationNumber,
    String oldPassword,
    String newPassword
) { }
