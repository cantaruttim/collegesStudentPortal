package br.com.adaicollege.studentPortal.config.mapper.forms;

import br.com.adaicollege.studentPortal.data.formsDTO.StudentsActivityFormsDTO;
import br.com.adaicollege.studentPortal.model.forms.activities.StudentsActivityForms;

public class StudentsActivityFormsMapper {

    // ======================================================
    // DTO → Entity
    // ======================================================
    public static StudentsActivityForms toEntity(StudentsActivityFormsDTO dto) {

        if (dto == null) return null;
        StudentsActivityForms activityForms = new StudentsActivityForms();

        activityForms.setId(dto.getId());
        activityForms.setRegistrationNumber(dto.getRegistrationNumber());
        activityForms.setFullName(dto.getFullName());
        activityForms.setEmail(dto.getEmail());

        // IMPLEMENT COMPOSITION
        // activityForms.setModuleId(dto.getModuleId());
        // activityForms.setTeacherId(dto.getTeacherId());

        activityForms.setFirstQuestion(dto.getFirstQuestion());
        activityForms.setSecondQuestion(dto.getSecondQuestion());
        activityForms.setCreatedAt(dto.getCreatedAt());


        return activityForms;
    }

    // ======================================================
    // Entity → DTO
    // ======================================================
    public static StudentsActivityFormsDTO toDTO(StudentsActivityForms activityForms) {

        if (activityForms == null) return null;
        StudentsActivityFormsDTO dto = new StudentsActivityFormsDTO();

        dto.setId(activityForms.getId());
        dto.setRegistrationNumber(activityForms.getRegistrationNumber());
        dto.setFullName(activityForms.getFullName());
        dto.setEmail(activityForms.getEmail());

        // IMPLEMENT COMPOSITION
        // dto.setModuleId(activityForms.getModuleId());
        // dto.setTeacherId(activityForms.getTeacherId());

        dto.setFirstQuestion(activityForms.getFirstQuestion());
        dto.setSecondQuestion(activityForms.getSecondQuestion());
        dto.setCreatedAt(activityForms.getCreatedAt());


        return dto;
    }


}
