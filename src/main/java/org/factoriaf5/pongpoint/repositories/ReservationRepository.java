package org.factoriaf5.pongpoint.repositories;

import org.factoriaf5.pongpoint.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}



