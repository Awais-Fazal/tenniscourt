package com.example.tenniscourt.service;

import com.example.tenniscourt.TestHelper;
import com.example.tenniscourt.model.request.BookingRequest;
import com.example.tenniscourt.util.BookingCalendarHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingCalendarHelper bookingCalendarHelper;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void registerInterestWithEmptyDate() {
        BookingRequest request = TestHelper.buildEmptyDateRequest();
        String response = bookingService.registerInterest(request);
        assertEquals("Unable to register interest. Date not provided in request body.", response);
    }

    @Test
    public void registerInterestWithEmptyName() {
        BookingRequest request = TestHelper.buildEmptyNameRequest();
        String response = bookingService.registerInterest(request);
        assertEquals("Unable to register interest. Name not provided in request body.", response);
    }

    @Test
    public void registerInterestWithEmptyPhoneNumber() {
        BookingRequest request = TestHelper.buildEmptyPhoneNumberRequest();
        String response = bookingService.registerInterest(request);
        assertEquals("Unable to register interest. Phone number not provided in request body.", response);
    }

    @Test
    public void registerInterestWithInvalidDate() {
        BookingRequest request = TestHelper.buildInvalidDateRequest();
        String response = bookingService.registerInterest(request);
        assertEquals("Unable to register interest. Date format is invalid, Should be dd/MM/yyyy.", response);
    }

    @Test
    public void registerInterestWithValidRequest() {
        BookingRequest request = TestHelper.buildValidRequest();
        when(bookingCalendarHelper.createBooking(any(), anyString(), anyString())).thenReturn(true);
        String response = bookingService.registerInterest(request);
        assertEquals("Interest registered successfully for 04/09/2021", response);
    }

    @Test
    public void registerInterestWithValidRequestNoBooking() {
        BookingRequest request = TestHelper.buildValidRequest();
        when(bookingCalendarHelper.createBooking(any(), anyString(), anyString())).thenReturn(false);
        String response = bookingService.registerInterest(request);
        assertEquals("No slot available on the requested date 04/09/2021", response);
    }
}
