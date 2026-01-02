package br.com.adaicollege.studentPortal.config.security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/user-login").permitAll()

                .requestMatchers(
                        "/api/v1/create-student",
                        "/api/v1/create-module",
                        "/api/v1/create-teacher"
                ).hasAnyRole("ADMIN", "SECRETARY")

                .requestMatchers(
                        "/api/v1/student-class-activity-response"
                ).hasRole("STUDENT")

                // DEFAULT
                .anyRequest().authenticated()
            )

            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .addFilterBefore(
                    new AuthFilter(),
                    UsernamePasswordAuthenticationFilter.class
            );
        return http.build();
    }

}
