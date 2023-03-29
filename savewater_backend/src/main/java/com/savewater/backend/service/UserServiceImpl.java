package com.savewater.backend.service;


import com.savewater.backend.models.Projects;
import com.savewater.backend.models.User;
import com.savewater.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
    public void sendEmail(String toEMail,String link) throws MessagingException, UnsupportedEncodingException {

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message);
        mailMessage.setFrom("contact@savewater.com", "SaveWater Support");
        mailMessage.setTo(toEMail);
        mailMessage.setSubject("Here's the link to reset your password");

       String content = "<p>Hello,</p>"
               + "<p>You have requested to reset your password.</p>"
               + "<p>Click the link below to change your password:</p>"
               + "<p><a href=\"" + link + "\">Change my password</a></p>"
               + "<br>"
               + "<p>Ignore this email if you do remember your password, "
               + "or you have not made the request.</p>";
        mailMessage.setText(content, true);

        mailSender.send(message);


    }



}
