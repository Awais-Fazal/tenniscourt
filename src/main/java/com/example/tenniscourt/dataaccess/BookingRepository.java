package com.example.tenniscourt.dataaccess;

import com.example.tenniscourt.model.BookingCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<BookingCalendar, LocalDate> {
}
