package br.com.adaicollege.studentPortal.service.academic;

import br.com.adaicollege.studentPortal.config.exceptions.StudentAlreadyExistsException;
import br.com.adaicollege.studentPortal.config.utils.PasswordUtils;
import br.com.adaicollege.studentPortal.config.utils.StudentNumber;
import br.com.adaicollege.studentPortal.data.academicDTO.CreateStudentDTO;
import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.model.enums.StudentStatus;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreateStudentService {

    private final CreateStudentRepository repo;
    private final UserLoginRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public CreateStudentService(CreateStudentRepository repo,
                                UserLoginRepository userRepo,
                                PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // -------------------------------------------------------------
    // AUX: CREATE USER LOGIN
    // -------------------------------------------------------------
    private void createUserLogin(CreateStudent savedStudent) {

        String registrationNumber =
                StudentNumber.defaultStudentNumber(
                        savedStudent.getSocialSecurityNumber()
                );

        String rawPassword =
                PasswordUtils.defaultPassword(
                        savedStudent.getSocialSecurityNumber(),
                        savedStudent.getCourseEnrolled().name()
                );

        String encryptedPassword = passwordEncoder.encode(rawPassword);

        UserLogin user = new UserLogin();
        user.setRegistrationNumber(registrationNumber);
        user.setStudentPassword(encryptedPassword);
        user.setStudentId(savedStudent.getId());

        userRepo.save(user);
    }

    // -------------------------------------------------------------
    // CREATE
    // -------------------------------------------------------------
    public CreateStudentDTO save(CreateStudentDTO dto) {

        if (repo.existsBySocialSecurityNumber(dto.socialSecurityNumber())) {
            throw new StudentAlreadyExistsException(
                    "Student already exists with this SSN"
            );
        }

        if (repo.existsByEmail(dto.email())) {
            throw new StudentAlreadyExistsException(
                    "Student already exists with this email"
            );
        }

        try {
            CreateStudent student = CreateStudent.from(dto);

            student.setId(null);
            student.setEnrolledAt(LocalDateTime.now());
            student.setStudentStatus(StudentStatus.ACTIVE);

            CreateStudent savedStudent = repo.save(student);

            createUserLogin(savedStudent);

            return new CreateStudentDTO(savedStudent);

        } catch (DuplicateKeyException ex) {
            throw new StudentAlreadyExistsException(
                    "Student already exists (duplicate key)"
            );
        }
    }

    // -------------------------------------------------------------
    // LIST ALL
    // -------------------------------------------------------------
    public List<CreateStudentDTO> listAll() {
        return repo.findAll()
                .stream()
                .map(CreateStudentDTO::new)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public CreateStudentDTO findById(String id) {

        CreateStudent student = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found: " + id
                        )
                );

        return new CreateStudentDTO(student);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public CreateStudentDTO update(String id, CreateStudentDTO dto) {

        CreateStudent student = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Student not found: " + id
                        )
                );

        student.setFirstName(dto.firstName());
        student.setFamilyName(dto.familyName());
        student.setEmail(dto.email());
        student.setBirthDate(dto.birthDate());
        student.setCourseEnrolled(dto.courseEnrolled());
        student.setModuleNameId(dto.moduleNameId());

        CreateStudent updated = repo.save(student);
        return new CreateStudentDTO(updated);
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found: " + id
            );
        }

        repo.deleteById(id);
    }
}
