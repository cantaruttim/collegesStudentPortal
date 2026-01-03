package br.com.adaicollege.studentPortal.data.auth;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
    @NotBlank String registrationNumber,
    @NotBlank String currentPassword,
    @NotBlank String newPassword
) { }
