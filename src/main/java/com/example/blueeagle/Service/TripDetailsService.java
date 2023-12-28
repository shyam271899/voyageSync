package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.TripDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TripDetailsService {
    TripDetails createTripDetails(TripDetails tripDetails);
    List<TripDetails> getAllTrips();
    TripDetails getTripById(Long tripId);
    void deleteTrip(Long tripId);

}