package com.example.blueeagle.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Embeddable
@Data
public class TripDestinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripDestinationId;
    private String destination;
}
