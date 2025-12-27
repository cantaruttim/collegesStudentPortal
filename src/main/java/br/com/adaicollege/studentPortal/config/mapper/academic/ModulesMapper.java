package br.com.adaicollege.studentPortal.config.mapper.academic;

import br.com.adaicollege.studentPortal.data.academic.ModulesDTO;
import br.com.adaicollege.studentPortal.model.academic.Modules;

public class ModulesMapper {

    // ======================================================
    // DTO → Entity
    // ======================================================
    public static Modules toEntity(ModulesDTO dto) {

        if (dto == null) return null;
        Modules mod = new Modules();

        mod.setId(dto.getId());
        mod.setModuleName(dto.getModuleName());
        mod.setModuleDescription(dto.getModuleDescription());
        mod.setTeacherName(dto.getTeacherName());
        mod.setQuantityClasses(dto.getQuantityClasses());
        mod.setStartDate(dto.getStartDate());
        mod.setEndDate(dto.getEndDate());
        mod.setCourse(dto.getCourse());

        return mod;
    }

    // ======================================================
    // Entity → DTO
    // ======================================================
    public static ModulesDTO toDTO(Modules mod) {

        if (mod == null) return null;
        ModulesDTO dto = new ModulesDTO();

        dto.setId(mod.getId());
        dto.setModuleName(mod.getModuleName());
        dto.setModuleDescription(mod.getModuleDescription());
        dto.setTeacherName(mod.getTeacherName());
        dto.setQuantityClasses(mod.getQuantityClasses());
        dto.setStartDate(mod.getStartDate());
        dto.setEndDate(mod.getEndDate());
        dto.setCourse(mod.getCourse());

        return dto;
    }


}
