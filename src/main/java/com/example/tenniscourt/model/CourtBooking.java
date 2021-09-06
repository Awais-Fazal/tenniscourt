package com.example.tenniscourt.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "courtbooking")
public class CourtBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @OneToOne
    @JoinColumn(name = "interest_one")
    private BookingInterest interestOne;

    @OneToOne
    @JoinColumn(name = "interest_two")
    private BookingInterest interestTwo;

    @OneToOne
    @JoinColumn(name = "interest_three")
    private BookingInterest interestThree;

    @OneToOne
    @JoinColumn(name = "interest_four")
    private BookingInterest interestFour;
}
