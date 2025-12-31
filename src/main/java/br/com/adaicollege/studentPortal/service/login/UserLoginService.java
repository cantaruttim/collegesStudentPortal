package br.com.adaicollege.studentPortal.service.login;

import br.com.adaicollege.studentPortal.config.exceptions.FirstAccessPasswordChangeRequiredException;
import br.com.adaicollege.studentPortal.config.exceptions.PasswordExpiredException;
import br.com.adaicollege.studentPortal.config.security.auth.MyToken;
import br.com.adaicollege.studentPortal.config.utils.TokenUtil;
import br.com.adaicollege.studentPortal.data.auth.ChangePasswordRequest;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLoginService {


    private final UserLoginRepository repo;

    public UserLoginService(UserLoginRepository repo) {
        this.repo = repo;
    }

    public UserLogin addUser(UserLogin user ) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setStudentPassword(encoder.encode(user.getStudentPassword()));

        return repo.save(user);
    }

    public MyToken userLogin(UserLogin user) {

        UserLogin storedUser = repo
                .findByRegistrationNumber(user.getRegistrationNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(
                user.getStudentPassword(),
                storedUser.getStudentPassword()
        )) {
            throw new RuntimeException("Unauthorized user");
        }

        if (storedUser.isFirstAccess()) {
            if (storedUser.getPasswordExpiresAt()
                    .isBefore(LocalDateTime.now())) {

                throw new PasswordExpiredException();
            }

            throw new FirstAccessPasswordChangeRequiredException();
        }

        return TokenUtil.encode(storedUser);
    }

    public void changePassword(ChangePasswordRequest request) {

        UserLogin user = repo
                .findByRegistrationNumber(request.registrationNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(
                request.oldPassword(),
                user.getStudentPassword()
        )) {
            throw new RuntimeException("Invalid password");
        }

        user.setStudentPassword(
                encoder.encode(request.newPassword())
        );
        user.setFirstAccess(false);
        user.setPasswordExpiresAt(null);

        repo.save(user);
    }

}
