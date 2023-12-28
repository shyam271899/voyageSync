package com.example.blueeagle.Repository;

import com.example.blueeagle.Entity.TripDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDatesRepository extends JpaRepository<TripDates, Long> {
    // You can add custom queries or methods if needed
}