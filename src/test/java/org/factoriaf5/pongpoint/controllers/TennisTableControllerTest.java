package org.factoriaf5.pongpoint.controllers;

import org.factoriaf5.pongpoint.models.TennisTable;
import org.factoriaf5.pongpoint.services.TennisTableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TennisTableControllerTest {

    @Mock
    private TennisTableService tennisTableService;

    @InjectMocks
    private TennisTableController tennisTableController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tennisTableController).build();
    }

    @Test
    public void testCreateTennisTable() throws Exception {
        TennisTable tennisTable = new TennisTable("Table 1");
        tennisTable.setId(1L);

        when(tennisTableService.createTennisTable(any(TennisTable.class))).thenReturn(tennisTable);

        mockMvc.perform(post("/api/tables")
                .contentType("application/json")
                .content("{\"name\":\"Table 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(tennisTableService, times(1)).createTennisTable(any(TennisTable.class));
    }

    @Test
    public void testGetAllTennisTables() throws Exception {
        TennisTable tennisTable1 = new TennisTable("Table 1");
        TennisTable tennisTable2 = new TennisTable("Table 2");

        when(tennisTableService.getAllTennisTables()).thenReturn(Arrays.asList(tennisTable1, tennisTable2));

        mockMvc.perform(get("/api/tables"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Table 1"))
                .andExpect(jsonPath("$[1].name").value("Table 2"));

        verify(tennisTableService, times(1)).getAllTennisTables();
    }

    @Test
    public void testGetTennisTableById() throws Exception {
        TennisTable tennisTable = new TennisTable("Table 1");
        tennisTable.setId(1L);

        when(tennisTableService.getTennisTableById(1L)).thenReturn(tennisTable);

        mockMvc.perform(get("/api/tables/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Table 1"));

        verify(tennisTableService, times(1)).getTennisTableById(1L);
    }

    @Test
    public void testUpdateTennisTable() throws Exception {
        TennisTable tennisTable = new TennisTable("Table 1");
        tennisTable.setId(1L);

        when(tennisTableService.updateTennisTable(eq(1L), any(TennisTable.class))).thenReturn(tennisTable);

        mockMvc.perform(put("/api/tables/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Table 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Table 1"));

        verify(tennisTableService, times(1)).updateTennisTable(eq(1L), any(TennisTable.class));
    }

    @Test
    public void testDeleteTennisTable() throws Exception {
        when(tennisTableService.deleteTennisTable(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/tables/1"))
                .andExpect(status().isNoContent());

        verify(tennisTableService, times(1)).deleteTennisTable(1L);
    }
}
