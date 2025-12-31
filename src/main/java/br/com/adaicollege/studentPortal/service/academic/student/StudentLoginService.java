package br.com.adaicollege.studentPortal.service.academic.student;

import br.com.adaicollege.studentPortal.config.utils.PasswordUtils;
import br.com.adaicollege.studentPortal.config.utils.StudentNumber;
import br.com.adaicollege.studentPortal.model.academic.student.CreateStudent;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import br.com.adaicollege.studentPortal.service.utils.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentLoginService {

    private final UserLoginRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final Integer EXPIRATION_PASSWORD_TIME = 24;

    public StudentLoginService(UserLoginRepository userRepo,
                               PasswordEncoder passwordEncoder,
                               EmailService emailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    public void createForStudent(CreateStudent student) {

        String registrationNumber =
                StudentNumber.defaultStudentNumber(
                        student.getSocialSecurityNumber()
                );

        String rawPassword =
                PasswordUtils.createDefaultPassword(
                        registrationNumber,
                        student.getCourseEnrolled().name()
                );

        UserLogin user = new UserLogin();
        user.setRegistrationNumber(registrationNumber);
        user.setStudentPassword(passwordEncoder.encode(rawPassword));
        user.setStudentId(student.getId());
        user.setFirstAccess(true);
        user.setPasswordExpiresAt(LocalDateTime.now().plusHours(EXPIRATION_PASSWORD_TIME));

        userRepo.save(user);

        emailService.sendStudentAccessEmail(
                student.getEmail(),
                registrationNumber,
                rawPassword
        );
    }


    public void resendFirstAccessPassword(CreateStudent student) {

        UserLogin user = userRepo.findByRegistrationNumber(
                        StudentNumber.defaultStudentNumber(
                                student.getSocialSecurityNumber()
                        )
                )
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isFirstAccess()) {
            throw new RuntimeException("User already completed first access");
        }

        if (user.getPasswordExpiresAt().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Temporary password is still valid");
        }

        String newTempPassword =
                PasswordUtils.createTemporaryPassword(
                        user.getRegistrationNumber(),
                        student.getCourseEnrolled().name()
                );

        user.setStudentPassword(passwordEncoder.encode(newTempPassword));
        user.setPasswordExpiresAt(LocalDateTime.now().plusHours(EXPIRATION_PASSWORD_TIME));

        userRepo.save(user);

        emailService.sendStudentAccessEmail(
                student.getEmail(),
                user.getRegistrationNumber(),
                newTempPassword
        );
    }
}
