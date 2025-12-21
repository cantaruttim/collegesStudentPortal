package br.com.adaicollege.studentPortal.controller.auth;


import br.com.adaicollege.studentPortal.config.security.Auth.MyToken;
import br.com.adaicollege.studentPortal.model.login.UserLogin;
import br.com.adaicollege.studentPortal.service.login.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-login")
public class UserLoginController {

    private final UserLoginService service;

    public UserLoginController(UserLoginService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> login(@RequestBody UserLogin user) {
        MyToken token = service.userLogin(user);
        return ResponseEntity.ok(token);
    }

}
