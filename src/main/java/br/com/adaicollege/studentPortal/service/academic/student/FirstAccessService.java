package br.com.adaicollege.studentPortal.service.academic.student;

import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@Service
public class FirstAccessService {

    private final UserLoginRepository repo;
    private final PasswordEncoder passwordEncoder;

    public FirstAccessService(UserLoginRepository repo,
                              PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public void firstAccess(String registrationNumber,
                            String currentPassword,
                            String newPassword) {

        UserLogin user = repo.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "User not found")
                );

        if (!user.isFirstAccess()) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "First access already completed"
            );
        }

        if (!passwordEncoder.matches(currentPassword, user.getStudentPassword())) {
            throw new ResponseStatusException(UNAUTHORIZED, "Invalid password");
        }

        user.setStudentPassword(passwordEncoder.encode(newPassword));
        user.setFirstAccess(false);
        user.setPasswordChangedAt(java.time.LocalDateTime.now());

        repo.save(user);
    }
}