package com.example.blueeagle.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class SignUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long signUpId;

    private String firstName;

    private String lastName;

    private String userName;

    private LocalDate dateOfBirth;

    private String email;

    private Double contactNumber;

    private String password;

    private boolean isEnabled = false;

    private String resetToken;

}
