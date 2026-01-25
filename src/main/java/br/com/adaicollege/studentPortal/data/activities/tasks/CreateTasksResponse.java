package br.com.adaicollege.studentPortal.data.activities.tasks;

import br.com.adaicollege.studentPortal.model.forms.tasks.CreateTasks;

import java.time.LocalDate;

public record CreateTasksResponse(
         String id,
         String titleTask,
         String subtitle,
         String description,
         String teacherId,
         String moduleId,
         LocalDate createAt,
         LocalDate expireAt,
         String responseBy
) {

    public CreateTasksResponse(CreateTasks task) {
        this(
                task.getId(),
                task.getTitleTask(),
                task.getSubtitle(),
                task.getDescription(),
                task.getTeacherId(),
                task.getModuleId(),
                task.getCreateAt(),
                task.getExpireAt(),
                task.getResponseBy()
        );
    }

}
