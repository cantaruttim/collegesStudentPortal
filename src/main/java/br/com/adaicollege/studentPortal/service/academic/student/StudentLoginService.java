package br.com.adaicollege.studentPortal.service.academic.student;

import br.com.adaicollege.studentPortal.config.utils.PasswordUtils;
import br.com.adaicollege.studentPortal.config.utils.StudentNumber;
import br.com.adaicollege.studentPortal.model.academic.CreateStudent;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentLoginService {

    private final UserLoginRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public StudentLoginService(UserLoginRepository userRepo,
                               PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createForStudent(CreateStudent student) {

        String registrationNumber =
                StudentNumber.defaultStudentNumber(
                        student.getSocialSecurityNumber()
                );

        String rawPassword =
                PasswordUtils.defaultPassword(
                        student.getSocialSecurityNumber(),
                        student.getCourseEnrolled().name()
                );

        String encryptedPassword = passwordEncoder.encode(rawPassword);

        UserLogin user = new UserLogin();
        user.setRegistrationNumber(registrationNumber);
        user.setStudentPassword(encryptedPassword);
        user.setStudentId(student.getId());

        userRepo.save(user);
    }

}
