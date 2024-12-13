package org.factoriaf5.pongpoint.DTOS;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDTOTest {

    @Test
    void testConstructorAndGettersSetters() {
        // Arrange
        Long clientId = 1L;
        Long tableId = 2L;
        Long scheduleId = 3L;
        Double price = 100.0;

        // Act
        ReservationDTO reservationDTO = new ReservationDTO(clientId, tableId, scheduleId, price);

        // Assert
        assertEquals(clientId, reservationDTO.getClientId());
        assertEquals(tableId, reservationDTO.getTableId());
        assertEquals(scheduleId, reservationDTO.getScheduleId());
        assertEquals(price, reservationDTO.getPrice());
    }

    @Test
    void testSetters() {
     
        ReservationDTO reservationDTO = new ReservationDTO();

        
        reservationDTO.setClientId(1L);
        reservationDTO.setTableId(2L);
        reservationDTO.setScheduleId(3L);
        reservationDTO.setPrice(100.0);

     
        assertEquals(1L, reservationDTO.getClientId());
        assertEquals(2L, reservationDTO.getTableId());
        assertEquals(3L, reservationDTO.getScheduleId());
        assertEquals(100.0, reservationDTO.getPrice());
    }

    @Test
    void testEmptyConstructor() {
     
        ReservationDTO reservationDTO = new ReservationDTO();

        
        assertNull(reservationDTO.getClientId());
        assertNull(reservationDTO.getTableId());
        assertNull(reservationDTO.getScheduleId());
        assertNull(reservationDTO.getPrice());
    }
}

