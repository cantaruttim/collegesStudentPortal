package br.com.adaicollege.studentPortal.config.exceptions;

public class FirstAccessPasswordChangeRequiredException extends RuntimeException {

    public FirstAccessPasswordChangeRequiredException() {
        super("Password change required on first access");
    }

}
