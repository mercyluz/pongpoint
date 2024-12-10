package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        schedule = new Schedule(1L, LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2024, 12, 2));
    }

    @Test
    public void testCreateOrUpdateSchedule() {
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule result = scheduleService.createOrUpdateSchedule(schedule);
        assertNotNull(result);
        assertEquals(schedule.getScheduleId(), result.getScheduleId());
        verify(scheduleRepository, times(1)).save(schedule);
    }

    @Test
    public void testGetScheduleById() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        Optional<Schedule> result = scheduleService.getScheduleById(1L);
        assertTrue(result.isPresent());
        assertEquals(schedule.getScheduleId(), result.get().getScheduleId());
    }

    @Test
    public void testDeleteSchedule() {
        doNothing().when(scheduleRepository).deleteById(1L);

        scheduleService.deleteSchedule(1L);
        verify(scheduleRepository, times(1)).deleteById(1L);
    }
}

