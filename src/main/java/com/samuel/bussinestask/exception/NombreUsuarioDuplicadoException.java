package com.samuel.bussinestask.exception;

public class NombreUsuarioDuplicadoException extends RuntimeException {

    public NombreUsuarioDuplicadoException(String userName) {
        super("El nombre de usuario ya existe: " + userName);
    }
}