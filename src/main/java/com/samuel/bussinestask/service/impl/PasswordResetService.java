package com.samuel.bussinestask.service.impl;

import com.samuel.bussinestask.entity.ResetToken;
import com.samuel.bussinestask.repository.ResetTokenRepository;
import com.samuel.bussinestask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final ResetTokenRepository tokenRepository;
    private final EmailService emailService;

    public PasswordResetService(
            UserRepository userRepository,
            ResetTokenRepository tokenRepository,
            EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    public void processForgotPassword(String email) {

        userRepository.findByEmail(email).ifPresent(user -> {
            ResetToken token = new ResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);
            token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            tokenRepository.save(token);
            String link = "https://localhost:8001/api/reset-password?token=" + token.getToken();
            emailService.sendResetEmail(user.getEmail(), link);
        });
    }
}