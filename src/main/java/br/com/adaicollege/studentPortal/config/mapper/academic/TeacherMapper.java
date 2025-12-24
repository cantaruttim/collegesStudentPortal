package br.com.adaicollege.studentPortal.config.mapper.academic;

import br.com.adaicollege.studentPortal.data.academicDTO.ModulesDTO;
import br.com.adaicollege.studentPortal.data.academicDTO.TeacherDTO;
import br.com.adaicollege.studentPortal.model.academic.Modules;
import br.com.adaicollege.studentPortal.model.academic.Teacher;

public class TeacherMapper {

    /*
        id;
        firstName;
        familyName;
        moduleName;
        CollegeCourse courseLectures;
    */

    // ======================================================
    // DTO → Entity
    // ======================================================
    public static Teacher toEntity(TeacherDTO dto) {

        if (dto == null) return null;
        Teacher teacher = new Teacher();

        teacher.setId(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setFamilyName(dto.getFamilyName());
        teacher.setModuleName(dto.getModuleName());
        teacher.setCourseLectures(dto.getCourseLectures());

        return teacher;
    }

    // ======================================================
    // Entity → DTO
    // ======================================================
    public static TeacherDTO toDTO(Teacher teacher) {

        if (teacher == null) return null;
        TeacherDTO dto = new TeacherDTO();

        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setFamilyName(teacher.getFamilyName());
        dto.setModuleName(teacher.getModuleName());
        dto.setCourseLectures(teacher.getCourseLectures());

        return dto;
    }


}
