package br.com.adaicollege.studentPortal.service.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendStudentAccessEmail(
            String to,
            String registration,
            String rawPassword
    ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Access to Student Portal");
        message.setText("""
                Hello %s,

                Your access to the Student Portal has been created.

                Registration: %s
                Temporary password: %s

                Please change your password on first access.

                ADAI College
                """.formatted(
                to,
                registration,
                rawPassword
        ));

        mailSender.send(message);
    }
}
