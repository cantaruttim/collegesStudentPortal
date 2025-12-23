package br.com.adaicollege.studentPortal.service.academic;

import br.com.adaicollege.studentPortal.config.mapper.academic.ModulesMapper;
import br.com.adaicollege.studentPortal.data.academicDTO.ModulesDTO;
import br.com.adaicollege.studentPortal.model.academic.Modules;
import br.com.adaicollege.studentPortal.repository.academic.ModulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ModulesService {

    private final ModulesRepository modulesRepo;

    public ModulesService(ModulesRepository modulesRepo) {
        this.modulesRepo = modulesRepo;
    }


    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    public ModulesDTO save(ModulesDTO dto) {

        Modules activity = ModulesMapper.toEntity(dto);
        activity.setId(null); // Mongo gera o ID

        Modules saved = modulesRepo.save(activity);
        return ModulesMapper.toDTO(saved);
    }

    // -------------------------------------------------------------
    // LIST ALL
    // -------------------------------------------------------------
    public List<ModulesDTO> listAll() {
        return modulesRepo.findAll()
                .stream()
                .map(ModulesMapper::toDTO)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public ModulesDTO findById(String id) {

        Modules activity = modulesRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        return ModulesMapper.toDTO(activity);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public ModulesDTO update(String id, ModulesDTO dto) {

        Modules module = modulesRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        // Dados básicos

        /*
            id;
            moduleName;
            moduleDescription;
            Teacher teacherName;
            course;
            quantityClasses;
            startDate;
            endDate;
        */

        module.setModuleName(dto.getModuleName());
        module.setModuleDescription(dto.getModuleDescription());
        // SKIPPED TEACHER
        module.setCourse(dto.getCourse());
        module.setQuantityClasses(dto.getQuantityClasses());
        module.setStartDate(dto.getStartDate());
        module.setEndDate(dto.getEndDate());


        Modules updated = modulesRepo.save(module);
        return ModulesMapper.toDTO(updated);
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!modulesRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found: " + id
            );
        }

        modulesRepo.deleteById(id);
    }


}
