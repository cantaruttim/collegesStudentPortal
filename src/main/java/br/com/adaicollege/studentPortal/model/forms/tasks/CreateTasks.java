package br.com.adaicollege.studentPortal.model.forms.tasks;

import java.time.LocalDate;

public class CreateTasks {

    // create by teacher, used by students

    private String id;
    private String title;
    private String subtitle;
    private String description;
    private String teacherId;
    private String moduleId;
    private LocalDate createAt;
    private LocalDate expireAt;
    private String responseBy;

    public CreateTasks(
            String id,
            String title,
            String subtitle,
            String description,
            String teacherId,
            String moduleId,
            LocalDate createAt,
            LocalDate expireAt,
            String responseBy
    ) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.teacherId = teacherId;
        this.moduleId = moduleId;
        this.createAt = createAt;
        this.expireAt = expireAt;
        this.responseBy = responseBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
