package com.savewater.backend.service;


import com.savewater.backend.models.User;
import com.savewater.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    public String forgotPassword(String email) {

        Optional<User> userOptional = Optional
                .ofNullable(userRepository.findByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setResetPasswordToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getResetPasswordToken();
    }

    public String resetPassword(String token, String password) {

        Optional<User> userOptional = Optional
                .ofNullable(userRepository.findByResetPasswordToken(token));

        if (!userOptional.isPresent()) {
            return "Invalid token for reset password.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token for reset password is expired.";

        }

        User user = userOptional.get();

        user.setPassword(passwordEncoder.encode(password));

        user.setResetPasswordToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }


    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }


    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
