package br.com.adaicollege.studentPortal.data.auth;

public record FirstAccessRequest(
    String registrationNumber,
    String currentPassword,
    String newPassword
) { }
