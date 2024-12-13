package org.factoriaf5.pongpoint.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTest {

    private Schedule schedule;
    private TennisTable mockTable;
    private Reservation mockReservation;

    @BeforeEach
    public void setUp() {
     
        mockTable = mock(TennisTable.class);

        
        schedule = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, mockTable);

       
        mockReservation = mock(Reservation.class);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(mockReservation);

        schedule.setReservations(reservations);
    }

    @Test
    public void testScheduleConstructor() {
        assertNotNull(schedule);
        assertEquals(mockTable, schedule.getTable());
        assertNotNull(schedule.getStartDateTime());
        assertNotNull(schedule.getEndDateTime());
        assertTrue(schedule.getAvailable());
    }

    @Test
    public void testSettersAndGetters() {
        schedule.setAvailable(false);
        schedule.setStartDateTime(LocalDateTime.now().plusHours(1));
        schedule.setEndDateTime(LocalDateTime.now().plusHours(2));

        assertFalse(schedule.getAvailable());
        assertNotNull(schedule.getStartDateTime());
        assertNotNull(schedule.getEndDateTime());
    }

    @Test
    public void testReservations() {
        List<Reservation> reservations = schedule.getReservations();
        assertEquals(1, reservations.size());
        assertEquals(mockReservation, reservations.get(0));
    }

    @Test
    public void testTableSetter() {
        TennisTable newTable = mock(TennisTable.class);
        schedule.setTable(newTable);

        assertEquals(newTable, schedule.getTable());
    }

    @Test
    public void testStartAndEndDate() {
        LocalDateTime startDate = LocalDateTime.of(2024, 12, 9, 10, 0);
        LocalDateTime endDate = startDate.plusHours(2);

        schedule.setStartDateTime(startDate);
        schedule.setEndDateTime(endDate);

        assertEquals(startDate, schedule.getStartDateTime());
        assertEquals(endDate, schedule.getEndDateTime());
    }
}
