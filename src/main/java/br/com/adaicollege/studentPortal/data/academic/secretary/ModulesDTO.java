package br.com.adaicollege.studentPortal.data.academic.secretary;

import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;

import java.time.LocalDate;

public class ModulesDTO {

    private String id;
    private String moduleName;
    private String moduleDescription;
    private String teacherName;
    private CollegeCourse course;
    private Integer quantityClasses;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CollegeCourse getCourse() {
        return course;
    }

    public void setCourse(CollegeCourse course) {
        this.course = course;
    }

    public Integer getQuantityClasses() {
        return quantityClasses;
    }

    public void setQuantityClasses(Integer quantityClasses) {
        this.quantityClasses = quantityClasses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
