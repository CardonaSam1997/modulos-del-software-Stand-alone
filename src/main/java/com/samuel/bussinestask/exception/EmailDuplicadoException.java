package com.samuel.bussinestask.exception;

public class EmailDuplicadoException extends RuntimeException {

    public EmailDuplicadoException(String email) {
        super("El email ya está registrado: " + email);
    }
}