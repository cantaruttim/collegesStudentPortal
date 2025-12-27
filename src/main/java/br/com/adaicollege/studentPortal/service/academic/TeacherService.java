package br.com.adaicollege.studentPortal.service.academic;

import br.com.adaicollege.studentPortal.config.mapper.academic.TeacherMapper;
import br.com.adaicollege.studentPortal.data.academic.TeacherDTO;
import br.com.adaicollege.studentPortal.model.academic.Teacher;
import br.com.adaicollege.studentPortal.repository.academic.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepo;

    public TeacherService(TeacherRepository teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    public TeacherDTO save(TeacherDTO dto) {

        Teacher teacher = TeacherMapper.toEntity(dto);
        teacher.setId(null); // Mongo gera o ID

        Teacher saved = teacherRepo.save(teacher);
        return TeacherMapper.toDTO(saved);
    }

    // -------------------------------------------------------------
    // LIST ALL
    // -------------------------------------------------------------
    public List<TeacherDTO> listAll() {
        return teacherRepo.findAll()
                .stream()
                .map(TeacherMapper::toDTO)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public TeacherDTO findById(String id) {

        Teacher mod = teacherRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        return TeacherMapper.toDTO(mod);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public TeacherDTO update(String id, TeacherDTO dto) {

        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );
        
        teacher.setFirstName(dto.getFirstName());
        teacher.setFamilyName(dto.getFamilyName());
        teacher.setModuleNameId(dto.getModuleName());
        teacher.setCourseLectures(dto.getCourseLectures());

        Teacher updated = teacherRepo.save(teacher);
        return TeacherMapper.toDTO(updated);
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!teacherRepo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found: " + id
            );
        }

        teacherRepo.deleteById(id);
    }


}
