package com.samuel.bussinestask.dto;
import com.samuel.bussinestask.entity.Role;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Long userId;
    private Role role;
}