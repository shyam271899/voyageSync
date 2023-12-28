package com.example.blueeagle.Service;

public interface ResetPasswordService {
    String resetPassword(String email, String oldPassword, String newPassword);
}
