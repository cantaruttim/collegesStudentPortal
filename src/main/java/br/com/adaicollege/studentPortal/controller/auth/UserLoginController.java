package br.com.adaicollege.studentPortal.controller.auth;


import br.com.adaicollege.studentPortal.config.security.MyToken;
import br.com.adaicollege.studentPortal.model.auth.UserLogin;
import br.com.adaicollege.studentPortal.service.UserLoginService;
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

    @PostMapping
    public ResponseEntity<MyToken> login(@RequestBody UserLogin user) {
        MyToken token = service.userLogin(user);
        return ResponseEntity.ok(token);
    }

}
