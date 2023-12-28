package com.example.blueeagle.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "tripConfirmationToken")
public class TripToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = TripDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "trip_id")
    private TripDetails tripDetails;

    public TripToken() {
    }

    public TripToken(TripDetails tripDetails) {
        this.tripDetails = tripDetails;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public TripDetails getTripDetailsEntity() {
        return tripDetails;
    }
}
