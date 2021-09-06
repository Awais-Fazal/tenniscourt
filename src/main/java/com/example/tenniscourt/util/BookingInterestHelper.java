package com.example.tenniscourt.util;

import com.example.tenniscourt.dataaccess.InterestRepository;
import com.example.tenniscourt.model.BookingInterest;
import org.springframework.stereotype.Component;

@Component
public class BookingInterestHelper {

    private InterestRepository interestRepository;

    public BookingInterestHelper(InterestRepository interestRepository) {
        this.interestRepository = interestRepository;
    }

    public BookingInterest createBookingInterest(String name, String phoneNumber) {
        BookingInterest bookingInterest = new BookingInterest();
        bookingInterest.setName(name);
        bookingInterest.setPhoneNumber(phoneNumber);
        interestRepository.save(bookingInterest);
        return bookingInterest;
    }
}
