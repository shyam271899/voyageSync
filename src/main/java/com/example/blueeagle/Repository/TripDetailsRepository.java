package com.example.blueeagle.Repository;

import com.example.blueeagle.Entity.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Long>{
    List<TripDetails> findAll();
    Optional<TripDetails> findById(Long tripId);
    void deleteById(Long tripId);



}