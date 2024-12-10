package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.models.User;
import org.factoriaf5.pongpoint.models.TableSelection;
import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.repositories.ReservationRepository;
import org.factoriaf5.pongpoint.repositories.UserRepository;
import org.factoriaf5.pongpoint.repositories.TableSelectionRepository;
import org.factoriaf5.pongpoint.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TableSelectionRepository tableSelectionRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private User user;
    private TableSelection tableSelection;
    private Schedule schedule;
    private LocalDateTime reservationDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear objetos de prueba
        user = new User(1L, "John Doe", "john.doe@example.com", "user");
        tableSelection = new TableSelection(1L, "Table 1", 50.0);
        schedule = new Schedule(1L, "12:00", "2024-12-08");
        reservationDate = LocalDateTime.of(2024, 12, 8, 12, 0, 0, 0);
        reservation = new Reservation(1L, user, tableSelection, schedule, reservationDate, "confirmed", 100.0);
    }

    @Test
    void testCreateReservation() {
        // Simulamos la interacción con los repositorios
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(tableSelectionRepository.findById(1L)).thenReturn(Optional.of(tableSelection));
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // Llamamos al método del servicio
        Reservation newReservation = reservationService.createReservation(1L, 1L, 1L, reservationDate, "confirmed", 100.0);

        // Verificamos que el resultado es el esperado
        assertNotNull(newReservation);
        assertEquals(1L, newReservation.getReservationId());
        assertEquals("confirmed", newReservation.getStatus());
        assertEquals(100.0, newReservation.getPrice());

        // Verificamos que los repositorios fueron llamados correctamente
        verify(userRepository, times(1)).findById(1L);
        verify(tableSelectionRepository, times(1)).findById(1L);
        verify(scheduleRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testGetAllReservations() {
        List<Reservation> reservations = Arrays.asList(reservation);

        // Simulamos la interacción con el repositorio
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Llamamos al método del servicio
        List<Reservation> result = reservationService.getAllReservations();

        // Verificamos que el resultado es el esperado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getReservationId());

        // Verificamos que el repositorio fue llamado correctamente
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testGetReservationById() {
        // Simulamos la interacción con el repositorio
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        // Llamamos al método del servicio
        Reservation result = reservationService.getReservationById(1L);

        // Verificamos que el resultado es el esperado
        assertNotNull(result);
        assertEquals(1L, result.getReservationId());

        // Verificamos que el repositorio fue llamado correctamente
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReservationById_NotFound() {
        // Simulamos que no se encuentra la reserva
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamamos al método del servicio y verificamos que lanza la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservationService.getReservationById(1L));
        assertEquals("Reservation not found", exception.getMessage());

        // Verificamos que el repositorio fue llamado correctamente
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteReservation() {
        // No es necesario simular nada, solo verificamos que se llama al método deleteById
        doNothing().when(reservationRepository).deleteById(1L);

        // Llamamos al método del servicio
        reservationService.deleteReservation(1L);

        // Verificamos que el repositorio fue llamado correctamente
        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateReservation() {
        Reservation updatedReservation = new Reservation(1L, user, tableSelection, schedule, reservationDate, "cancelled", 120.0);

        // Simulamos la interacción con los repositorios
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(updatedReservation);

        // Llamamos al método del servicio
        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        // Verificamos que los datos fueron actualizados correctamente
        assertNotNull(result);
        assertEquals("cancelled", result.getStatus());
        assertEquals(120.0, result.getPrice());

        // Verificamos que los repositorios fueron llamados correctamente
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testUpdateReservation_NotFound() {
        // Simulamos que no se encuentra la reserva
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamamos al método del servicio y verificamos que lanza la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservationService.updateReservation(1L, reservation));
        assertEquals("Reservation not found", exception.getMessage());

        // Verificamos que el repositorio fue llamado correctamente
        verify(reservationRepository, times(1)).findById(1L);
    }
}
