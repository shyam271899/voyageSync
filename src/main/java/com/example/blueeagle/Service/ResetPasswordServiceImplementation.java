package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.SignUp;
import com.example.blueeagle.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordServiceImplementation implements ResetPasswordService {

    @Autowired
    private SignUpRepository signUpRepository;

    @Override
    public String resetPassword(String email, String oldPassword, String newPassword) {
        Optional<SignUp> optionalRequest = signUpRepository.findByEmail(email);

        if (optionalRequest.isPresent()) {
            SignUp existingRequest = optionalRequest.get();

            if (!existingRequest.getPassword().equals(oldPassword)) {
                return "Invalid old password";
            } else if (newPassword.equals(oldPassword)) {
                return "Same old and new password";
            } else {
                existingRequest.setPassword(newPassword);
                signUpRepository.save(existingRequest);
                return "Password reset successful";
            }
        } else {
            return "Registration Not Found";
        }
    }
}