package com.example.blueeagle.Model;

import lombok.Data;

@Data
public class ResetPassword {

    private  String email;

    private String oldPassword;

    private String newPassword;
}
