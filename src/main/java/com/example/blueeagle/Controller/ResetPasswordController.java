package com.example.blueeagle.Controller;

import com.example.blueeagle.Model.ResetPassword;
import com.example.blueeagle.Service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;


    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPassword request) {
        String result = resetPasswordService.resetPassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());

        if (result.equals("Invalid old password") || result.equals("Same old and new password")) {
            return ResponseEntity.badRequest().body(result);
        } else if (result.equals("Password reset successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

