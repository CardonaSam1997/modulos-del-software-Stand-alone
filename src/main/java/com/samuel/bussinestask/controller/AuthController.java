package com.samuel.bussinestask.controller;

import com.samuel.bussinestask.dto.ForgotPasswordRequestDTO;
import com.samuel.bussinestask.dto.LoginRequestDTO;
import com.samuel.bussinestask.dto.LoginResponseDTO;
import com.samuel.bussinestask.dto.ResetPasswordRequestDTO;
import com.samuel.bussinestask.service.impl.AuthService;
import com.samuel.bussinestask.service.impl.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final AuthService authService;

    private final PasswordResetService passwordResetService;

    public AuthController(PasswordResetService passwordResetService, AuthService authService) {
        this.passwordResetService = passwordResetService;
        this.authService = authService;
    }

    /**
     * Valida que exista el usuario y permite pasar
     * a las otras paginas
     * @param request
     * @return id, rol y token
     */
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Permite recuperar la contraseña, validando si el correo
     * existe y enviando una url con token y tiempo
     * @param request
     * @return
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO request
    ) {
        passwordResetService.processForgotPassword(request.getEmail());
        return ResponseEntity.ok(
                "Si el correo existe, se enviará un enlace de recuperación"
        );
    }

    /**
     * Valida que el tiempo del token siga activo
     * @param token
     * @return
     */
    @GetMapping("/reset-password")
    public ResponseEntity<String> validateToken(
            @RequestParam String token
    ) {
        passwordResetService.validateToken(token);
        return ResponseEntity.ok("Token válido");
    }

    /**
     * Cambia la contraseña
     * @param request
     * @return
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO request
    ) {
        passwordResetService.resetPassword(
                request.getToken(),
                request.getNewPassword()
        );
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }
}