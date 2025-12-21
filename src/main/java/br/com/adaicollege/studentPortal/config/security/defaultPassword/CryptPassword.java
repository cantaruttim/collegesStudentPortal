package br.com.adaicollege.studentPortal.config.security.defaultPassword;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class CryptPassword {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // used when a student is created
        return new BCryptPasswordEncoder();
    }

}
