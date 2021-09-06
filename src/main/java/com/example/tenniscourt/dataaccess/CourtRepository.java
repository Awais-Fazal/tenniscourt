package com.example.tenniscourt.dataaccess;

import com.example.tenniscourt.model.CourtBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<CourtBooking, Long> {
}
