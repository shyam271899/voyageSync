
package com.example.blueeagle.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TripDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    private String tripName;
    private Boolean hasTripDateDecided;
    @ElementCollection
    private List<TripDates> tripDates;
    private Boolean hasDestinationDecided;
    @ElementCollection
    private List<TripDestinations> tripDestinations;
    private String tripLink;
    private String hostName;
}