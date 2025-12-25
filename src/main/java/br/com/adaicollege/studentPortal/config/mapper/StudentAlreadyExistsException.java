package br.com.adaicollege.studentPortal.config.mapper;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class StudentAlreadyExistsException extends RuntimeException {

    public StudentAlreadyExistsException(String message) {
        super(message);
    }

}
