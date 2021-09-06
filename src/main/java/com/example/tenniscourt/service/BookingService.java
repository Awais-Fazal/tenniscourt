package com.example.tenniscourt.service;

import com.example.tenniscourt.model.request.BookingRequest;
import com.example.tenniscourt.util.BookingCalendarHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class BookingService {

    private BookingCalendarHelper bookingCalendarHelper;

    public BookingService(BookingCalendarHelper bookingCalendarHelper) {
        this.bookingCalendarHelper = bookingCalendarHelper;
    }

    public String registerInterest(BookingRequest request) {
        String validationResult = validateRequest(request);
        if (!"valid".equals(validationResult)) {
            return validationResult;
        }
        LocalDate requestDate = LocalDate.parse(request.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (bookingCalendarHelper.createBooking(requestDate, request.getName(), request.getPhoneNumber())) {
            return "Interest registered successfully for " + request.getDate();
        } else {
            return "No slot available on the requested date " + request.getDate();
        }
    }

    private String validateRequest(BookingRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return "Unable to register interest. Request body is empty.";
        }
        if (ObjectUtils.isEmpty(request.getDate())) {
            return "Unable to register interest. Date not provided in request body.";
        }
        if (ObjectUtils.isEmpty(request.getName())) {
            return "Unable to register interest. Name not provided in request body.";
        }
        if (ObjectUtils.isEmpty(request.getPhoneNumber())) {
            return "Unable to register interest. Phone number not provided in request body.";
        }
        try {
            LocalDate.parse(request.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return "Unable to register interest. Date format is invalid, Should be dd/MM/yyyy.";
        }
        return "valid";
    }

}
