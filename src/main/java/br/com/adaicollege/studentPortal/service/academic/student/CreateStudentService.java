package br.com.adaicollege.studentPortal.service.academic.student;

import br.com.adaicollege.studentPortal.config.exceptions.StudentAlreadyExistsException;
import br.com.adaicollege.studentPortal.data.academicDTO.student.CreateStudentRequest;
import br.com.adaicollege.studentPortal.data.academicDTO.student.StudentResponse;
import br.com.adaicollege.studentPortal.data.academicDTO.student.UpdateStudentRequest;
import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;
import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateStudentService {

    private final CreateStudentRepository repo;
    private final StudentLoginService loginService;

    public CreateStudentService(CreateStudentRepository repo,
                                UserLoginRepository userRepo,
                                PasswordEncoder passwordEncoder,
                                StudentLoginService loginService) {
        this.repo = repo;
        this.loginService = loginService;
    }

    // -------------------------
    // CREATE
    // -------------------------
    public StudentResponse create(CreateStudentRequest request) {

        if (repo.existsBySocialSecurityNumber(request.socialSecurityNumber())) {
            throw new StudentAlreadyExistsException("Student already exists with this SSN");
        }

        if (repo.existsByEmail(request.email())) {
            throw new StudentAlreadyExistsException("Student already exists with this email");
        }

        CreateStudent student = CreateStudent.from(request);
        student.setStudentStatus(StudentStatus.ACTIVE);
        student.setEnrolledAt(LocalDateTime.now());

        CreateStudent saved = repo.save(student);
        loginService.createForStudent(saved);

        return new StudentResponse(saved);
    }

    // -------------------------
    // UPDATE
    // -------------------------
    public StudentResponse update(String id, UpdateStudentRequest request) {

        CreateStudent student = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found: " + id
                ));

        student.setFirstName(request.firstName());
        student.setFamilyName(request.familyName());
        student.setEmail(request.email());
        student.setBirthDate(request.birthDate());
        student.setCourseEnrolled(request.courseEnrolled());
        student.setModuleNameId(request.moduleNameId());

        return new StudentResponse(repo.save(student));
    }

    // -------------------------
    // FIND / LIST
    // -------------------------
    public StudentResponse findById(String id) {
        return new StudentResponse(
                repo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found"
                        ))
        );
    }

    public List<StudentResponse> listAll() {
        return repo.findAll()
                .stream()
                .map(StudentResponse::new)
                .toList();
    }
}
