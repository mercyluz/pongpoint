package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private User user;
    private TableSelection tableSelection;
    private Schedule schedule;
    private LocalDateTime reservationDate;

    @BeforeEach
    void setUp() {
        // Crear objetos de prueba
        user = new User(1L, "John Doe", "john.doe@example.com", "user");
        tableSelection = new TableSelection(1L, "Table 1", 50.0); // basePrice = 50.0
        schedule = new Schedule(1L, "12:00", "2024-12-08");
        reservationDate = LocalDateTime.of(2024, 12, 8, 12, 0, 0, 0);
        
        // Crear la reserva con el constructor
        reservation = new Reservation(1L, user, tableSelection, schedule, reservationDate, "confirmed", 100.0);
    }

    @Test
    void testReservationConstructorAndGetters() {
        // Verificar que los valores se asignan correctamente
        assertEquals(1L, reservation.getReservationId());
        assertEquals(user, reservation.getUser());
        assertEquals(tableSelection, reservation.getTableSelection());
        assertEquals(schedule, reservation.getSchedule());
        assertEquals(reservationDate, reservation.getDate());
        assertEquals("confirmed", reservation.getStatus());
        assertEquals(100.0, reservation.getPrice());
    }

    @Test
    void testSetters() {
        // Cambiar los valores usando setters
        User newUser = new User(2L, "Jane Doe", "jane.doe@example.com", "admin");
        TableSelection newTableSelection = new TableSelection(2L, "Table 2", 60.0);
        Schedule newSchedule = new Schedule(2L, "18:00", "2024-12-09");
        LocalDateTime newDate = LocalDateTime.of(2024, 12, 9, 18, 0, 0, 0);
        
        reservation.setUser(newUser);
        reservation.setTableSelection(newTableSelection);
        reservation.setSchedule(newSchedule);
        reservation.setDate(newDate);
        reservation.setStatus("cancelled");
        reservation.setPrice(150.0);
        
        // Verificar que los valores se actualizan correctamente
        assertEquals(newUser, reservation.getUser());
        assertEquals(newTableSelection, reservation.getTableSelection());
        assertEquals(newSchedule, reservation.getSchedule());
        assertEquals(newDate, reservation.getDate());
        assertEquals("cancelled", reservation.getStatus());
        assertEquals(150.0, reservation.getPrice());
    }

    @Test
    void testDefaultConstructor() {
        // Probar el constructor vacío
        Reservation newReservation = new Reservation();
        assertNull(newReservation.getReservationId());
        assertNull(newReservation.getUser());
        assertNull(newReservation.getTableSelection());
        assertNull(newReservation.getSchedule());
        assertNull(newReservation.getDate());
        assertNull(newReservation.getStatus());
        assertNull(newReservation.getPrice());
    }

    @Test
    void testPriceCanBeNull() {
        // Crear una reserva sin precio
        Reservation reservationWithoutPrice = new Reservation(2L, user, tableSelection, schedule, reservationDate, "confirmed", null);
        
        // Verificar que el precio es null
        assertNull(reservationWithoutPrice.getPrice());
    }
}
