package com.example.tenniscourt.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bookingcalendar")
public class BookingCalendar {
    @Id
    @Column(name = "booking_date", columnDefinition = "DATE")
    private LocalDate bookingDate;

    @OneToOne
    @JoinColumn(name = "booking_one")
    private CourtBooking courtBookingOne;

    @OneToOne
    @JoinColumn(name = "booking_two")
    private CourtBooking courtBookingTwo;

    @OneToOne
    @JoinColumn(name = "booking_three")
    private CourtBooking courtBookingThree;

}
