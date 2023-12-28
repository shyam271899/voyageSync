package com.example.blueeagle.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@Table(name="confirmationToken")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = SignUp.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "sign_up_id")
    private SignUp signUp;

    public ConfirmationToken() {
    }


    public ConfirmationToken(SignUp signUp) {
        this.signUp = signUp;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public SignUp getSignUpEntity() {
        return signUp;
    }
}