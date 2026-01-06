package br.com.adaicollege.studentPortal.service.academic.secretary;

import br.com.adaicollege.studentPortal.config.exceptions.ModuleNotFoundException;
import br.com.adaicollege.studentPortal.config.exceptions.TeacherNotFoundException;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.ModulesResponse;
import br.com.adaicollege.studentPortal.data.academic.secretary.modules.UpdateModulesRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherRequest;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.TeacherResponse;
import br.com.adaicollege.studentPortal.data.academic.secretary.teacher.UpdateTeacherRequest;
import br.com.adaicollege.studentPortal.model.academic.secretary.Modules;
import br.com.adaicollege.studentPortal.model.academic.secretary.Teacher;
import br.com.adaicollege.studentPortal.repository.academic.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) { this.repo = repo; }

    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = Teacher.from(request);
        Teacher saved = repo.save(teacher);

        return new TeacherResponse(saved);
    }

    public TeacherResponse findById(String id) {
        return new TeacherResponse(
                repo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        )
                )
        );
    }

    public List<TeacherResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(TeacherResponse::new)
                .toList();
    }

    public TeacherResponse update(String id, UpdateTeacherRequest request) {

        Teacher teacher = repo.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));

        teacher.setFirstName(request.firstName());
        teacher.setFamilyName(request.familyName());
        teacher.setModuleNameId(request.moduleNameId());
        teacher.setCourseLectures(request.courseLectures());
        Teacher saved = repo.save(teacher);

        return new TeacherResponse(saved);
    }

    public void delete(String id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Teacher not found: " + id
            );
        }
        repo.deleteById(id);
    }



}
