package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Reservation;
import org.factoriaf5.pongpoint.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    public void testCreateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);

        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/api/reservations")
                .contentType("application/json")
                .content("{\"client\":null, \"table\":null, \"schedule\":null, \"price\":50.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
        
        verify(reservationService, times(1)).createReservation(any(Reservation.class));
    }

    @Test
    public void testGetAllReservations() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);

        when(reservationService.getAllReservations()).thenReturn(Arrays.asList(reservation));

        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    public void testGetReservationById() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);

        when(reservationService.getReservationById(1L)).thenReturn(reservation);

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(reservationService, times(1)).getReservationById(1L);
    }

    @Test
    public void testDeleteReservation() throws Exception {
        when(reservationService.deleteReservation(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/reservations/1"))
                .andExpect(status().isNoContent());

        verify(reservationService, times(1)).deleteReservation(1L);
    }

    @Test
    public void testUpdateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setPrice(50.0);

        when(reservationService.updateReservation(1L, reservation)).thenReturn(reservation);

        mockMvc.perform(put("/api/reservations/1")
                .contentType("application/json")
                .content("{\"client\":null, \"table\":null, \"schedule\":null, \"price\":50.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(reservationService, times(1)).updateReservation(1L, reservation);
    }
}
