package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear una reserva de prueba
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);
    }

    @Test
    public void testCreateReservation() {
        // Dado que la reserva se guarda correctamente
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Cuando se crea una nueva reserva
        Reservation createdReservation = reservationService.createReservation(reservation);

        // Entonces se debe retornar la misma reserva
        assertNotNull(createdReservation);
        assertEquals(1L, createdReservation.getId());
        assertEquals(50.0, createdReservation.getPrice());
        verify(reservationRepository, times(1)).save(reservation);  // Verificamos que se llamo a save
    }

    @Test
    public void testGetAllReservations() {
        // Dado que existen dos reservas en la base de datos
        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setPrice(50.0);
        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setPrice(100.0);
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        // Cuando se obtienen todas las reservas
        List<Reservation> reservations = reservationService.getAllReservations();

        // Entonces se deben retornar ambas reservas
        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        assertEquals(1L, reservations.get(0).getId());
        assertEquals(50.0, reservations.get(0).getPrice());
        verify(reservationRepository, times(1)).findAll();  // Verificamos que se llamo a findAll
    }

    @Test
    public void testGetReservationByIdFound() {
        // Dado que existe una reserva en la base de datos
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Cuando se obtiene la reserva por ID
        Reservation foundReservation = reservationService.getReservationById(1L);

        // Entonces se debe retornar la reserva
        assertNotNull(foundReservation);
        assertEquals(1L, foundReservation.getId());
        assertEquals(50.0, foundReservation.getPrice());
        verify(reservationRepository, times(1)).findById(1L);  // Verificamos que se llamo a findById
    }

    @Test
    public void testGetReservationByIdNotFound() {
        // Dado que no existe una reserva con el ID 1
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Cuando se intenta obtener la reserva por ID
        Reservation foundReservation = reservationService.getReservationById(1L);

        // Entonces se debe retornar null
        assertNull(foundReservation);
        verify(reservationRepository, times(1)).findById(1L);  // Verificamos que se llamo a findById
    }

    @Test
    public void testDeleteReservationFound() {
        // Dado que la reserva existe
        when(reservationRepository.existsById(1L)).thenReturn(true);

        // Cuando se elimina la reserva
        boolean result = reservationService.deleteReservation(1L);

        // Entonces se debe retornar true
        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(1L);  // Verificamos que se llamo a deleteById
    }

    @Test
    public void testDeleteReservationNotFound() {
        // Dado que la reserva no existe
        when(reservationRepository.existsById(1L)).thenReturn(false);

        // Cuando se intenta eliminar la reserva
        boolean result = reservationService.deleteReservation(1L);

        // Entonces se debe retornar false
        assertFalse(result);
        verify(reservationRepository, times(1)).existsById(1L);  // Verificamos que se llamo a existsById
    }

    @Test
    public void testUpdateReservationFound() {
        // Dado que la reserva existe
        Reservation updatedReservation = new Reservation();
        updatedReservation.setId(1L);
        updatedReservation.setPrice(60.0);
        when(reservationRepository.existsById(1L)).thenReturn(true);
        when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);

        // Cuando se actualiza la reserva
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        // Entonces se debe retornar la reserva actualizada
        assertNotNull(result);
        assertEquals(60.0, result.getPrice());
        verify(reservationRepository, times(1)).save(updatedReservation);  // Verificamos que se llamo a save
    }

    @Test
    public void testUpdateReservationNotFound() {
        // Dado que la reserva no existe
        Reservation updatedReservation = new Reservation();
        updatedReservation.setId(1L);
        updatedReservation.setPrice(60.0);
        when(reservationRepository.existsById(1L)).thenReturn(false);

        // Cuando se intenta actualizar la reserva
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        // Entonces se debe retornar null
        assertNull(result);
        verify(reservationRepository, times(0)).save(updatedReservation);  // No se debe llamar a save
    }
}
