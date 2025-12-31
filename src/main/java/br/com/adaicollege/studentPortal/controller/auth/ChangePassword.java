package br.com.adaicollege.studentPortal.controller.auth;

import br.com.adaicollege.studentPortal.data.auth.ChangePasswordRequest;
import br.com.adaicollege.studentPortal.service.login.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ChangePassword {

    private final UserLoginService service;

    public ChangePassword(UserLoginService service) {
        this.service = service;
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        service.changePassword(request);
        return ResponseEntity.noContent().build();
    }
}
