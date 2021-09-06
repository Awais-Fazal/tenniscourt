package com.example.tenniscourt.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private String date;
    private String name;
    private String phoneNumber;
}
