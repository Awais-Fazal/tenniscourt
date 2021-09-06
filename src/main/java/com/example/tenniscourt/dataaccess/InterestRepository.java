package com.example.tenniscourt.dataaccess;

import com.example.tenniscourt.model.BookingInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<BookingInterest, Long> {
}
