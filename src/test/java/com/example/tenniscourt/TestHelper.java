package com.example.tenniscourt;

import com.example.tenniscourt.model.request.BookingRequest;

public class TestHelper {

    public static BookingRequest buildEmptyDateRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setDate("");
        bookingRequest.setName("Test Name");
        bookingRequest.setPhoneNumber("1234567");
        return bookingRequest;
    }

    public static BookingRequest buildEmptyNameRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setDate("04/09/2021");
        bookingRequest.setName("");
        bookingRequest.setPhoneNumber("1234567");
        return bookingRequest;
    }

    public static BookingRequest buildEmptyPhoneNumberRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setDate("04/09/2021");
        bookingRequest.setName("Test Name");
        bookingRequest.setPhoneNumber("");
        return bookingRequest;
    }

    public static BookingRequest buildInvalidDateRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setDate("04-09-2021");
        bookingRequest.setName("Test Name");
        bookingRequest.setPhoneNumber("1234567");
        return bookingRequest;
    }

    public static BookingRequest buildValidRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setDate("04/09/2021");
        bookingRequest.setName("Test Name");
        bookingRequest.setPhoneNumber("1234567");
        return bookingRequest;
    }
}
