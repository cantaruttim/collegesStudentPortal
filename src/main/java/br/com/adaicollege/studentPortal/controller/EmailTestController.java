package br.com.adaicollege.studentPortal.controller;

import br.com.adaicollege.studentPortal.service.utils.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    private final EmailService emailService;

    public EmailTestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> testEmail(@RequestParam String to) {

        emailService.sendStudentAccessEmail(
                to,
                "2024011234",
                "TEMP1234"
        );

        return ResponseEntity.ok("Email sent");
    }
}
