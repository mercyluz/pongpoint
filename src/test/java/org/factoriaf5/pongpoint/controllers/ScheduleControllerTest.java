package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.services.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    public void testCreateSchedule() throws Exception {
        Schedule schedule = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        schedule.setId(1L);

        when(scheduleService.createSchedule(any(Schedule.class))).thenReturn(schedule);

        mockMvc.perform(post("/api/schedules")
                .contentType("application/json")
                .content("{\"startDateTime\":\"2024-12-09T10:00:00\", \"endDateTime\":\"2024-12-09T11:00:00\", \"available\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(scheduleService, times(1)).createSchedule(any(Schedule.class));
    }

    @Test
    public void testGetAllSchedules() throws Exception {
        Schedule schedule1 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        Schedule schedule2 = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(2), true, null);
        when(scheduleService.getAllSchedules()).thenReturn(Arrays.asList(schedule1, schedule2));

        mockMvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(scheduleService, times(1)).getAllSchedules();
    }

    @Test
    public void testGetScheduleById() throws Exception {
        Schedule schedule = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        schedule.setId(1L);
        when(scheduleService.getScheduleById(1L)).thenReturn(schedule);

        mockMvc.perform(get("/api/schedules/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(scheduleService, times(1)).getScheduleById(1L);
    }

    @Test
    public void testUpdateSchedule() throws Exception {
        Schedule schedule = new Schedule(LocalDateTime.now(), LocalDateTime.now().plusHours(1), true, null);
        schedule.setId(1L);
        when(scheduleService.updateSchedule(eq(1L), any(Schedule.class))).thenReturn(schedule);

        mockMvc.perform(put("/api/schedules/1")
                .contentType("application/json")
                .content("{\"startDateTime\":\"2024-12-09T10:00:00\", \"endDateTime\":\"2024-12-09T11:00:00\", \"available\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(scheduleService, times(1)).updateSchedule(eq(1L), any(Schedule.class));
    }

    @Test
    public void testDeleteSchedule() throws Exception {
        when(scheduleService.deleteSchedule(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/schedules/1"))
                .andExpect(status().isNoContent());

        verify(scheduleService, times(1)).deleteSchedule(1L);
    }
}
