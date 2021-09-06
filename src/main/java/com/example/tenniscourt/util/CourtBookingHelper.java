package com.example.tenniscourt.util;

import com.example.tenniscourt.dataaccess.CourtRepository;
import com.example.tenniscourt.model.BookingInterest;
import com.example.tenniscourt.model.CourtBooking;
import org.springframework.stereotype.Component;

@Component
public class CourtBookingHelper {

    private BookingInterestHelper bookingInterestHelper;
    private CourtRepository courtRepository;

    public CourtBookingHelper(BookingInterestHelper bookingInterestHelper,
                              CourtRepository courtRepository) {
        this.bookingInterestHelper = bookingInterestHelper;
        this.courtRepository = courtRepository;
    }

    public boolean canAddInterest(CourtBooking courtBooking) {
        return courtBooking.getInterestTwo() == null || courtBooking.getInterestThree() == null || courtBooking.getInterestFour() == null;
    }

    /*
        canAddInterest is called before this, so we know that booking is available at either slot 2, 3 or 4
        Slot 1 is filled when we first create a new court entry
        this function return true when the bookings are complete for this court
     */
    public boolean addCourtInterest(CourtBooking courtBooking, String name, String phoneNumber) {
        BookingInterest bookingInterest = bookingInterestHelper.createBookingInterest(name, phoneNumber);
        if (courtBooking.getInterestTwo() == null) {
            updateCourtSecondInterest(courtBooking, bookingInterest);
            return false;
        } else if (courtBooking.getInterestThree() == null) {
            updateCourtThirdInterest(courtBooking, bookingInterest);
            return false;
        } else if (courtBooking.getInterestFour() == null) {
            updateCourtForthInterest(courtBooking, bookingInterest);
            return true;
        }
        return false;
    }

    public CourtBooking createCourtBooking(String name, String phoneNumber) {
        CourtBooking courtBooking = new CourtBooking();
        BookingInterest bookingInterest = bookingInterestHelper.createBookingInterest(name, phoneNumber);
        courtBooking.setInterestOne(bookingInterest);
        courtRepository.save(courtBooking);
        return courtBooking;
    }

    private CourtBooking updateCourtSecondInterest(CourtBooking courtBooking, BookingInterest bookingInterest) {
        courtBooking.setInterestTwo(bookingInterest);
        courtRepository.save(courtBooking);
        return courtBooking;
    }

    private CourtBooking updateCourtThirdInterest(CourtBooking courtBooking, BookingInterest bookingInterest) {
        courtBooking.setInterestThree(bookingInterest);
        courtRepository.save(courtBooking);
        return courtBooking;
    }

    private CourtBooking updateCourtForthInterest(CourtBooking courtBooking, BookingInterest bookingInterest) {
        courtBooking.setInterestFour(bookingInterest);
        courtRepository.save(courtBooking);
        return courtBooking;
    }
}
