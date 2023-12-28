package com.example.blueeagle.Controller;

import com.example.blueeagle.Model.ForgotPasswordRequest;
import com.example.blueeagle.Service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/initiate")
    public ResponseEntity<?> initiateForgotPassword(@RequestBody ForgotPasswordRequest request) {
        return forgotPasswordService.initiateForgotPassword(request.getEmail());
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String resetToken,
                                           @RequestParam("newPassword") String newPassword) {
        return forgotPasswordService.resetPassword(resetToken, newPassword);
    }
}
