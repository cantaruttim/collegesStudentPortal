package br.com.adaicollege.studentPortal.config.security.auth;

//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        /*
//        *  Responsible to filter requests and tokens
//        * */
//
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests( (auth) -> {
//                    auth.requestMatchers(
//                            new AntPathRequestMatcher("//student-login/login", "GET")).permitAll()
//                            .anyRequest().authenticated();
//                });
//
//        http.addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//}
