package br.com.adaicollege.studentPortal.model.forms.tasks;

import br.com.adaicollege.studentPortal.data.activities.tasks.CreateTasksRequest;

import java.time.LocalDate;

public class CreateTasks {

    // create by teacher, used by students

    private String id;
    private String titleTask;
    private String subtitle;
    private String description;
    private String teacherId;
    private String moduleId;
    private LocalDate createAt;
    private LocalDate expireAt;
    private String responseBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitle(String titleTask) {
        this.titleTask = titleTask;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }

    public String getResponseBy() {
        return responseBy;
    }

    public void setResponseBy(String responseBy) {
        this.responseBy = responseBy;
    }

    public static CreateTasks from(CreateTasksRequest req) {
        CreateTasks task = new CreateTasks();
        task.setTitle(req.titleTask());
        task.setSubtitle(req.subtitle());
        task.setDescription(req.description());
        task.setModuleId(req.moduleId());
        task.setTeacherId(req.teacherId());
        task.setCreateAt(req.createAt());
        task.setExpireAt(req.expireAt());
        task.setResponseBy(req.responseBy());
        return task;
    }

}
