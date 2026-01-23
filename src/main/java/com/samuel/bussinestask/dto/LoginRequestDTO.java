package com.samuel.bussinestask.dto;
import lombok.Data;

@Data
public class LoginRequestDTO {
    private String identifier; // email o username
    private String password;
}