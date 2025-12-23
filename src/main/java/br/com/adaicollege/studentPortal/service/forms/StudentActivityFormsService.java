package br.com.adaicollege.studentPortal.service.forms;

import br.com.adaicollege.studentPortal.config.mapper.forms.StudentsActivityFormsMapper;
import br.com.adaicollege.studentPortal.data.formsDTO.StudentsActivityFormsDTO;
import br.com.adaicollege.studentPortal.model.academic.Modules;
import br.com.adaicollege.studentPortal.model.forms.StudentsActivityForms;
import br.com.adaicollege.studentPortal.repository.forms.StudentActivityFormsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentActivityFormsService {

    private final StudentActivityFormsRepository activityFormsRepo;

    public StudentActivityFormsService(StudentActivityFormsRepository activityFormsRepo) {
        this.activityFormsRepo = activityFormsRepo;
    }


    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    public StudentsActivityFormsDTO save(StudentsActivityFormsDTO dto) {

        StudentsActivityForms activity = StudentsActivityFormsMapper.toEntity(dto);
        activity.setId(null); // Mongo gera o ID

        StudentsActivityForms saved = activityFormsRepo.save(activity);
        return StudentsActivityFormsMapper.toDTO(saved);
    }

    // -------------------------------------------------------------
    // LIST ALL
    // -------------------------------------------------------------
    public List<StudentsActivityFormsDTO> listAll() {
        return activityFormsRepo.findAll()
                .stream()
                .map(StudentsActivityFormsMapper::toDTO)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public StudentsActivityFormsDTO findById(String id) {

        StudentsActivityForms activity = activityFormsRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        return StudentsActivityFormsMapper.toDTO(activity);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public StudentsActivityFormsDTO update(String id, StudentsActivityFormsDTO dto) {

        StudentsActivityForms activity = activityFormsRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        // Dados básicos

        activity.setId(dto.getId());
        activity.setRegistrationNumber(dto.getRegistrationNumber());
        activity.setFullName(dto.getFullName());
        activity.setEmail(dto.getEmail());
        activity.setFirstQuestion(dto.getFirstQuestion());
        activity.setSecondQuestion(dto.getSecondQuestion());

        if (activity.getModuleId() == null) {
            activity.setModuleId(new Modules());
        }

        if (dto.getModuleId() != null) {
            activity.getModuleId().setModuleName(dto.getModuleId().getModuleName());
            activity.getModuleId().setModuleDescription(dto.getModuleId().getModuleDescription());
            activity.getModuleId().setCourse(dto.getModuleId().getCourse());
            activity.getModuleId().setStartDate(dto.getModuleId().getStartDate());
            activity.getModuleId().setEndDate(dto.getModuleId().getEndDate());
            activity.getModuleId().setTeacherName(dto.getModuleId().getTeacherName());
            activity.getModuleId().setQuantityClasses(dto.getModuleId().getQuantityClasses());
        }

        StudentsActivityForms updated = activityFormsRepo.save(activity);
        return StudentsActivityFormsMapper.toDTO(updated);
    }
    
    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!activityFormsRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found: " + id
            );
        }

        activityFormsRepo.deleteById(id);
    }

}
