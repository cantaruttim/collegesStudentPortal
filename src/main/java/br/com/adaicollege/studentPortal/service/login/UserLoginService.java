package br.com.adaicollege.studentPortal.service.login;

import br.com.adaicollege.studentPortal.config.security.auth.MyToken;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        if (encoder.matches(user.getStudentPassword(), storedUser.getStudentPassword())) {
            return new MyToken("security123");
            // return TokenUtil.encode(storedUser);
        }

        throw new RuntimeException("Unauthorized!");
    }

}
