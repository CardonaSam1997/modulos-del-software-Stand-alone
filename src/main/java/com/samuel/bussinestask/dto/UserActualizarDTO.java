package com.samuel.bussinestask.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserActualizarDTO {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    private boolean enable;
    private boolean completed;

}