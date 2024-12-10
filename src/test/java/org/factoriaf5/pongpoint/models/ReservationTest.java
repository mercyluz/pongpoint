package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationTest {

    private Reservation reservation;
    private Client mockClient;
    private TennisTable mockTable;
    private Schedule mockSchedule;

    @BeforeEach
    public void setUp() {
        // Creamos mocks de las dependencias
        mockClient = mock(Client.class);
        mockTable = mock(TennisTable.class);
        mockSchedule = mock(Schedule.class);

        // Creamos la instancia de Reservation
        reservation = new Reservation(mockClient, mockTable, mockSchedule, 100.0);
    }

    @Test
    public void testReservationConstructor() {
        assertNotNull(reservation);
        assertEquals(mockClient, reservation.getClient());
        assertEquals(mockTable, reservation.getTable());
        assertEquals(mockSchedule, reservation.getSchedule());
        assertEquals(100.0, reservation.getPrice());
        assertTrue(reservation.getConfirmed());
    }

    @Test
    public void testSettersAndGetters() {
        reservation.setPrice(150.0);
        reservation.setConfirmed(false);

        assertEquals(150.0, reservation.getPrice());
        assertFalse(reservation.getConfirmed());
    }

    @Test
    public void testClientSetter() {
        Client newClient = mock(Client.class);
        reservation.setClient(newClient);

        assertEquals(newClient, reservation.getClient());
    }

    @Test
    public void testTableSetter() {
        TennisTable newTable = mock(TennisTable.class);
        reservation.setTable(newTable);

        assertEquals(newTable, reservation.getTable());
    }

    @Test
    public void testScheduleSetter() {
        Schedule newSchedule = mock(Schedule.class);
        reservation.setSchedule(newSchedule);

        assertEquals(newSchedule, reservation.getSchedule());
    }
}
