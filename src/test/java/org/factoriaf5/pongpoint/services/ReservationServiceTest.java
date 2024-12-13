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

 
        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);
    }

    @Test
    public void testCreateReservation() {
        
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

      
        Reservation createdReservation = reservationService.createReservation(reservation);

        assertNotNull(createdReservation);
        assertEquals(1L, createdReservation.getId());
        assertEquals(50.0, createdReservation.getPrice());
        verify(reservationRepository, times(1)).save(reservation); 
     }

    @Test
    public void testGetAllReservations() {
       
        Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setPrice(50.0);
        Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setPrice(100.0);
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

      
        List<Reservation> reservations = reservationService.getAllReservations();

     
        assertNotNull(reservations);
        assertEquals(2, reservations.size());
        assertEquals(1L, reservations.get(0).getId());
        assertEquals(50.0, reservations.get(0).getPrice());
        verify(reservationRepository, times(1)).findAll();  // Verificamos que se llamo a findAll
    }

    @Test
    public void testGetReservationByIdFound() {
        
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        
        Reservation foundReservation = reservationService.getReservationById(1L);

        assertNotNull(foundReservation);
        assertEquals(1L, foundReservation.getId());
        assertEquals(50.0, foundReservation.getPrice());
        verify(reservationRepository, times(1)).findById(1L);  // Verificamos que se llamo a findById
    }

    @Test
    public void testGetReservationByIdNotFound() {
        
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());


        Reservation foundReservation = reservationService.getReservationById(1L);

        assertNull(foundReservation);
        verify(reservationRepository, times(1)).findById(1L);  
    }

    @Test
    public void testDeleteReservationFound() {
      
        when(reservationRepository.existsById(1L)).thenReturn(true);

       
        boolean result = reservationService.deleteReservation(1L);

        
        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(1L);  
    }

    @Test
    public void testDeleteReservationNotFound() {
       
        when(reservationRepository.existsById(1L)).thenReturn(false);

     
        boolean result = reservationService.deleteReservation(1L);

       
        assertFalse(result);
        verify(reservationRepository, times(1)).existsById(1L);  
    }

    @Test
    public void testUpdateReservationFound() {
        
        Reservation updatedReservation = new Reservation();
        updatedReservation.setId(1L);
        updatedReservation.setPrice(60.0);
        when(reservationRepository.existsById(1L)).thenReturn(true);
        when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);

        
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

     
        assertNotNull(result);
        assertEquals(60.0, result.getPrice());
        verify(reservationRepository, times(1)).save(updatedReservation);  // Verificamos que se llamo a save
    }

    @Test
    public void testUpdateReservationNotFound() {
        
        Reservation updatedReservation = new Reservation();
        updatedReservation.setId(1L);
        updatedReservation.setPrice(60.0);
        when(reservationRepository.existsById(1L)).thenReturn(false);

       
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        assertNull(result);
        verify(reservationRepository, times(0)).save(updatedReservation);  
    }
}
