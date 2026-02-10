package br.com.adaicollege.studentPortal.service.login;

import br.com.adaicollege.studentPortal.config.exceptions.FirstAccessPasswordChangeRequiredException;
import br.com.adaicollege.studentPortal.config.exceptions.PasswordExpiredException;
import br.com.adaicollege.studentPortal.config.security.auth.MyToken;
import br.com.adaicollege.studentPortal.config.utils.TokenUtil;
import br.com.adaicollege.studentPortal.data.auth.ChangePasswordRequest;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import br.com.adaicollege.studentPortal.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserLoginService {


    private final UserLoginRepository repo;
    private final RoleService roleService;

    public UserLoginService(UserLoginRepository repo, RoleService roleService) {
        this.repo = repo;
        this.roleService = roleService;
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

        // TESTING
        System.out.println("LOGIN USER: " + storedUser.getRegistrationNumber());
        System.out.println("ROLES: " + storedUser.getRoles());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(
                user.getStudentPassword(),
                storedUser.getStudentPassword()
        )) {
            throw new RuntimeException("Unauthorized user");
        }

//        if (storedUser.isFirstAccess()) {
//            if (storedUser.getPasswordExpiresAt()
//                    .isBefore(LocalDateTime.now())) {
//
//                throw new PasswordExpiredException();
//            }
//
//            throw new FirstAccessPasswordChangeRequiredException();
//        }

        if (
                storedUser.isFirstAccess()
                        && !storedUser.getRoles().contains("ADMIN")
        ) {
            if (
                    storedUser.getPasswordExpiresAt() != null &&
                            storedUser.getPasswordExpiresAt().isBefore(LocalDateTime.now())
            ) {
                throw new PasswordExpiredException();
            }

            throw new FirstAccessPasswordChangeRequiredException();
        }



        Set<String> roles = storedUser.getRoles();

        Set<String> permissions =
                roleService.resolvePermissions(roles);

        UserLogin tokenUser = new UserLogin();
        tokenUser.setRegistrationNumber(storedUser.getRegistrationNumber());
        tokenUser.setRoles(roles);
        tokenUser.setPermissions(permissions);


        return TokenUtil.encode(tokenUser);
    }

    public void changePassword(ChangePasswordRequest request) {

        if (request.currentPassword() == null || request.newPassword() == null) {
            throw new IllegalArgumentException(("Password fields cannot be null"));
        }

        UserLogin user = repo
                .findByRegistrationNumber(request.registrationNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(
                request.currentPassword(),
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
