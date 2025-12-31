package br.com.adaicollege.studentPortal.config.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(403).body(ex.getMessage());
    }


    // FIRST ACESS AND PASSWORD EXPIRE EXCEPTIONS
    @ExceptionHandler(FirstAccessPasswordChangeRequiredException.class)
    public ResponseEntity<String> handleFirstAccess() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("FIRST_ACCESS_PASSWORD_CHANGE_REQUIRED");
    }

    @ExceptionHandler(PasswordExpiredException.class)
    public ResponseEntity<String> handlePasswordExpired() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("PASSWORD_EXPIRED");
    }


}
