package br.com.adaicollege.studentPortal.data.auth;

public record FirstAccessResponse(
    String registrationNumber,
    String message
) { }
