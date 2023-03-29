package com.savewater.backend.service;

import com.savewater.backend.models.Projects;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    public String forgotPassword(String email);
    public String resetPassword(String token, String password);
    void sendEmail(String email, String response) throws MessagingException, UnsupportedEncodingException;
}
