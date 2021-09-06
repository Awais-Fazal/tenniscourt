package com.example.tenniscourt.util;

import com.example.tenniscourt.dataaccess.BookingRepository;
import com.example.tenniscourt.model.BookingCalendar;
import com.example.tenniscourt.model.CourtBooking;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Log4j2
@Component
public class BookingCalendarHelper {

    private BookingRepository bookingRepository;
    private CourtBookingHelper courtBookingHelper;

    public BookingCalendarHelper(BookingRepository bookingRepository,
                                 CourtBookingHelper courtBookingHelper) {
        this.bookingRepository = bookingRepository;
        this.courtBookingHelper = courtBookingHelper;
    }

    /*
        Try to add booking to a court, if possible. We will try in each of the three courts
        If booking is successful in a court and the court is full after this booking, we send
        a notification
     */
    public boolean createBooking(LocalDate requestDate, String name, String phoneNumber) {
        BookingCalendar bookingCalendar = getBookingCalendar(requestDate);
        if (bookingCalendar == null) {
            createBookingCalendar(requestDate, name, phoneNumber);
            return true;
        } else if (courtBookingHelper.canAddInterest(bookingCalendar.getCourtBookingOne())) {
            if (courtBookingHelper.addCourtInterest(bookingCalendar.getCourtBookingOne(), name, phoneNumber)) {
                sendCourtBookingCompleteNotification(requestDate, 1);
            }
            return true;
        } else if (bookingCalendar.getCourtBookingTwo() == null) {
            addSecondCourt(bookingCalendar, name, phoneNumber);
            return true;
        } else if (courtBookingHelper.canAddInterest(bookingCalendar.getCourtBookingTwo())) {
            if (courtBookingHelper.addCourtInterest(bookingCalendar.getCourtBookingTwo(), name, phoneNumber)) {
                sendCourtBookingCompleteNotification(requestDate, 2);
            }
            return true;
        } else if (bookingCalendar.getCourtBookingThree() == null) {
            addThirdCourt(bookingCalendar, name, phoneNumber);
            return true;
        } else if (courtBookingHelper.canAddInterest(bookingCalendar.getCourtBookingThree())) {
            if (courtBookingHelper.addCourtInterest(bookingCalendar.getCourtBookingThree(), name, phoneNumber)) {
                sendCourtBookingCompleteNotification(requestDate, 3);
            }
            return true;
        }
        return false;
    }

    private BookingCalendar getBookingCalendar(LocalDate requestDate) {
        Optional booking  = bookingRepository.findById(requestDate);
        if (booking.isPresent()) {
            return (BookingCalendar) booking.get();
        }
        return null;
    }

    private BookingCalendar createBookingCalendar(LocalDate requestDate, String name, String phoneNumber) {
        BookingCalendar bookingCalendar = new BookingCalendar();
        CourtBooking courtBooking = courtBookingHelper.createCourtBooking(name, phoneNumber);
        bookingCalendar.setBookingDate(requestDate);
        bookingCalendar.setCourtBookingOne(courtBooking);
        bookingRepository.save(bookingCalendar);
        return bookingCalendar;
    }

    private BookingCalendar addSecondCourt(BookingCalendar bookingCalendar,  String name, String phoneNumber) {
        CourtBooking courtBooking = courtBookingHelper.createCourtBooking(name, phoneNumber);
        bookingCalendar.setCourtBookingTwo(courtBooking);
        bookingRepository.save(bookingCalendar);
        return bookingCalendar;
    }

    private BookingCalendar addThirdCourt(BookingCalendar bookingCalendar,  String name, String phoneNumber) {
        CourtBooking courtBooking = courtBookingHelper.createCourtBooking(name, phoneNumber);
        bookingCalendar.setCourtBookingThree(courtBooking);
        bookingRepository.save(bookingCalendar);
        return bookingCalendar;
    }

    private void sendCourtBookingCompleteNotification(LocalDate date, int courtNumber) {
        log.info("Court booking complete of Court Number " + courtNumber + " for " + date);
    }
}
