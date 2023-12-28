package com.example.blueeagle.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

    @Entity
    @Embeddable
    @Data
    public class TripDates {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long tripDateId;
        private Date startDate;
        private Date endDate;

    }

