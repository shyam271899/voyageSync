package com.example.blueeagle.Service;

import org.springframework.http.ResponseEntity;

public interface ForgotPasswordService {
    ResponseEntity<?> initiateForgotPassword(String email);

    ResponseEntity<?> resetPassword(String resetToken, String newPassword);
}
