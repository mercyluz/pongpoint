package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Crear una nueva reserva
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Obtener todas las reservas
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Obtener una reserva por ID
    public Reservation getReservationById(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.orElse(null); // Retorna null si no se encuentra la reserva
    }

    // Eliminar una reserva
    public boolean deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Actualizar una reserva
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        if (reservationRepository.existsById(id)) {
            updatedReservation.setId(id); // Asegurarse de que el ID de la reserva esté presente
            return reservationRepository.save(updatedReservation);
        }
        return null; // Si la reserva no existe, se retorna null
    }
}
