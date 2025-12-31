package br.com.adaicollege.studentPortal.controller.auth;

import br.com.adaicollege.studentPortal.data.auth.FirstAccessRequest;
import br.com.adaicollege.studentPortal.service.academic.student.FirstAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class FirstAccessController {

    private final FirstAccessService service;

    public FirstAccessController(FirstAccessService service) {
        this.service = service;
    }

    @PostMapping("/first-access")
    public ResponseEntity<Void> firstAccess(
            @RequestBody FirstAccessRequest request
    ) {
        service.firstAccess(
                request.registrationNumber(),
                request.currentPassword(),
                request.newPassword()
        );

        return ResponseEntity.noContent().build();
    }
}