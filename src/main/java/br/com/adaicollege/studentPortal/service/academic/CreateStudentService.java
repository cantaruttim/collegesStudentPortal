package br.com.adaicollege.studentPortal.service.academic;

import br.com.adaicollege.studentPortal.config.mapper.StudentAlreadyExistsException;
import br.com.adaicollege.studentPortal.config.mapper.academic.CreateStudentMapper;
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

    private void createUserLogin(CreateStudent student) {

        student.setId(null);
        student.setEnrolledAt(LocalDateTime.now());
        student.setStudentStatus(StudentStatus.ACTIVE);

        CreateStudent savedStudent = repo.save(student);

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

        if (repo.existsBySocialSecurityNumber(dto.getSocialSecurityNumber())) {
            throw new StudentAlreadyExistsException(
                    "Student already exists with this SSN"
            );
        }

        if (repo.existsByEmail(dto.getEmail())) {
            throw new StudentAlreadyExistsException(
                    "Student already exists with this email"
            );
        }

        try {
            CreateStudent student = CreateStudentMapper.toEntity(dto);
            student.setId(null);
            student.setEnrolledAt(LocalDateTime.now());
            student.setStudentStatus(StudentStatus.ACTIVE);

            CreateStudent savedStudent = repo.save(student);

            createUserLogin(savedStudent);

            return CreateStudentMapper.toDTO(savedStudent);

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
                .map(CreateStudentMapper::toDTO)
                .toList();
    }

    // -------------------------------------------------------------
    // FIND BY ID
    // -------------------------------------------------------------
    public CreateStudentDTO findById(String id) {

        CreateStudent mod = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        return CreateStudentMapper.toDTO(mod);
    }

    // -------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------
    public CreateStudentDTO update(String id, CreateStudentDTO dto) {

        CreateStudent student = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found: " + id
                        )
                );

        student.setFirstName(dto.getFirstName());
        // IMPLEMENTS!

        CreateStudent updated = repo.save(student);
        return CreateStudentMapper.toDTO(updated);
    }

    // -------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------
    public void delete(String id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found: " + id
            );
        }

        repo.deleteById(id);
    }


}
