package com.samuel.bussinestask.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    private String error;
    private String mensaje;
    private LocalDateTime timestamp;
}