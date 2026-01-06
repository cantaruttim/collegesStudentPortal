package br.com.adaicollege.studentPortal.model.forms.others;

import java.time.LocalDate;

public class CreateTasks {

    // create by teacher, used by students

    private String id;
    private String title;
    private String subtitle;
    private String description;
    private LocalDate createAt;
    private LocalDate expireAt;
    private String responseBy;

    public CreateTasks(
            String id,
            String title,
            String subtitle,
            String description,
            LocalDate createAt,
            LocalDate expireAt,
            String responseBy
    ) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
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

    public int quantityResponse() {
        return 0;
    }



}
