package br.com.adaicollege.studentPortal.data.activities.tasks;

import java.time.LocalDate;

public record CreateTasksRequest(
        String titleTask,
        String subtitle,
        String description,
        String teacherId,
        String moduleId,
        LocalDate createAt,
        LocalDate expireAt,
        String responseBy
) { }
