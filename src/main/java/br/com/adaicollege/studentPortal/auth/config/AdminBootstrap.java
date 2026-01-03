package br.com.adaicollege.studentPortal.auth.config;

import br.com.adaicollege.studentPortal.auth.enums.RoleName;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.repository.login.UserLoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminBootstrap {

    @Bean
    CommandLineRunner createAdmin(UserLoginRepository repo) {
        return args -> {

            if (repo.findByRegistrationNumber("admin").isPresent()) {
                return;
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            UserLogin admin = new UserLogin();
            admin.setRegistrationNumber("admin");
            admin.setStudentPassword(encoder.encode("admin"));
            admin.setFirstAccess(false);
            admin.setPasswordExpiresAt(null);

            admin.setRoles(Set.of(RoleName.ADMIN.name()));

            admin.setPermissions(Set.of());

            repo.save(admin);

            System.out.println("✅ ADMIN USER CREATED");
        };
    }
}
