package org.factoriaf5.pongpoint.services;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        schedule = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        schedule.setId(1L);
    }

    @Test
    public void testCreateSchedule() {
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule createdSchedule = scheduleService.createSchedule(schedule);

        assertNotNull(createdSchedule);
        assertEquals(1L, createdSchedule.getId());
        verify(scheduleRepository, times(1)).save(schedule);
    }

    @Test
    public void testGetAllSchedules() {
        Schedule schedule1 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        Schedule schedule2 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(2), true, null);
        when(scheduleRepository.findAll()).thenReturn(Arrays.asList(schedule1, schedule2));

        var schedules = scheduleService.getAllSchedules();

        assertEquals(2, schedules.size());
        verify(scheduleRepository, times(1)).findAll();
    }

    @Test
    public void testGetScheduleById() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        Schedule foundSchedule = scheduleService.getScheduleById(1L);

        assertNotNull(foundSchedule);
        assertEquals(1L, foundSchedule.getId());
        verify(scheduleRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteSchedule() {
        when(scheduleRepository.existsById(1L)).thenReturn(true);

        boolean result = scheduleService.deleteSchedule(1L);

        assertTrue(result);
        verify(scheduleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateSchedule() {
        when(scheduleRepository.existsById(1L)).thenReturn(true);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        Schedule updatedSchedule = scheduleService.updateSchedule(1L, schedule);

        assertNotNull(updatedSchedule);
        assertEquals(1L, updatedSchedule.getId());
        verify(scheduleRepository, times(1)).save(schedule);
    }
}
