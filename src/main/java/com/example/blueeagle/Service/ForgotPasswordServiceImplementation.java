package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.SignUp;
import com.example.blueeagle.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ForgotPasswordServiceImplementation implements ForgotPasswordService{


    @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    private EmailService emailService;


    @Override
    public ResponseEntity<?> initiateForgotPassword(String email) {
        SignUp signUp = signUpRepository.findByEmailIgnoreCase(email);

        if (signUp == null) {
            return ResponseEntity.badRequest().body("Error: User with this email does not exist.");
        }

        // Generate a unique reset token
        String resetToken = generateResetToken();

        // Save the reset token to the user's account
        signUp.setResetToken(resetToken);
        signUpRepository.save(signUp);

        // Send an email with the reset password link
        sendResetPasswordEmail(signUp.getEmail(), resetToken);

        return ResponseEntity.ok("Password reset email sent successfully!");

    }

    @Override
    public ResponseEntity<?> resetPassword(String resetToken, String newPassword) {
        SignUp signUp = signUpRepository.findByResetToken(resetToken);

        if (signUp == null) {
            return ResponseEntity.badRequest().body("Error: Invalid or expired reset token.");
        }

        // Update the user's password
        signUp.setPassword(newPassword);
        signUp.setResetToken(null); // Reset the token after a successful password change
        signUpRepository.save(signUp);

        return ResponseEntity.ok("Password reset successful!");
    }
    private void sendResetPasswordEmail(String email, String resetToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Reset Password");
        mailMessage.setText("To reset your password, please click here: "
                + "http://127.0.0.1:8080/forgot-password/reset?token=" + resetToken);
        emailService.sendEmail(mailMessage);
    }
    private String generateResetToken() {
        // Implement your logic to generate a unique reset token, e.g., using UUID
        return UUID.randomUUID().toString();
    }

}
