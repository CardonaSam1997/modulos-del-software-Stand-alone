package com.samuel.bussinestask.exception;

public class UserNoEncontradoException extends RuntimeException {

    public UserNoEncontradoException(Integer id) {
        super("Usuario con id " + id + " no encontrado");
    }
}