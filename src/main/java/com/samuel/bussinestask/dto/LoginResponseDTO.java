package com.samuel.bussinestask.dto;
import com.samuel.bussinestask.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String token;
    private Integer userId;
    private Role role;
}