package com.samuel.bussinestask.service.impl;
import com.samuel.bussinestask.entity.ResetToken;
import com.samuel.bussinestask.entity.User;
import com.samuel.bussinestask.repository.ResetTokenRepository;
import com.samuel.bussinestask.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final ResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(
            UserRepository userRepository,
            ResetTokenRepository tokenRepository,
            EmailService emailService,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void processForgotPassword(String email) {

        userRepository.findByEmail(email).ifPresent(user -> {

            ResetToken token = new ResetToken();
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);
            token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            token.setUsed(false);

            tokenRepository.save(token);
            String link = "http://localhost:5173/reset-password?token=" + token.getToken();
            emailService.sendResetEmail(user.getEmail(), link);
        });
    }

    public void validateToken(String token) {
        ResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Token inválido"
                        )
                );

        if (resetToken.isUsed()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El token ya fue utilizado"
            );
        }

        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El token ha expirado"
            );
        }
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {

        ResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Token inválido"
                        )
                );

        if (resetToken.isUsed()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El token ya fue utilizado"
            );
        }

        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El token ha expirado"
            );
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        resetToken.setUsed(true);
        tokenRepository.save(resetToken);
    }
}