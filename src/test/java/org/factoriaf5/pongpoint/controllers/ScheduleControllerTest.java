package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.Schedule;
import org.factoriaf5.pongpoint.services.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
        schedule = new Schedule(1L, LocalTime.of(10, 0), LocalTime.of(12, 0), LocalDate.of(2024, 12, 2));
    }

    @Test
    public void testCreateOrUpdateSchedule() throws Exception {
        when(scheduleService.createOrUpdateSchedule(schedule)).thenReturn(schedule);

        mockMvc.perform(post("/schedules")
                .contentType("application/json")
                .content("{\"scheduleId\":1, \"startTime\":\"10:00\", \"endTime\":\"12:00\", \"date\":\"2024-12-02\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }

    @Test
    public void testGetScheduleById() throws Exception {
        when(scheduleService.getScheduleById(1L)).thenReturn(java.util.Optional.of(schedule));

        mockMvc.perform(get("/schedules/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduleId").value(1));
    }

    @Test
    public void testDeleteSchedule() throws Exception {
        doNothing().when(scheduleService).deleteSchedule(1L);

        mockMvc.perform(delete("/schedules/1"))
                .andExpect(status().isNoContent());
    }
}
