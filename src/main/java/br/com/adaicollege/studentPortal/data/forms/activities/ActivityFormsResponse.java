package br.com.adaicollege.studentPortal.data.forms.activities;

import br.com.adaicollege.studentPortal.model.forms.activities.StudentsActivityForms;

import java.time.LocalDateTime;

public record ActivityFormsResponse(
        String id,
        String registrationNumber,
        String fullName,
        String email,
        String moduleId,
        String teacherId,
        String firstQuestion,
        String secondQuestion,
        LocalDateTime createdAt
) {
    public ActivityFormsResponse(StudentsActivityForms forms) {
        this(
                forms.getId(),
                forms.getRegistrationNumber(),
                forms.getFullName(),
                forms.getEmail(),
                forms.getModuleId(),
                forms.getTeacherId(),
                forms.getFirstQuestion(),
                forms.getSecondQuestion(),
                forms.getCreatedAt()
        );
    }
}