package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<Reservation> createReservation(
            @RequestParam Long userId,
            @RequestParam Long tableSelectionId,
            @RequestParam Long scheduleId,
            @RequestParam LocalDateTime date,
            @RequestParam String status,
            @RequestParam Double price) {
        
        Reservation newReservation = reservationService.createReservation(userId, tableSelectionId, scheduleId, date, status, price);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    // Obtener todas las reservas
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Obtener una reserva por ID
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservation);
    }

    // Eliminar una reserva
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

    // Actualizar una reserva
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long reservationId,
            @RequestBody Reservation updatedReservation) {
        
        Reservation reservation = reservationService.updateReservation(reservationId, updatedReservation);
        return ResponseEntity.ok(reservation);
    }
}
