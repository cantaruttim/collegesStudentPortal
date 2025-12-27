package br.com.adaicollege.studentPortal.data.formsDTO.activities;

public record StudentsActivityFormRequest(
        String registrationNumber,
        String fullName,
        String email,
        String moduleId,
        String teacherId,
        String firstQuestion,
        String secondQuestion
) { }



