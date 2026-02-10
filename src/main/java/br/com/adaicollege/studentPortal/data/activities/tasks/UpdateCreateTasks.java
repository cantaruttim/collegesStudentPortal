package br.com.adaicollege.studentPortal.data.activities.tasks;

import java.time.LocalDate;

public record UpdateCreateTasks(

    String title,
    String subtitle,
    String description,
    String teacherId,
    String moduleId,
    LocalDate createAt,
    LocalDate expireAt,
    String responseBy


) { }
