package org.factoriaf5.pongpoint.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.models.User;
import org.factoriaf5.pongpoint.models.TableSelection;
import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private MockMvc mockMvc;

    private Reservation reservation;
    private User user;
    private TableSelection tableSelection;
    private Schedule schedule;
    private LocalDateTime reservationDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        
        // Crear objetos de prueba
        user = new User(1L, "John Doe", "john.doe@example.com", "user");
        tableSelection = new TableSelection(1L, "Table 1", 50.0); // basePrice = 50.0
        schedule = new Schedule(1L, "12:00", "2024-12-08");
        reservationDate = LocalDateTime.of(2024, 12, 8, 12, 0, 0, 0);

        reservation = new Reservation(1L, user, tableSelection, schedule, reservationDate, "confirmed", 100.0);
    }

    @Test
    void testCreateReservation() throws Exception {
        // Simulamos que el servicio creará una nueva reserva
        when(reservationService.createReservation(1L, 1L, 1L, reservationDate, "confirmed", 100.0)).thenReturn(reservation);

        // Realizamos la solicitud POST para crear la reserva
        mockMvc.perform(post("/api/reservations")
                .param("userId", "1")
                .param("tableSelectionId", "1")
                .param("scheduleId", "1")
                .param("date", "2024-12-08T12:00:00")
                .param("status", "confirmed")
                .param("price", "100.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())  // Esperamos un código de estado 201 Created
                .andExpect(jsonPath("$.reservationId").value(1))
                .andExpect(jsonPath("$.status").value("confirmed"))
                .andExpect(jsonPath("$.price").value(100.0));

        // Verificamos que el servicio fue llamado con los parámetros correctos
        verify(reservationService, times(1)).createReservation(1L, 1L, 1L, reservationDate, "confirmed", 100.0);
    }

    @Test
    void testGetAllReservations() throws Exception {
        List<Reservation> reservations = Arrays.asList(reservation);

        // Simulamos que el servicio devolverá todas las reservas
        when(reservationService.getAllReservations()).thenReturn(reservations);

        // Realizamos la solicitud GET para obtener todas las reservas
        mockMvc.perform(get("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Esperamos un código de estado 200 OK
                .andExpect(jsonPath("$[0].reservationId").value(1))
                .andExpect(jsonPath("$[0].status").value("confirmed"))
                .andExpect(jsonPath("$[0].price").value(100.0));

        // Verificamos que el servicio fue llamado una vez
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    void testGetReservationById() throws Exception {
        // Simulamos que el servicio devolverá la reserva por ID
        when(reservationService.getReservationById(1L)).thenReturn(reservation);

        // Realizamos la solicitud GET para obtener la reserva por ID
        mockMvc.perform(get("/api/reservations/{reservationId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Esperamos un código de estado 200 OK
                .andExpect(jsonPath("$.reservationId").value(1))
                .andExpect(jsonPath("$.status").value("confirmed"))
                .andExpect(jsonPath("$.price").value(100.0));

        // Verificamos que el servicio fue llamado con el ID correcto
        verify(reservationService, times(1)).getReservationById(1L);
    }

    @Test
    void testDeleteReservation() throws Exception {
        // No es necesario simular nada para este caso, simplemente verificamos que el método DELETE funcione

        // Realizamos la solicitud DELETE para eliminar la reserva
        mockMvc.perform(delete("/api/reservations/{reservationId}", 1L))
                .andExpect(status().isNoContent());  // Esperamos un código de estado 204 No Content

        // Verificamos que el servicio fue llamado con el ID correcto
        verify(reservationService, times(1)).deleteReservation(1L);
    }

    @Test
    void testUpdateReservation() throws Exception {
        // Creamos una reserva actualizada
        Reservation updatedReservation = new Reservation(1L, user, tableSelection, schedule, reservationDate, "cancelled", 120.0);

        // Simulamos que el servicio actualizará la reserva correctamente
        when(reservationService.updateReservation(1L, updatedReservation)).thenReturn(updatedReservation);

        // Realizamos la solicitud PUT para actualizar la reserva
        mockMvc.perform(put("/api/reservations/{reservationId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"reservationId\": 1, \"user\": { \"userId\": 1 }, \"tableSelection\": { \"tableId\": 1 }, \"schedule\": { \"scheduleId\": 1 }, \"date\": \"2024-12-08T12:00:00\", \"status\": \"cancelled\", \"price\": 120.0 }"))
                .andExpect(status().isOk())  // Esperamos un código de estado 200 OK
                .andExpect(jsonPath("$.status").value("cancelled"))
                .andExpect(jsonPath("$.price").value(120.0));

        // Verificamos que el servicio fue llamado con el ID correcto y los datos actualizados
        verify(reservationService, times(1)).updateReservation(1L, updatedReservation);
    }
}
