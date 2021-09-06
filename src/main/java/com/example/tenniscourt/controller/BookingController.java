package com.example.tenniscourt.controller;

import com.example.tenniscourt.model.request.BookingRequest;
import com.example.tenniscourt.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/register")
    public String registerInterest(@RequestBody BookingRequest bookingRequest) {
        return bookingService.registerInterest(bookingRequest);
    }
}
