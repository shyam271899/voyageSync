package com.example.blueeagle.Repository;

import com.example.blueeagle.Entity.TripDestinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDestinationsRepository extends JpaRepository<TripDestinations, Long> {
    // You can add custom queries or methods if needed
}