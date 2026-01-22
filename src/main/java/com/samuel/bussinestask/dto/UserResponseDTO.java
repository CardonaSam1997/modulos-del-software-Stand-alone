package com.samuel.bussinestask.dto;
import com.samuel.bussinestask.entity.Role;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Integer id;
    private String userName;
    private String email;
    private boolean enable;
    private boolean completed;
    private Role role;
}