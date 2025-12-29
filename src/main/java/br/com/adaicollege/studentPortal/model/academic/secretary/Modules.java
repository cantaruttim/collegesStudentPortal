package br.com.adaicollege.studentPortal.model.academic.secretary;

import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesRequest;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Modules {

    @Id
    private String id;
    private String moduleName;
    private String moduleDescription;
    private String teacherId;
    private CollegeCourse course;
    private Integer quantityClasses;
    private LocalDate startDate;
    private LocalDate endDate;

    public Modules() {}

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public static Modules from(ModulesRequest req) {
        Modules modules = new Modules();
        modules.setModuleName(req.moduleName());
        modules.setModuleDescription(req.moduleDescription());
        modules.setCourse(req.course());
        modules.setTeacherId(req.teacherId());
        modules.setQuantityClasses(req.quantityClasses());
        modules.setStartDate(req.startDate());
        modules.setEndDate(req.endDate());
        return modules;
    }


}
