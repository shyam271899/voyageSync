package com.example.blueeagle.Controller;

import com.example.blueeagle.Entity.TripDetails;
import com.example.blueeagle.Service.TripDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripDetailsController {
    @Autowired
    private TripDetailsService tripDetailsService;

    @PostMapping("/create-trip")
    public ResponseEntity<TripDetails> createTripDetails(@RequestBody TripDetails tripDetails) {
        TripDetails createdTrip = tripDetailsService.createTripDetails(tripDetails);
        return ResponseEntity.ok(createdTrip);
    }

    @GetMapping("/get-trips")
    public ResponseEntity<List<TripDetails>> getAllTrips() {
        List<TripDetails> trips = tripDetailsService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDetails> getTripById(@PathVariable Long tripId) {
        TripDetails trip = tripDetailsService.getTripById(tripId);
        return ResponseEntity.ok(trip);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {
        tripDetailsService.deleteTrip(tripId);
        return ResponseEntity.noContent().build();
    }
}
