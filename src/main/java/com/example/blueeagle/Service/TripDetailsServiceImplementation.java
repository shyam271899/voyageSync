package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.TripDates;
import com.example.blueeagle.Entity.TripDestinations;
import com.example.blueeagle.Entity.TripDetails;
import com.example.blueeagle.Entity.TripToken;
import com.example.blueeagle.Repository.TripDatesRepository;
import com.example.blueeagle.Repository.TripDestinationsRepository;
import com.example.blueeagle.Repository.TripDetailsRepository;
import com.example.blueeagle.Repository.TripTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripDetailsServiceImplementation implements TripDetailsService {

    @Autowired
    private TripDetailsRepository tripDetailsRepository;
    @Autowired
    private TripDatesRepository tripDatesRepository;
    @Autowired
    private TripDestinationsRepository tripDestinationsRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TripTokenRepository tripTokenRepository;


    @Override
    @Transactional
    public TripDetails createTripDetails(TripDetails tripDetails) {
        // Validate and set tripDates
        if (tripDetails.getHasTripDateDecided() != null && tripDetails.getHasTripDateDecided()) {
            tripDetails.setTripDates(validateAndLimitList(tripDetails.getTripDates(), 4));

            // Save TripDates before saving TripDetails
            saveTransientEntities(tripDetails.getTripDates());
        } else {
            // If hasTripDateDecided is false, allow the user to enter only one trip date
            List<TripDates> singleTripDateList = validateAndLimitList(tripDetails.getTripDates(), 1);
            if (!singleTripDateList.isEmpty()) {
                tripDetails.setTripDates(singleTripDateList);
                // Save TripDates before saving TripDetails
                saveTransientEntities(tripDetails.getTripDates());
            } else {
                tripDetails.setTripDates(null);
            }
        }

        // Validate and set tripDestinations
        if (tripDetails.getHasDestinationDecided() != null && tripDetails.getHasDestinationDecided()) {
            tripDetails.setTripDestinations(validateAndLimitList(tripDetails.getTripDestinations(), 4));

            // Save TripDestinations before saving TripDetails
            saveTransientEntities(tripDetails.getTripDestinations());
        } else {
            // If hasDestinationDecided is false, allow the user to enter only one trip destination
            List<TripDestinations> singleTripDestinationList = validateAndLimitList(tripDetails.getTripDestinations(), 1);
            if (!singleTripDestinationList.isEmpty()) {
                tripDetails.setTripDestinations(singleTripDestinationList);
                // Save TripDestinations before saving TripDetails
                saveTransientEntities(tripDetails.getTripDestinations());
            } else {
                tripDetails.setTripDestinations(null);
            }
        }

        // Generate trip link and set it in TripDetails
        String tripLink = generateTripLink(tripDetails);
        tripDetails.setTripLink(tripLink);

        // Save to the database
        tripDetails = tripDetailsRepository.save(tripDetails);

        // Generate and save TripToken
        TripToken tripToken = new TripToken(tripDetails);
        tripTokenRepository.save(tripToken);

        return tripDetails;
    }



    @Override
    public List<TripDetails> getAllTrips() {
        return tripDetailsRepository.findAll();
    }

    @Override
    public TripDetails getTripById(Long tripId) {
        return tripDetailsRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    @Override
    public void deleteTrip(Long tripId) {
        tripDetailsRepository.deleteById(tripId);
    }



    private <T> List<T> validateAndLimitList(List<T> list, int limit) {
        if (list != null && list.size() > limit) {
            return list.subList(0, limit);
        }
        return list;
    }

    private <T> void saveTransientEntities(List<T> entities) {
        for (T entity : entities) {
            if (entity instanceof TripDates) {
                // Save TripDates
                tripDatesRepository.save((TripDates) entity);
            } else if (entity instanceof TripDestinations) {
                // Save TripDestinations
                tripDestinationsRepository.save((TripDestinations) entity);
            }
            // Add more cases if needed for other associated entities
        }
    }



    private String generateTripLink(TripDetails tripDetails) {
        // Generate a unique trip link using UUID
        UUID uuid = UUID.randomUUID();
        return "http://127.0.0.1:8080/tripLink/join?token=" + uuid.toString();
    }
}
