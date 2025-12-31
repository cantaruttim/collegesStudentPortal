package br.com.adaicollege.studentPortal.config.exceptions;

public class PasswordExpiredException extends RuntimeException {

    public PasswordExpiredException() {
        super("Initial password has expired");
    }

}
