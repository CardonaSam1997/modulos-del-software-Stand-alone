package com.samuel.bussinestask.service.impl;

import com.samuel.bussinestask.dto.LoginRequestDTO;
import com.samuel.bussinestask.dto.LoginResponseDTO;
import com.samuel.bussinestask.entity.User;
import com.samuel.bussinestask.exception.AuthException;
import com.samuel.bussinestask.repository.UserRepository;
import com.samuel.bussinestask.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getIdentifier())
                .orElseGet(() -> userRepository.findByUserName(request.getIdentifier())
                        .orElseThrow(() -> new RuntimeException("Usuario o correo erroneo")));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Credenciales inválidas");
        }

        if (!user.isEnable()) {
            throw new AuthException("Usuario inhabilitado");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponseDTO(
                token,
                user.getId(),
                user.getRole()
        );
    }


}